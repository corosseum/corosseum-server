package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.domain.vo.ReviewResult;
import com.ysw.corosseum.dto.submission.ReviewResponseDTO;
import com.ysw.corosseum.infra.ReviewClient;
import com.ysw.corosseum.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewClient reviewClient;

	@Override
	public ReviewResult reviewCode(String code) {
		ReviewResponseDTO response = reviewClient.reviewCode(code);
		
		return new ReviewResult(
			response.getReadabilityScore(),
			response.getCreativityScore(),
			response.getInefficiencyScore(),
			response.getComment()
		);
	}
}
