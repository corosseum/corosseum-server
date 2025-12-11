package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.submission.SubmissionRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import com.ysw.corosseum.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "코드 제출 및 조회 API")
@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

	private final SubmissionService submissionService;

	@Operation(
		summary = "코드 제출",
		description = "퀘스트에 대한 코드를 제출합니다. 하루에 한 번만 제출 가능합니다."
	)
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> submitCode(
		@RequestBody SubmissionRequestDTO request
	) {
		submissionService.submitCode(
			request.getQuestId(),
			request.getUserId(),
			request.getCode()
		);
		return ResponseEntity.ok(ApiResponse.empty());
	}

	@Operation(
		summary = "제출본 목록 조회",
		description = "페이징된 제출본 목록을 조회합니다."
	)
	@GetMapping
	public ResponseEntity<ApiResponse<Page<SubmissionResponseDTO>>> getSubmissions(
		Pageable pageable
	) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmissions(pageable)));
	}

	@Operation(
		summary = "제출본 상세 조회",
		description = "특정 제출본의 상세 정보를 조회합니다."
	)
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<SubmissionResponseDTO>> getSubmission(
		@PathVariable String id
	) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmission(id)));
	}
}
