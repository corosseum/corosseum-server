package com.ysw.corosseum.dto.quest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class QuestTopics {

	private List<QuestTopic> topics;

	public static QuestTopics of(List<QuestTopic> topics) {
		return QuestTopics.builder()
			.topics(topics)
			.build();
	}
}
