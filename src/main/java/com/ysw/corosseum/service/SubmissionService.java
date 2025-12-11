package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubmissionService {
	void submitCode(String questId, String userId, String code);
	Page<SubmissionResponseDTO> getSubmissions(Pageable pageable);
	SubmissionResponseDTO getSubmission(String id);
}
