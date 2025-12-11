package com.ysw.corosseum.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Instant;

@Schema(description = "API 공통 응답 형식")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	@Schema(description = "응답 시간 (ISO 8601 형식)", example = "2024-01-01T00:00:00Z")
	private String timestamp;
	
	@Schema(description = "응답 메시지")
	private String message;
	
	@Schema(description = "응답 데이터")
	private T data;

	public static <T> ApiResponse<T> of(T data) {
		return new ApiResponse<>(
			String.valueOf(Instant.now()),
			null,
			data
		);
	}

	public static <T> ApiResponse<T> of(String message) {
		return new ApiResponse<>(
			String.valueOf(Instant.now()),
			message,
			null
		);
	}

	public static <T> ApiResponse<T> of(String message, T data) {
		return new ApiResponse<>(
			String.valueOf(Instant.now()),
			message,
			data
		);
	}

	public static <T> ApiResponse<T> empty() {
		return new ApiResponse<>(
			String.valueOf(Instant.now()),
			null,
			null
		);
	}
}
