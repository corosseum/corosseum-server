package com.ysw.corosseum.domain.entity;

import com.ysw.corosseum.domain.type.VoteType;
import com.ysw.corosseum.util.DateUtil;
import com.ysw.corosseum.util.UuidUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "votes", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"submission_id", "user_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Vote {

	@Id
	@Column(name = "id", length = 36)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "submission_id", nullable = false)
	private Submission submission;

	@Column(name = "user_id", nullable = false, length = 36)
	private String userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "vote_type", nullable = false, length = 20)
	private VoteType voteType;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	public static Vote create(Submission submission, String userId, VoteType voteType) {
		return Vote.builder()
			.id(UuidUtil.generate())
			.submission(submission)
			.userId(userId)
			.voteType(voteType)
			.createdAt(DateUtil.now())
			.build();
	}

	public void changeVoteType(VoteType voteType) {
		this.voteType = voteType;
		this.updatedAt = DateUtil.now();
	}
}
