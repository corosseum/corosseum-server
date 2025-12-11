package com.ysw.corosseum.infra;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysw.corosseum.common.exception.InternalServerErrorException;
import com.ysw.corosseum.dto.openai.OpenAiChatRequest;
import com.ysw.corosseum.dto.openai.OpenAiChatResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenAiClient {

	private final RestClient openAiRestClient;
	private final ObjectMapper objectMapper;

	@Value("${openai.model}")
	private String model;

	@Value("${openai.temperature}")
	private Double temperature;

	public OpenAiChatRequest.ResponseFormat jsonObjectFormat() {
		return new OpenAiChatRequest.ResponseFormat("json_object", null);
	}

	public OpenAiChatRequest.ResponseFormat jsonSchemaFormat(String name, Map<String, Object> schema) {
		return new OpenAiChatRequest.ResponseFormat(
			"json_schema",
			Map.of("name", name, "schema", schema)
		);
	}

	public <T> T requestJson(
		String systemPrompt,
		String userPrompt,
		Class<T> responseType,
		OpenAiChatRequest.ResponseFormat responseFormat
	) {

		OpenAiChatRequest request = buildRequest(systemPrompt, userPrompt, responseFormat);

		try {
			OpenAiChatResponse response = callOpenAi(request);
			String content = extractContent(response);

			return objectMapper.readValue(content, responseType);
		} catch (Exception e) {
			log.error("OpenAI API 호출 실패", e);
			throw new InternalServerErrorException("OpenAI 서비스 연동 중 오류가 발생했습니다.", e);
		}
	}

	private OpenAiChatRequest buildRequest(
		String systemPrompt,
		String userPrompt,
		OpenAiChatRequest.ResponseFormat responseFormat
	) {
		OpenAiChatRequest request = new OpenAiChatRequest();
		request.setModel(model);
		request.setTemperature(temperature);
		request.setMessages(List.of(
			new OpenAiChatRequest.Message("system", systemPrompt),
			new OpenAiChatRequest.Message("user", userPrompt)
		));
		request.setResponseFormat(Objects.requireNonNullElseGet(responseFormat, this::jsonObjectFormat));
		return request;
	}

	public OpenAiChatResponse callOpenAi(OpenAiChatRequest request) {
		return openAiRestClient.post()
			.uri("/chat/completions")
			.body(request)
			.retrieve()
			.body(OpenAiChatResponse.class);
	}

	public String extractContent(OpenAiChatResponse response) {
		if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
			throw new IllegalStateException("Empty response from OpenAI");
		}

		OpenAiChatResponse.Message message = response.getChoices().getFirst().getMessage();
		if (message == null || message.getContent() == null || message.getContent().isBlank()) {
			throw new IllegalStateException("Empty content from OpenAI");
		}

		return message.getContent();
	}
}
