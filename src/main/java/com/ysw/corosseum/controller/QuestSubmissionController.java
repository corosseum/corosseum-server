package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import com.ysw.corosseum.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "코드 제출 및 조회 API")
@RestController
@RequestMapping("/quests/{questId}/submissions")
@RequiredArgsConstructor
public class QuestSubmissionController {

	private final SubmissionService submissionService;

	@Operation(
		summary = "코드 제출",
		description = "퀘스트에 대한 코드를 제출합니다. 하루에 한 번만 제출 가능합니다."
	)
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> submitCode(
		@PathVariable String questId,
		@RequestBody SubmissionRequestDTO request
	) {
		submissionService.submitCode(
			questId,
			request.getUserId(),
			request.getCode()
		);
		return ResponseEntity.ok(ApiResponse.empty());
	}

	@Operation(
		summary = "특정 퀘스트의 제출본 목록 조회",
		description = "특정 퀘스트에 제출된 코드를 등록 최신순으로 페이징하여 조회합니다."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<PagedResponseDTO<SubmissionResponseDTO>>> getSubmissionsByQuestId(
		@PathVariable String questId,
		PagingRequestDTO pagingRequest
	) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmissionsByQuestId(questId, pagingRequest)));
	}

	@Operation(
		summary = "제출본 상세 조회",
		description = "특정 제출본의 상세 정보를 조회합니다."
	)
	@GetMapping("/{submissionId}")
	public ResponseEntity<ApiResponse<SubmissionResponseDTO>> getSubmission(
		@PathVariable String questId,
		@PathVariable String submissionId
	) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmission(questId, submissionId)));
	}
}
