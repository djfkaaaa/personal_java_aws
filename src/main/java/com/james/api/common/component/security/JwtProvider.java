package com.james.api.common.component.security;

import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;
import com.james.api.user.repository.UserRepository;
import com.james.api.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Component
public class JwtProvider {

    @Value("${jwt.iss}")
    private String issuer;
    private final SecretKey secretKey;
    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);

    public JwtProvider(@Value("${jwt.secret}") String secretKey){
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));

    }

    public String createToken(UserDto user){

        String token = Jwts.builder()
                .issuer(issuer)
                .signWith(secretKey)
                .expiration(Date.from(expiredDate))
                .subject("james")
                .claim("username",user.getUsername())
                .claim("job",user.getJob())
                .claim("userId",user.getId())
                .compact();
        log.info("로그인 성공으로 발급된 토큰" + token);
        return token;
    }

    public String extractTokenFromheader(HttpServletRequest request) {
        log.info("프론트에서 넘어온 리퀘스트 : {}" , request.getServletPath());
        String bearerToken = request.getHeader("Authorization");
        log.info("프론트에서 넘어온 토큰 : {}" , bearerToken);
        return bearerToken != null && bearerToken.startsWith("Bearer") ? bearerToken.substring(7) : "undefined";
    }

    public void printToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("token header : " + header );
        log.info("token payload : " + payload );

    }

    public Claims getToken(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


}
