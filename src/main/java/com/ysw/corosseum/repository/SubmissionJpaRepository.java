package com.ysw.corosseum.repository;

import com.ysw.corosseum.domain.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionJpaRepository extends JpaRepository<Submission, String> {

	@Query("SELECT s FROM Submission s JOIN FETCH s.quest WHERE s.quest.questDate = :date ORDER BY s.createdAt DESC")
	List<Submission> findByQuestDate(LocalDate date);

	@Query("SELECT COUNT(s) FROM Submission s")
	long countAllActive();

	@Query("SELECT COUNT(s) FROM Submission s WHERE s.quest.id = :questId")
	long countByQuestId(String questId);

	@Query("SELECT s FROM Submission s JOIN FETCH s.quest WHERE s.id = :id AND s.quest.id = :questId")
	Optional<Submission> findByIdAndQuestId(String id, String questId);
}
