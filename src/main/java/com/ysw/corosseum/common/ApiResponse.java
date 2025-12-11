package com.ysw.corosseum.common;

import lombok.*;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	private String timestamp;
	private String message;
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
