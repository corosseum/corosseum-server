package com.ysw.corosseum.repository.impl;

import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.type.QuestStatus;
import com.ysw.corosseum.repository.QuestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestRepository {

	private final QuestJpaRepository questJpaRepository;

	public Optional<Quest> findById(String id) {
		return questJpaRepository.findById(id);
	}

	public Quest save(Quest quest) {
		return questJpaRepository.save(quest);
	}

	public Optional<Quest> findByStatus(QuestStatus status) {
		return questJpaRepository.findByStatus(status);
	}

	public Optional<Quest> findActiveQuest() {
		return questJpaRepository.findActiveQuest();
	}

	public Optional<Quest> findByQuestDate(LocalDate date) {
		return questJpaRepository.findByQuestDate(date);
	}

	public boolean existsByQuestDate(LocalDate date) {
		return questJpaRepository.existsByQuestDate(date);
	}

	public java.util.List<Quest> findByQuestDateBetween(LocalDate start, LocalDate end) {
		return questJpaRepository.findByQuestDateBetween(start, end);
	}
}
