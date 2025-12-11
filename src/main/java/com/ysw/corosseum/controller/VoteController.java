package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.vote.VoteRequestDTO;
import com.ysw.corosseum.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "투표 API")
@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

	private final VoteService voteService;

	@Operation(
		summary = "코드에 투표",
		description = "제출된 코드에 대해 투표합니다"
	)
	@PostMapping("/{submissionId}")
	public ResponseEntity<ApiResponse<Void>> vote(
		@PathVariable String submissionId,
		@RequestBody VoteRequestDTO request
	) {
		voteService.vote(submissionId, request.getUserId(), request.getVoteType());
		return ResponseEntity.ok(ApiResponse.empty());
	}
}
