package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
import com.ysw.corosseum.dto.quest.QuestResponseDTO;

public interface QuestService {
	QuestResponseDTO getTodayQuest();
	PagedResponseDTO<QuestResponseDTO> getCompletedQuests(PagingRequestDTO pagingRequest);
	void refreshDailyQuest();
	void generateWeeklyQuests();
}
