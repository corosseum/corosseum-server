package com.ysw.corosseum.repository;

import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.type.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface QuestJpaRepository extends JpaRepository<Quest, String> {

	@Query("SELECT q FROM Quest q WHERE q.status = :status")
	Optional<Quest> findByStatus(QuestStatus status);

	default Optional<Quest> findActiveQuest() {
		return findByStatus(QuestStatus.ACTIVATED);
	}

	@Query("SELECT q FROM Quest q WHERE q.questDate = :date")
	Optional<Quest> findByQuestDate(LocalDate date);

	boolean existsByQuestDate(LocalDate date);

	@Query("SELECT q FROM Quest q WHERE q.questDate BETWEEN :start AND :end")
	java.util.List<Quest> findByQuestDateBetween(LocalDate start, LocalDate end);
}
