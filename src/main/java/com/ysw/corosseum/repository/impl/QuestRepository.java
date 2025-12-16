package com.ysw.corosseum.repository.impl;

import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.type.QuestStatus;
import com.ysw.corosseum.repository.QuestJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestRepository {

	private final QuestJpaRepository questJpaRepository;
	private final EntityManager entityManager;

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

	public List<Quest> findByQuestDateBetween(LocalDate start, LocalDate end) {
		return questJpaRepository.findByQuestDateBetween(start, end);
	}

	public List<Quest> findAllOrderByQuestDateDesc(int offset, int limit) {
		TypedQuery<Quest> query = entityManager.createQuery(
			"SELECT q FROM Quest q WHERE q.status != 'DELETED' ORDER BY q.questDate DESC",
			Quest.class
		);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	public List<Quest> findAllCompletedOrderByQuestDateDesc(int offset, int limit) {
		TypedQuery<Quest> query = entityManager.createQuery(
			"SELECT q FROM Quest q WHERE q.status IN ('ACTIVATED', 'COMPLETED') ORDER BY q.questDate DESC",
			Quest.class
		);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	public long countAll() {
		return questJpaRepository.countAll();
	}

	public long countCompleted() {
		return questJpaRepository.countCompleted();
	}
}
