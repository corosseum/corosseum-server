package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.dto.quest.QuestTopic;
import com.ysw.corosseum.dto.quest.QuestTopics;
import com.ysw.corosseum.infra.QuestGenerationClient;
import com.ysw.corosseum.service.QuestGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestGenerationServiceImpl implements QuestGenerationService {

	private static final int MAX_RETRY = 5;

	private final QuestGenerationClient questGenerationClient;

	@Override
	public List<QuestTopic> generateQuests(int count) {
		for (int attempt = 1; attempt <= MAX_RETRY; attempt++) {
			try {
				QuestTopics questTopics = questGenerationClient.generateQuests(count);
				if (questTopics != null && questTopics.getTopics() != null && !questTopics.getTopics().isEmpty()) {
					return questTopics.getTopics();
				}
			} catch (Exception e) {
				log.warn("퀘스트 생성 요청 실패 {}/{}: {}", attempt, MAX_RETRY, e.getMessage());
			}
		}
		log.error("퀘스트 생성 요청이 {}번 시도 후 실패했습니다. 퀘스트 생성을 종료합니다.", MAX_RETRY);
		throw new RuntimeException();
	}
}
