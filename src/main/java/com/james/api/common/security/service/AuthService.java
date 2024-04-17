package com.james.api.common.security.service;

import com.james.api.common.component.MessengerVo;
import com.james.api.user.model.UserDto;

public interface AuthService {

    MessengerVo login(UserDto param);
    String createToken(UserDto dto);
}
