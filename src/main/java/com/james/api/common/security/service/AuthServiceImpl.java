package com.james.api.common.security.service;

import com.james.api.common.component.MessengerVo;
import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;
import com.james.api.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;


    public Optional<User> findUserByUsername(String username) {
        return repo.findUserByUsername(username);
    }

    public MessengerVo login(UserDto param) {

        boolean flag = repo.findUserByUsername(param.getUsername()).get().getPassword().equals(param.getPassword());

        return MessengerVo.builder()
                .message(flag? "Success" : "failure")
                .accessToken(flag? createToken(param) : "none")
                .build();}

    @Override
    public String createToken(UserDto dto) {

        Claims claims = (Claims) Jwts.claims();
        claims.put("username",dto.getUsername());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenValid = now.plusSeconds(24*60*60*1000);
        // ^ 발급받은 token이 유요한 시간을 지정해줌 (24*60*60*1000은 24시간)


        String token = Jwts.builder()
                .claim("iss","james.co.kr")
                .claim("sub","User Auth")
                .claim("exp",tokenValid)
                .claim("userId",dto.getId())
                .claim("username",dto.getUsername())
                .claim("job","admin")
                .compact();

        log.info(token);

        return token;
    }
}
