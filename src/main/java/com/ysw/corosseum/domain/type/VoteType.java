package com.ysw.corosseum.domain.type;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	description = "투표 타입: DISGUSTING(🤮 토나와), GENIUS(🤩 천재다), LOL(🤣 빵터짐)",
	allowableValues = {"DISGUSTING", "GENIUS", "LOL"},
	example = "GENIUS"
)
public enum VoteType {
	DISGUSTING,
	GENIUS,
	LOL
}
