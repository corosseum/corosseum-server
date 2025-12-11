package com.ysw.corosseum.service.impl;

import com.ysw.corosseum.dto.auth.GuestAuthResponse;
import com.ysw.corosseum.service.AuthService;
import com.ysw.corosseum.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public GuestAuthResponse issueGuestId() {
        return GuestAuthResponse.of(UuidUtil.generate());
    }
}
