package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.common.exception.BadRequestException;
import com.ysw.corosseum.common.exception.NotFoundException;
import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.domain.entity.Vote;
import com.ysw.corosseum.repository.impl.SubmissionRepository;
import com.ysw.corosseum.repository.impl.VoteRepository;
import com.ysw.corosseum.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    @Transactional
    public void vote(String submissionId, String userId, VoteType voteType) {
        Submission submission = submissionRepository.findById(submissionId)
            .orElseThrow(() -> new NotFoundException("제출을 찾을 수 없습니다."));

        voteRepository.findBySubmissionIdAndUserId(submissionId, userId)
            .ifPresentOrElse(
                existingVote -> existingVote.changeVoteType(voteType),
                () -> {
                    try {
                        voteRepository.save(Vote.create(submission, userId, voteType));
                    } catch (DataIntegrityViolationException e) {
                        throw new BadRequestException("이미 투표하셨습니다.");
                    }
                }
            );
    }
}
