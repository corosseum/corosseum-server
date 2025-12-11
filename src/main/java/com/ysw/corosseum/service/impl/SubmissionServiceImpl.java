package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.common.exception.NotFoundException;
import com.ysw.corosseum.domain.vo.ReviewResult;
import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import com.ysw.corosseum.repository.impl.QuestRepository;
import com.ysw.corosseum.repository.impl.SubmissionRepository;
import com.ysw.corosseum.service.ReviewService;
import com.ysw.corosseum.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

	private final SubmissionRepository submissionRepository;
	private final QuestRepository questRepository;
	private final ReviewService reviewService;

	@Override
	@Transactional
	public void submitCode(String questId, String userId, String code) {
		Quest quest = questRepository.findById(questId)
			.orElseThrow(() -> new NotFoundException("퀘스트를 찾을 수 없습니다."));

		Submission submission = Submission.create(quest, userId, code);
		submission = submissionRepository.save(submission);

		// 리뷰 생성
		ReviewResult reviewResult = reviewService.reviewCode(code);
		submission.updateReview(
			reviewResult.getReadabilityScore(),
			reviewResult.getCreativityScore(),
			reviewResult.getInefficiencyScore(),
			reviewResult.getComment()
		);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SubmissionResponseDTO> getSubmissions(Pageable pageable) {
		return submissionRepository.findAllActive(pageable)
			.map(SubmissionResponseDTO::of);
	}

	@Override
	@Transactional(readOnly = true)
	public SubmissionResponseDTO getSubmission(String id) {
		Submission submission = submissionRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("제출을 찾을 수 없습니다."));
		return SubmissionResponseDTO.of(submission);
	}
}
