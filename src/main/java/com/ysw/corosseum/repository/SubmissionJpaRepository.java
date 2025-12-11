package com.ysw.corosseum.repository;

import com.ysw.corosseum.domain.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubmissionJpaRepository extends JpaRepository<Submission, String> {

	@Query("SELECT s FROM Submission s ORDER BY s.createdAt DESC")
	Page<Submission> findAllActive(Pageable pageable);

	@Query("SELECT s FROM Submission s WHERE s.quest.questDate = :date ORDER BY s.createdAt DESC")
	List<Submission> findByQuestDate(LocalDate date);
}
