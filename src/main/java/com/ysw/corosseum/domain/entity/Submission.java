package com.ysw.corosseum.domain.entity;

import com.ysw.corosseum.domain.type.SubmissionStatus;
import com.ysw.corosseum.util.DateUtil;
import com.ysw.corosseum.util.UuidUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "submissions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Submission {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;

    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private String code;

    @Column(name = "readability_score")
    private Integer readabilityScore;

    @Column(name = "creativity_score")
    private Integer creativityScore;

    @Column(name = "inefficiency_score")
    private Integer inefficiencyScore;

    @Column(name = "review_comment", columnDefinition = "TEXT")
    private String reviewComment;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private SubmissionStatus status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

	public static Submission create(Quest quest, String userId, String code) {
		return Submission.builder()
            .id(UuidUtil.generate())
			.quest(quest)
			.userId(userId)
			.code(code)
			.status(SubmissionStatus.PENDING)
			.createdAt(DateUtil.now())
			.build();
	}

    public void updateReview(
        Integer readabilityScore,
        Integer creativityScore,
        Integer inefficiencyScore,
        String reviewComment
    ) {
        this.readabilityScore = readabilityScore;
        this.creativityScore = creativityScore;
        this.inefficiencyScore = inefficiencyScore;
        this.reviewComment = reviewComment;
        this.status = SubmissionStatus.REVIEWED;
        this.updatedAt = DateUtil.now();
    }
}
