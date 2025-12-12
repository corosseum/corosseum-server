package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.common.PagedResponseDTO;
import com.ysw.corosseum.dto.common.PagingRequestDTO;
import com.ysw.corosseum.dto.submission.SubmissionResponseDTO;

public interface SubmissionService {
	void submitCode(String questId, String userId, String code);
	PagedResponseDTO<SubmissionResponseDTO> getSubmissions(PagingRequestDTO pagingRequest);
	PagedResponseDTO<SubmissionResponseDTO> getSubmissionsByQuestId(String questId, PagingRequestDTO pagingRequest);
	SubmissionResponseDTO getSubmission(String questId, String submissionId);
}
