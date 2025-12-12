package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.leaderboard.LeaderboardResponseDTO;
import com.ysw.corosseum.service.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "리더보드 API")
@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardService leaderboardService;

	@Operation(
		summary = "오늘의 리더보드 조회",
		description = "오늘 날짜의 제출 코드들을 투표 수 기준으로 정렬하여 조회합니다."
	)
	@GetMapping("/daily")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getDailyLeaderboard() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getDailyLeaderboard()));
	}

	@Operation(
		summary = "어제의 TOP 10 조회",
		description = "어제 날짜의 제출 코드 중 투표 수가 가장 많은 상위 10개를 조회합니다."
	)
	@GetMapping("/yesterday-top")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getYesterdayTopLeaderboard() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getYesterdayTopLeaderboard()));
	}

	@Operation(
		summary = "명예의 전당 조회",
		description = "각 투표 타입별로 가장 많은 표를 받은 코드들을 조회합니다."
	)
	@GetMapping("/hall-of-fame")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getHallOfFame() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getHallOfFame()));
	}
}
