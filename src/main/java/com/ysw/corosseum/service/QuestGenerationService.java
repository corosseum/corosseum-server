package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.quest.QuestTopic;

import java.util.List;

public interface QuestGenerationService {

	List<QuestTopic> generateQuests(int count);
}
