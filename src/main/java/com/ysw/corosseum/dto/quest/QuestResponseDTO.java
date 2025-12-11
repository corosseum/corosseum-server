package com.ysw.corosseum.dto.quest;

import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.type.QuestStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class QuestResponseDTO {
    private String id;
    private String description;
    private LocalDate questDate;
    private QuestStatus status;

    public static QuestResponseDTO of(Quest quest) {
        return QuestResponseDTO.builder()
            .id(quest.getId())
            .description(quest.getDescription())
            .questDate(quest.getQuestDate())
            .status(quest.getStatus())
            .build();
    }

    public static QuestResponseDTO of(
        String id,
        String description,
        LocalDate questDate,
        QuestStatus status
    ) {
        return QuestResponseDTO.builder()
            .id(id)
            .description(description)
            .questDate(questDate)
            .status(status)
            .build();
    }
}
