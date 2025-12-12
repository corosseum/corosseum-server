package com.ysw.corosseum.dto.quest;

import com.ysw.corosseum.domain.entity.Quest;
import com.ysw.corosseum.domain.type.QuestStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(description = "퀘스트 응답")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class QuestResponseDTO {
	@Schema(description = "퀘스트 ID")
    private String id;
    
	@Schema(description = "퀘스트 설명")
    private String description;
    
	@Schema(description = "퀘스트 날짜", example = "2024-01-01")
    private LocalDate questDate;

    public static QuestResponseDTO of(Quest quest) {
        return QuestResponseDTO.builder()
            .id(quest.getId())
            .description(quest.getDescription())
            .questDate(quest.getQuestDate())
            .build();
    }
}
