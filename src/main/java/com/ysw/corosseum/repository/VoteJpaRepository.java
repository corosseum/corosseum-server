package com.ysw.corosseum.repository;

import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface VoteJpaRepository extends JpaRepository<Vote, String> {

	@Query("SELECT v FROM Vote v WHERE v.submission.id = :submissionId AND v.userId = :userId")
	Optional<Vote> findBySubmissionIdAndUserId(String submissionId, String userId);

	@Query("SELECT v.submission.id, v.voteType, COUNT(v) FROM Vote v WHERE v.submission.id IN :submissionIds GROUP BY v.submission.id, v.voteType")
	List<Object[]> countBySubmissionIdsGroupByVoteType(List<String> submissionIds);

	default Map<String, Map<VoteType, Long>> getVoteCountsBySubmissionIds(List<String> submissionIds) {
		if (submissionIds.isEmpty()) {
			return Map.of();
		}
		return countBySubmissionIdsGroupByVoteType(submissionIds).stream()
			.collect(Collectors.groupingBy(
				result -> (String) result[0],
				Collectors.toMap(
					result -> (VoteType) result[1],
					result -> (Long) result[2],
					(a, b) -> a
				)
			));
	}
}
