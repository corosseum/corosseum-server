package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.common.exception.NotFoundException;
import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.domain.vo.ReviewResult;
import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import com.ysw.corosseum.repository.impl.QuestRepository;
import com.ysw.corosseum.repository.impl.SubmissionRepository;
import com.ysw.corosseum.repository.impl.VoteRepository;
import com.ysw.corosseum.service.ReviewService;
import com.ysw.corosseum.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

	private final SubmissionRepository submissionRepository;
	private final QuestRepository questRepository;
	private final ReviewService reviewService;
	private final VoteRepository voteRepository;

	@Override
	@Transactional
	public void submitCode(String questId, String userId, String code) {
		Quest quest = questRepository.findById(questId)
			.orElseThrow(() -> new NotFoundException("퀘스트를 찾을 수 없습니다."));

		Submission submission = Submission.create(quest, userId, code);
		submission = submissionRepository.save(submission);

		// 리뷰 생성
		ReviewResult reviewResult = reviewService.reviewCode(quest.getDescription(), code);
		submission.updateReview(
			reviewResult.getReadabilityScore(),
			reviewResult.getCreativityScore(),
			reviewResult.getInefficiencyScore(),
			reviewResult.getComment()
		);
	}

	@Override
	@Transactional(readOnly = true)
	public PagedResponseDTO<SubmissionResponseDTO> getSubmissions(PagingRequestDTO pagingRequest) {
		long total = submissionRepository.countAllActive();
		if (total == 0) {
			return PagedResponseDTO.empty(pagingRequest);
		}
		List<Submission> submissions = submissionRepository.findAllActive(
			pagingRequest.getOffset(),
			pagingRequest.getLimit()
		);
		List<String> submissionIds = submissions.stream()
			.map(Submission::getId)
			.collect(Collectors.toList());

		Map<String, Map<VoteType, Long>> voteCountsMap = voteRepository.getVoteCountsBySubmissionIds(submissionIds);

		List<SubmissionResponseDTO> dtoList = submissions.stream()
			.map(submission -> {
				Map<VoteType, Long> voteCounts = voteCountsMap.getOrDefault(submission.getId(), Map.of());
				Long disgustingVotes = voteCounts.getOrDefault(VoteType.DISGUSTING, 0L);
				Long geniusVotes = voteCounts.getOrDefault(VoteType.GENIUS, 0L);
				Long lolVotes = voteCounts.getOrDefault(VoteType.LOL, 0L);
				Long totalVotes = disgustingVotes + geniusVotes + lolVotes;

				return SubmissionResponseDTO.of(submission, totalVotes, disgustingVotes, geniusVotes, lolVotes);
			})
			.collect(Collectors.toList());
		return PagedResponseDTO.of(pagingRequest, total, dtoList);
	}

	@Override
	@Transactional(readOnly = true)
	public PagedResponseDTO<SubmissionResponseDTO> getSubmissionsByQuestId(String questId, PagingRequestDTO pagingRequest) {
		long total = submissionRepository.countByQuestId(questId);
		if (total == 0) {
			return PagedResponseDTO.empty(pagingRequest);
		}
		List<Submission> submissions = submissionRepository.findByQuestIdOrderByCreatedAtDesc(
			questId,
			pagingRequest.getOffset(),
			pagingRequest.getLimit()
		);
		List<String> submissionIds = submissions.stream()
			.map(Submission::getId)
			.collect(Collectors.toList());

		Map<String, Map<VoteType, Long>> voteCountsMap = voteRepository.getVoteCountsBySubmissionIds(submissionIds);

		List<SubmissionResponseDTO> dtoList = submissions.stream()
			.map(submission -> {
				Map<VoteType, Long> voteCounts = voteCountsMap.getOrDefault(submission.getId(), Map.of());
				Long disgustingVotes = voteCounts.getOrDefault(VoteType.DISGUSTING, 0L);
				Long geniusVotes = voteCounts.getOrDefault(VoteType.GENIUS, 0L);
				Long lolVotes = voteCounts.getOrDefault(VoteType.LOL, 0L);
				Long totalVotes = disgustingVotes + geniusVotes + lolVotes;

				return SubmissionResponseDTO.of(submission, totalVotes, disgustingVotes, geniusVotes, lolVotes);
			})
			.collect(Collectors.toList());
		return PagedResponseDTO.of(pagingRequest, total, dtoList);
	}

	@Override
	@Transactional(readOnly = true)
	public SubmissionResponseDTO getSubmission(String questId, String submissionId) {
		Submission submission = submissionRepository.findByIdAndQuestId(submissionId, questId)
			.orElseThrow(() -> new NotFoundException("제출을 찾을 수 없습니다."));

		Map<String, Map<VoteType, Long>> voteCountsMap = voteRepository.getVoteCountsBySubmissionIds(List.of(submissionId));
		Map<VoteType, Long> voteCounts = voteCountsMap.getOrDefault(submissionId, Map.of());
		Long disgustingVotes = voteCounts.getOrDefault(VoteType.DISGUSTING, 0L);
		Long geniusVotes = voteCounts.getOrDefault(VoteType.GENIUS, 0L);
		Long lolVotes = voteCounts.getOrDefault(VoteType.LOL, 0L);
		Long totalVotes = disgustingVotes + geniusVotes + lolVotes;

		return SubmissionResponseDTO.of(submission, totalVotes, disgustingVotes, geniusVotes, lolVotes);
	}
}
