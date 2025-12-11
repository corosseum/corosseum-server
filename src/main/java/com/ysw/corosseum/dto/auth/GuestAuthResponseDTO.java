package com.ysw.corosseum.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게스트 인증 응답")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GuestAuthResponseDTO {
	@Schema(description = "발급된 게스트 UUID")
    private String uuid;

    public static GuestAuthResponseDTO of(String uuid) {
        return new GuestAuthResponseDTO(uuid);
    }
}
