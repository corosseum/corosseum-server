package com.ysw.corosseum.service;

import com.ysw.corosseum.dto.auth.GuestAuthResponse;

public interface AuthService {
    GuestAuthResponse issueGuestId();
}
