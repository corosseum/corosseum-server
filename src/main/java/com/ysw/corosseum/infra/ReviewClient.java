package com.ysw.corosseum.infra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ysw.corosseum.dto.openai.OpenAiChatRequest;
import com.ysw.corosseum.dto.submission.ReviewResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReviewClient {

	private final OpenAiClient openAiClient;

	private static final String SYSTEM_PROMPT = """
	너는 시니컬하고 돌려말하는 걸 좋아하는 시니어 개발자다. 
	절대 건설적인 피드백이나 솔루션을 제공하지 않는다. 오직 비꼬고 조롱하는 것에만 집중한다.
	직접적으로 말하지 않고 은근히 비꼬는 말투를 사용하되, 때로는 강하게 비꼬기도 한다.
	비꼬는 말에 유머 감각을 자연스럽게 섞어서 재미있게 만든다. 농담, 비유, 과장 등을 활용한다.
	모든 응답은 유효한 JSON 형식이어야 한다.
	""";

	private static final String USER_PROMPT_TEMPLATE = """
	다음 퀘스트 설명을 기반으로 제출된 코드를 리뷰하라. 퀘스트의 요구사항과 제약사항을 고려하여 평가한다.
	
	퀘스트 설명:
	%s
	
	제출된 코드:
	%s
	
	4가지 항목으로 평가한다:
	1. readability_score: 코드가 얼마나 읽기 쉬운가? (0-100 정수)
	2. creativity_score: 퀘스트의 제약사항을 고려했을 때, 발상이 얼마나 기괴하고 창의적인가? (0-100 정수)
	3. inefficiency_score: 얼마나 비효율적이고 읽는 사람을 화나게 하는가? 얼마나 광기가 섞였나? (0-100 정수)
	4. roast_comment: 퀘스트 설명과 코드를 대조하여 강하게 비꼬되 유머러스한 코멘트를 작성한다. 
	   퀘스트의 요구사항이나 제약사항을 얼마나 잘 따랐는지(또는 얼마나 무시했는지)를 비꼬는 맥락으로 활용한다.
	   건설적인 조언은 절대 하지 말고, 오직 비꼬고 조롱하는 것만 작성한다. 
	   돌려말하되 강하게 비꼬는 톤을 유지하고, 재미있는 비유나 농담을 자연스럽게 섞는다. 솔루션이나 개선 방안은 절대 제시하지 않는다.
	""";

	public ReviewResponseDTO reviewCode(String questDescription, String code) {
		String userPrompt = USER_PROMPT_TEMPLATE.formatted(questDescription, code);

		Map<String, Object> schema = Map.of(
			"type", "object",
			"properties", Map.of(
				"readability_score", Map.of("type", "integer"),
				"creativity_score", Map.of("type", "integer"),
				"inefficiency_score", Map.of("type", "integer"),
				"roast_comment", Map.of("type", "string")
			),
			"required", List.of("readability_score", "creativity_score", "inefficiency_score", "roast_comment")
		);

		OpenAiChatRequest.ResponseFormat responseFormat = openAiClient.jsonSchemaFormat("CodeReview", schema);

		return openAiClient.requestJson(SYSTEM_PROMPT, userPrompt, ReviewResponseDTO.class, responseFormat);
	}
}
