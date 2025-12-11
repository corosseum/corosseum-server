package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.leaderboard.LeaderboardResponseDTO;
import com.ysw.corosseum.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

	private final LeaderboardService leaderboardService;

	@GetMapping("/daily")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getDailyLeaderboard() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getDailyLeaderboard()));
	}

	@GetMapping("/yesterday-top3")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getYesterdayTop3() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getYesterdayTop3()));
	}

	@GetMapping("/hall-of-fame")
	public ResponseEntity<ApiResponse<List<LeaderboardResponseDTO>>> getHallOfFame() {
		return ResponseEntity.ok(ApiResponse.of(leaderboardService.getHallOfFame()));
	}
}
