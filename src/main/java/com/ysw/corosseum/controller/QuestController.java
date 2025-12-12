package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
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
	@GetMapping("/today")
	public ResponseEntity<ApiResponse<QuestResponseDTO>> getTodayQuest() {
		return ResponseEntity.ok(ApiResponse.of(questService.getTodayQuest()));
	}

	@Operation(
		summary = "역대 퀘스트 목록 조회",
		description = "역대 퀘스트 목록을 날짜 내림차순으로 페이징하여 조회합니다."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<PagedResponseDTO<QuestResponseDTO>>> getCompletedQuests(
		PagingRequestDTO pagingRequest
	) {
		return ResponseEntity.ok(ApiResponse.of(questService.getCompletedQuests(pagingRequest)));
	}
}
