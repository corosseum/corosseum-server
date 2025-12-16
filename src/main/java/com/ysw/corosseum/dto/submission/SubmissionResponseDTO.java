package com.ysw.corosseum.dto.submission;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.domain.type.SubmissionStatus;
import com.ysw.corosseum.util.DateFormatUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "코드 제출 응답")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SubmissionResponseDTO {
	@Schema(description = "제출 ID")
	private String id;
	
	@Schema(description = "퀘스트 ID")
	private String questId;
	
	@Schema(description = "퀘스트 설명")
	private String questDescription;
	
	@Schema(description = "사용자 ID")
	private String userId;
	
	@Schema(description = "제출된 코드")
	private String code;
	
	@Schema(description = "가독성 점수 (광기)")
	private Integer readabilityScore;
	
	@Schema(description = "창의성 점수 (예술성)")
	private Integer creativityScore;
	
	@Schema(description = "비효율성 점수 (파괴력)")
	private Integer inefficiencyScore;
	
	@Schema(description = "리뷰 코멘트")
	private String reviewComment;
	
	@Schema(description = "제출 상태")
	private SubmissionStatus status;
	
	@Schema(description = "생성 일시", example = "2024-01-01 00:00:00")
	private String createdAt;

	@Schema(description = "총 투표 수")
	private Long totalVotes;

	@Schema(description = "🤮 토나와 투표 수")
	private Long disgustingVotes;

	@Schema(description = "🤩 천재다 투표 수")
	private Long geniusVotes;

	@Schema(description = "🤣 빵터짐 투표 수")
	private Long lolVotes;

	public static SubmissionResponseDTO of(
		Submission submission,
		Long totalVotes,
		Long disgustingVotes,
		Long geniusVotes,
		Long lolVotes
	) {
		return SubmissionResponseDTO.builder()
			.id(submission.getId())
			.questId(submission.getQuest().getId())
			.questDescription(submission.getQuest().getDescription())
			.userId(submission.getUserId())
			.code(submission.getCode())
			.readabilityScore(submission.getReadabilityScore())
			.creativityScore(submission.getCreativityScore())
			.inefficiencyScore(submission.getInefficiencyScore())
			.reviewComment(submission.getReviewComment())
			.status(submission.getStatus())
			.createdAt(DateFormatUtil.format(submission.getCreatedAt()))
			.totalVotes(totalVotes)
			.disgustingVotes(disgustingVotes)
			.geniusVotes(geniusVotes)
			.lolVotes(lolVotes)
			.build();
	}
}
