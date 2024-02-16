package TeamTwo.TeamTwoProject.security;

import TeamTwo.TeamTwoProject.config.jwt.JwtProperties;
import TeamTwo.TeamTwoProject.entity.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenProvider {
    @Autowired
    JwtProperties jwtProperties;

    // 토큰 발급하는 메소드 만들기
    public String createToken(UserEntity userEntity) {
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey()) // secretKey, 암호화 방식을 설정
                .setSubject(String.valueOf(userEntity.getUserid())) // payload에 들어갈 정보
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    // 토큰 검증하는 메소드 만들기
    public String validateAndGetUserId (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey()) // secretKey 설정
                .parseClaimsJws(token) // 검증할 토큰 설정
                .getBody(); //payload를 get 하는 메소드

        return claims.getSubject();
    }
}
