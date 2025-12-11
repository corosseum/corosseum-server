package com.ysw.corosseum.service;

import com.ysw.corosseum.domain.type.VoteType;

public interface VoteService {
	void vote(String submissionId, String userId, VoteType voteType);
}
