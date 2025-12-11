package com.ysw.corosseum.dto.leaderboard;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "리더보드 응답")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class LeaderboardResponseDTO {
	@Schema(description = "제출 정보")
    private SubmissionResponseDTO submission;
    
	@Schema(description = "총 투표 수")
    private Long totalVotes;
    
	@Schema(description = "🤮 토나와 투표 수")
    private Long disgustingVotes;
    
	@Schema(description = "🤩 천재다 투표 수")
    private Long geniusVotes;
    
	@Schema(description = "🤣 빵터짐 투표 수")
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
