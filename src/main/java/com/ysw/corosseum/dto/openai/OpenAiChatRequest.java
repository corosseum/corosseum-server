package com.ysw.corosseum.dto.openai;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiChatRequest {

	private String model;
	private List<Message> messages;
	@JsonProperty("response_format")
	private ResponseFormat responseFormat;
	private Double temperature;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Message {
		private String role;
		private String content;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponseFormat {
		private String type; // json_object | json_schema
		@JsonProperty("json_schema")
		private Map<String, Object> jsonSchema;
	}
}
