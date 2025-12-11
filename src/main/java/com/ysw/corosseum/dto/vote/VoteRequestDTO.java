package com.ysw.corosseum.dto.vote;

import com.ysw.corosseum.domain.type.VoteType;
import lombok.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class VoteRequestDTO {
	private String userId;
	private VoteType voteType;
}
