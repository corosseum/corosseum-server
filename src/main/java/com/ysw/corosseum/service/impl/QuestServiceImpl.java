package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.common.exception.NotFoundException;
import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
import com.ysw.corosseum.dto.quest.QuestResponseDTO;
import com.ysw.corosseum.dto.quest.QuestTopic;
import com.ysw.corosseum.repository.impl.QuestRepository;
import com.ysw.corosseum.service.QuestGenerationService;
import com.ysw.corosseum.service.QuestService;
import com.ysw.corosseum.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestServiceImpl implements QuestService {

	private final QuestRepository questRepository;
	private final QuestGenerationService questGenerationService;

	@Override
	@Transactional(readOnly = true)
	public QuestResponseDTO getTodayQuest() {
		Quest quest = questRepository.findActiveQuest()
			.orElseThrow(() -> new NotFoundException("활성 퀘스트가 없습니다."));
		
		return QuestResponseDTO.of(quest);
	}

	@Override
	@Transactional(readOnly = true)
	public PagedResponseDTO<QuestResponseDTO> getCompletedQuests(PagingRequestDTO pagingRequest) {
		long total = questRepository.countCompleted();

		if (total == 0) {
			return PagedResponseDTO.empty(pagingRequest);
		}

		List<Quest> quests = questRepository.findAllCompletedOrderByQuestDateDesc(
			pagingRequest.getOffset(),
			pagingRequest.getLimit()
		);

		return PagedResponseDTO.of(
			pagingRequest,
			total,
			quests.stream()
				.map(QuestResponseDTO::of)
				.toList()
		);
	}

	@Override
	@Transactional
	@Scheduled(cron = "0 0 0 * * *")
	public void refreshDailyQuest() {
		LocalDate today = DateUtil.today();
		
		// 전날 활성 퀘스트 비활성화
		questRepository.findActiveQuest().ifPresent(Quest::deactivate);
		
		// 오늘 날짜의 퀘스트가 있으면 활성화, 없으면 종료
		questRepository.findByQuestDate(today).ifPresentOrElse(
			Quest::activate,
			() -> log.error("오늘({}) 활성화할 퀘스트가 없습니다.", today)
		);
	}

	@Override
	@Transactional
	@Scheduled(cron = "0 0 12 ? * SAT", zone = "Asia/Seoul")
//	@Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
	public void generateWeeklyQuests() {
		List<LocalDate> nextWeekDates = DateUtil.getNextWeekDates();

		LocalDate start = nextWeekDates.getFirst();
		LocalDate end = nextWeekDates.getLast();

		Set<LocalDate> existingDates = questRepository.findByQuestDateBetween(start, end).stream()
			.map(Quest::getQuestDate)
			.collect(Collectors.toSet());

		List<LocalDate> missingDates = nextWeekDates.stream()
			.filter(date -> !existingDates.contains(date))
			.toList();

		if (missingDates.isEmpty()) {
			log.info("다음 주 퀘스트가 이미 모두 존재합니다.");
			return;
		}

		List<QuestTopic> topics = questGenerationService.generateQuests(missingDates.size());
		Iterator<QuestTopic> iterator = topics.iterator();

		for (LocalDate date : missingDates) {
			if (!iterator.hasNext()) {
				log.warn("퀘스트 주제가 부족하여 생성 중단");
				break;
			}
			QuestTopic topic = iterator.next();
			Quest quest = Quest.create(topic.getDescription(), date);
			questRepository.save(quest);
		}
	}
}
