package com.ysw.corosseum.repository.impl;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.repository.SubmissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubmissionRepository {

	private final SubmissionJpaRepository submissionJpaRepository;

	public Optional<Submission> findById(String id) {
		return submissionJpaRepository.findById(id);
	}

	public Submission save(Submission submission) {
		return submissionJpaRepository.saveAndFlush(submission);
	}

	public Page<Submission> findAllActive(Pageable pageable) {
		return submissionJpaRepository.findAllActive(pageable);
	}

	public List<Submission> findByQuestDate(LocalDate date) {
		return submissionJpaRepository.findByQuestDate(date);
	}
}
