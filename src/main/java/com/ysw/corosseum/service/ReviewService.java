package com.ysw.corosseum.service;

import com.ysw.corosseum.domain.vo.ReviewResult;

public interface ReviewService {
	ReviewResult reviewCode(String questDescription, String code);
}
