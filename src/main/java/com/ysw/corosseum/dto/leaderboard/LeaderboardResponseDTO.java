package com.ysw.corosseum.dto.leaderboard;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import lombok.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class LeaderboardResponseDTO {
    private SubmissionResponseDTO submission;
    private Long totalVotes;
    private Long disgustingVotes;
    private Long geniusVotes;
    private Long lolVotes;

    public static LeaderboardResponseDTO of(
        Submission submission,
        Long totalVotes,
        Long disgustingVotes,
        Long geniusVotes,
        Long lolVotes
    ) {
        return LeaderboardResponseDTO.builder()
            .submission(SubmissionResponseDTO.of(submission))
            .totalVotes(totalVotes)
            .disgustingVotes(disgustingVotes)
            .geniusVotes(geniusVotes)
            .lolVotes(lolVotes)
            .build();
    }
}
