package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.submission.SubmissionRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import com.ysw.corosseum.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

	private final SubmissionService submissionService;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> submitCode(@RequestBody SubmissionRequestDTO request) {
		submissionService.submitCode(
			request.getQuestId(),
			request.getUserId(),
			request.getCode()
		);
		return ResponseEntity.ok(ApiResponse.empty());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<SubmissionResponseDTO>>> getSubmissions(Pageable pageable) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmissions(pageable)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<SubmissionResponseDTO>> getSubmission(@PathVariable String id) {
		return ResponseEntity.ok(ApiResponse.of(submissionService.getSubmission(id)));
	}
}
