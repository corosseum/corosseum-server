package com.ysw.corosseum.repository.impl;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.repository.SubmissionJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubmissionRepository {

	private final SubmissionJpaRepository submissionJpaRepository;
	private final EntityManager entityManager;

	public Optional<Submission> findById(String id) {
		TypedQuery<Submission> query = entityManager.createQuery(
			"SELECT s FROM Submission s JOIN FETCH s.quest WHERE s.id = :id",
			Submission.class
		);
		query.setParameter("id", id);
		return query.getResultStream().findFirst();
	}

	public Optional<Submission> findByIdAndQuestId(String id, String questId) {
		return submissionJpaRepository.findByIdAndQuestId(id, questId);
	}

	public Submission save(Submission submission) throws DataIntegrityViolationException {
		return submissionJpaRepository.saveAndFlush(submission);
	}

	public List<Submission> findByQuestDate(LocalDate date) {
		return submissionJpaRepository.findByQuestDate(date);
	}

	public List<Submission> findAllActive(int offset, int limit) {
		TypedQuery<Submission> query = entityManager.createQuery(
			"SELECT s FROM Submission s JOIN FETCH s.quest ORDER BY s.createdAt DESC",
			Submission.class
		);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	public long countAllActive() {
		return submissionJpaRepository.countAllActive();
	}

	public List<Submission> findByQuestIdOrderByCreatedAtDesc(String questId, int offset, int limit) {
		TypedQuery<Submission> query = entityManager.createQuery(
			"SELECT s FROM Submission s JOIN FETCH s.quest WHERE s.quest.id = :questId ORDER BY s.createdAt DESC",
			Submission.class
		);
		query.setParameter("questId", questId);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	public long countByQuestId(String questId) {
		return submissionJpaRepository.countByQuestId(questId);
	}

	public List<Submission> findTop10ByTotalVotes() {
		TypedQuery<Submission> query = entityManager.createQuery(
			"""
			SELECT s FROM Submission s
			JOIN FETCH s.quest
			LEFT JOIN Vote v ON v.submission = s
			GROUP BY s, s.quest.id
			HAVING COUNT(v) > 0
			ORDER BY COUNT(v) DESC
			""",
			Submission.class
		);
		query.setMaxResults(10);
		return query.getResultList();
	}
}
