package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.auth.GuestAuthResponseDTO;

public interface AuthService {
    GuestAuthResponseDTO issueGuestId();
}
