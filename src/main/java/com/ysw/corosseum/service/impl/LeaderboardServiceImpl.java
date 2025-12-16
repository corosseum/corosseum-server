package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.dto.leaderboard.LeaderboardResponseDTO;
import com.ysw.corosseum.repository.impl.SubmissionRepository;
import com.ysw.corosseum.repository.impl.VoteRepository;
import com.ysw.corosseum.service.LeaderboardService;
import com.ysw.corosseum.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

	private final SubmissionRepository submissionRepository;
	private final VoteRepository voteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<LeaderboardResponseDTO> getDailyLeaderboard() {
		LocalDate today = DateUtil.today();
		List<Submission> submissions = submissionRepository.findByQuestDate(today);
		return buildLeaderboard(submissions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LeaderboardResponseDTO> getYesterdayTopLeaderboard() {
		LocalDate yesterday = DateUtil.today().minusDays(1);
		List<Submission> submissions = submissionRepository.findByQuestDate(yesterday);
		return buildLeaderboard(submissions, 10);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LeaderboardResponseDTO> getHallOfFame() {
		List<Submission> topSubmissions = submissionRepository.findTop10ByTotalVotes();
		return buildLeaderboard(topSubmissions, 10);
	}

	private List<LeaderboardResponseDTO> buildLeaderboard(List<Submission> submissions) {
		return buildLeaderboard(submissions, submissions.size());
	}

	private List<LeaderboardResponseDTO> buildLeaderboard(List<Submission> submissions, int limit) {
		if (submissions.isEmpty()) {
			return List.of();
		}

		List<String> submissionIds = submissions.stream()
			.map(Submission::getId)
			.collect(Collectors.toList());

		Map<String, Map<VoteType, Long>> voteCountsMap = voteRepository.getVoteCountsBySubmissionIds(submissionIds);

		return submissions.stream()
			.map(submission -> {
				Map<VoteType, Long> voteCounts = voteCountsMap.getOrDefault(submission.getId(), Map.of());
				Long disgustingVotes = voteCounts.getOrDefault(VoteType.DISGUSTING, 0L);
				Long geniusVotes = voteCounts.getOrDefault(VoteType.GENIUS, 0L);
				Long lolVotes = voteCounts.getOrDefault(VoteType.LOL, 0L);
				Long totalVotes = disgustingVotes + geniusVotes + lolVotes;

				return LeaderboardResponseDTO.of(submission, totalVotes, disgustingVotes, geniusVotes, lolVotes);
			})
			.sorted((a, b) -> Long.compare(b.getTotalVotes(), a.getTotalVotes()))
			.limit(limit)
			.collect(Collectors.toList());
	}
}
