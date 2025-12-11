package com.ysw.corosseum.dto.vote;

import com.ysw.corosseum.domain.type.VoteType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "투표 요청")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class VoteRequestDTO {
	@NotBlank
	@Schema(description = "사용자 ID (UUID)")
	private String userId;

	@NotNull
	@Schema(description = "투표 타입")
	private VoteType voteType;
}
