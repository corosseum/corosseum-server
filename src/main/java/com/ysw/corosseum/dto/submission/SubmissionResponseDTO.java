package com.ysw.corosseum.dto.submission;

import com.ysw.corosseum.domain.entity.Submission;
import com.ysw.corosseum.domain.type.SubmissionStatus;
import com.ysw.corosseum.util.DateFormatUtil;
import lombok.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SubmissionResponseDTO {
	private String id;
	private String questId;
	private String userId;
	private String code;
	private Integer readabilityScore;
	private Integer creativityScore;
	private Integer inefficiencyScore;
	private String reviewComment;
	private SubmissionStatus status;
	private String createdAt;

	public static SubmissionResponseDTO of(Submission submission) {
		return SubmissionResponseDTO.builder()
			.id(submission.getId())
			.questId(submission.getQuest().getId())
			.userId(submission.getUserId())
			.code(submission.getCode())
			.readabilityScore(submission.getReadabilityScore())
			.creativityScore(submission.getCreativityScore())
			.inefficiencyScore(submission.getInefficiencyScore())
			.reviewComment(submission.getReviewComment())
			.status(submission.getStatus())
			.createdAt(DateFormatUtil.format(submission.getCreatedAt()))
			.build();
	}
}
