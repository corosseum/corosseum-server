package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.quest.QuestResponseDTO;

public interface QuestService {
	QuestResponseDTO getCurrentQuest();
	void refreshDailyQuest();
	void generateWeeklyQuests();
}
