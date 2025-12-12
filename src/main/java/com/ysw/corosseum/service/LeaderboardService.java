package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.leaderboard.LeaderboardResponseDTO;

import java.util.List;

public interface LeaderboardService {
	List<LeaderboardResponseDTO> getDailyLeaderboard();
	List<LeaderboardResponseDTO> getYesterdayTopLeaderboard();
	List<LeaderboardResponseDTO> getHallOfFame();
}
