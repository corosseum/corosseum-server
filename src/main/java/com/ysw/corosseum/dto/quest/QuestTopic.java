package com.ysw.corosseum.dto.quest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class QuestTopic {

	private String description;

	public static QuestTopic of(String description) {
		return QuestTopic.builder()
			.description(description)
			.build();
	}
}
