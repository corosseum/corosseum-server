package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.quest.QuestResponseDTO;
import com.ysw.corosseum.service.QuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퀘스트 API")
@RestController
@RequestMapping("/quests")
@RequiredArgsConstructor
public class QuestController {

	private final QuestService questService;

	@Operation(
		summary = "현재 퀘스트 조회",
		description = "오늘 날짜의 활성화된 퀘스트를 조회합니다."
	)
	@GetMapping("/current")
	public ResponseEntity<ApiResponse<QuestResponseDTO>> getCurrentQuest() {
		return ResponseEntity.ok(ApiResponse.of(questService.getCurrentQuest()));
	}
}
