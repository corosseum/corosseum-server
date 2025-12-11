package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.vote.VoteRequestDTO;
import com.ysw.corosseum.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

	private final VoteService voteService;

	@PostMapping("/{submissionId}")
	public ResponseEntity<ApiResponse<Void>> vote(@PathVariable String submissionId, @RequestBody VoteRequestDTO request) {
		voteService.vote(submissionId, request.getUserId(), request.getVoteType());
		return ResponseEntity.ok(ApiResponse.empty());
	}
}
