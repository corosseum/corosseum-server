package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.dto.auth.GuestAuthResponseDTO;
import com.ysw.corosseum.service.AuthService;
import com.ysw.corosseum.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public GuestAuthResponseDTO issueGuestId() {
        return GuestAuthResponseDTO.of(UuidUtil.generate());
    }
}
