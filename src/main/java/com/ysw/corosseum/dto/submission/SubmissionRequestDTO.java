package com.ysw.corosseum.dto.submission;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "코드 제출 요청")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SubmissionRequestDTO {
	@NotBlank
	@Schema(description = "퀘스트 ID")
	private String questId;

	@NotBlank
	@Schema(description = "사용자 ID (UUID)")
	private String userId;

	@NotBlank
	@Schema(description = "제출할 코드")
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
