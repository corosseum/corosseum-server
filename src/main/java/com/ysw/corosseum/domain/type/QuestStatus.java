package com.ysw.corosseum.domain.type;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	description = "퀘스트 상태: INACTIVE(비활성), ACTIVATED(활성화), DELETED(삭제됨)",
	allowableValues = {"INACTIVE", "ACTIVATED", "DELETED"},
	example = "ACTIVATED"
)
public enum QuestStatus {
	INACTIVE,
	ACTIVATED,
	DELETED
}
