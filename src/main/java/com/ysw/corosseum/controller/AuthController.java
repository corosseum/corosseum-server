package com.ysw.corosseum.controller;

import com.ysw.corosseum.common.ApiResponse;
import com.ysw.corosseum.dto.auth.GuestAuthResponseDTO;
import com.ysw.corosseum.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
        summary = "게스트 사용자 식별자 발급",
        description = "회원가입 없이 임시 UUID를 발급받습니다."
    )
    @PostMapping("/guest")
    public ResponseEntity<ApiResponse<GuestAuthResponseDTO>> issueGuestToken() {
        return ResponseEntity.ok(ApiResponse.of(authService.issueGuestId()));
    }
}
