package com.ysw.corosseum.infra;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ysw.corosseum.dto.openai.OpenAiChatRequest;
import com.ysw.corosseum.dto.quest.QuestTopics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestGenerationClient {

	private final OpenAiClient openAiClient;

	private static final String SYSTEM_PROMPT = """
	너는 시니컬하고 돌려말하는 걸 좋아하는 시니어 개발자다. 직접적으로 말하지 않고 은근히 비꼬는 말투를 사용한다.
	굉장히 단순하고 재치있는 코딩 퀘스트를 만들어야 한다. 
	모든 퀘스트는 '가장 더럽게', '가장 아름답게', '가장 비효율적으로' 같은 경쟁 요소를 포함해야 한다.
	예시: "hello world를 가장 더럽게 출력하기", "별찍기로 크리스마스 트리를 가장 아름답게 만들기", "1부터 100까지 합을 가장 비효율적으로 구하기"
	퀘스트는 재치있고 단순한 목표를 가져야 하며, 복잡한 알고리즘 문제가 아니어야 한다.
	""";

	private static final String USER_PROMPT_TEMPLATE = """
	다음 주간 퀘스트 %d개를 만들어라.
	각 퀘스트는 description(설명)만 포함하면 된다. 
	description에는 단순하고 재치있는 목표와 '가장 ~하게' 같은 경쟁 요소를 명확히 기술해야 한다.
	예시 형식: "[단순한 목표]를 가장 [경쟁 요소]로 [만들기/구현하기/출력하기]"
	""";

	public QuestTopics generateQuests(int count) {
		String userPrompt = USER_PROMPT_TEMPLATE.formatted(count);

		Map<String, Object> schema = Map.of(
			"type", "object",
			"properties", Map.of(
				"topics", Map.of(
					"type", "array",
					"items", Map.of(
						"type", "object",
						"properties", Map.of(
							"description", Map.of("type", "string")
						),
						"required", List.of("description")
					)
				)
			),
			"required", List.of("topics")
		);

		OpenAiChatRequest.ResponseFormat responseFormat = openAiClient.jsonSchemaFormat("QuestTopics", schema);

        return openAiClient.requestJson(SYSTEM_PROMPT, userPrompt, QuestTopics.class, responseFormat);
    }
}
