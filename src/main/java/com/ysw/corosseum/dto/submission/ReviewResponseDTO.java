package com.ysw.corosseum.dto.submission;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

	@JsonProperty("readability_score")
	private int readabilityScore;

	@JsonProperty("creativity_score")
	private int creativityScore;

	@JsonProperty("inefficiency_score")
	private int inefficiencyScore;

	@JsonProperty("roast_comment")
	private String comment;
}
