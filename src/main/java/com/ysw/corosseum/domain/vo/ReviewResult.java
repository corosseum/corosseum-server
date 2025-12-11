package com.ysw.corosseum.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResult {
	private Integer readabilityScore;
	private Integer creativityScore;
	private Integer inefficiencyScore;
	private String comment;
}
