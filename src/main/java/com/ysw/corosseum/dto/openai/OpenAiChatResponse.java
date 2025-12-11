package com.ysw.corosseum.dto.openai;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiChatResponse {

	private List<Choice> choices;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Choice {
		private Message message;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Message {
		private String role;
		private String content;
	}
}
