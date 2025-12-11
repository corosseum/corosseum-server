package com.ysw.corosseum.repository.impl;

import com.ysw.corosseum.domain.entity.Vote;
import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.repository.VoteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoteRepository {

	private final VoteJpaRepository voteJpaRepository;

	public void save(Vote vote) throws DataIntegrityViolationException {
		voteJpaRepository.saveAndFlush(vote);
	}

	public Optional<Vote> findBySubmissionIdAndUserId(String submissionId, String userId) {
		return voteJpaRepository.findBySubmissionIdAndUserId(submissionId, userId);
	}

	public List<Object[]> countBySubmissionIdsGroupByVoteType(List<String> submissionIds) {
		return voteJpaRepository.countBySubmissionIdsGroupByVoteType(submissionIds);
	}

	public Map<String, Map<VoteType, Long>> getVoteCountsBySubmissionIds(List<String> submissionIds) {
		return voteJpaRepository.getVoteCountsBySubmissionIds(submissionIds);
	}
}
