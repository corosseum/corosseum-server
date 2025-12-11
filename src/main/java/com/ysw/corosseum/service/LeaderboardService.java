package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.leaderboard.LeaderboardResponseDTO;

import java.util.List;

public interface LeaderboardService {
	List<LeaderboardResponseDTO> getDailyLeaderboard();
	List<LeaderboardResponseDTO> getYesterdayTop3();
	List<LeaderboardResponseDTO> getHallOfFame();
}
