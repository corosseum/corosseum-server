package com.ysw.corosseum.domain.type;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	description = "제출 상태: PENDING(대기중), REVIEWED(리뷰 완료), DELETED(삭제됨)",
	allowableValues = {"PENDING", "REVIEWED", "DELETED"},
	example = "REVIEWED"
)
public enum SubmissionStatus {
	PENDING,
	REVIEWED,
	DELETED
}
