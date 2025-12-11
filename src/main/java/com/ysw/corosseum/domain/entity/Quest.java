package com.ysw.corosseum.domain.entity;

import com.ysw.corosseum.domain.type.QuestStatus;
import com.ysw.corosseum.util.DateUtil;
import com.ysw.corosseum.util.UuidUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "quests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Quest {

	@Id
	@Column(name = "id", length = 36)
	private String id;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "quest_date", nullable = false)
	private LocalDate questDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private QuestStatus status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at")
	private Instant updatedAt;

	public static Quest create(String description, LocalDate questDate) {
		return Quest.builder()
			.id(UuidUtil.generate())
			.description(description)
			.questDate(questDate)
			.status(QuestStatus.INACTIVE)
			.createdAt(DateUtil.now())
			.build();
	}

	public void activate() {
		this.status = QuestStatus.ACTIVATED;
		this.updatedAt = DateUtil.now();
	}

	public void deactivate() {
		this.status = QuestStatus.INACTIVE;
		this.updatedAt = DateUtil.now();
	}
}
