package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.quest.QuestResponseDTO;
import com.ysw.corosseum.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quests")
@RequiredArgsConstructor
public class QuestController {

	private final QuestService questService;

	@GetMapping("/current")
	public ResponseEntity<ApiResponse<QuestResponseDTO>> getCurrentQuest() {
		return ResponseEntity.ok(ApiResponse.of(questService.getCurrentQuest()));
	}
}
