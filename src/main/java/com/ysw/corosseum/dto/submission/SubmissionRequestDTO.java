package com.ysw.corosseum.dto.submission;

import lombok.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SubmissionRequestDTO {
	private String questId;
	private String userId;
	private String code;

	public static SubmissionRequestDTO of(
		String questId,
		String userId,
		String code
	) {
		return SubmissionRequestDTO.builder()
			.questId(questId)
			.userId(userId)
			.code(code)
			.build();
	}
}
