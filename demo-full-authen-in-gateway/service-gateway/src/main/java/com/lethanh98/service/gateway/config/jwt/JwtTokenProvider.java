package com.lethanh98.service.gateway.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lethanh98.service.gateway.exception.CustomException;
import com.lethanh98.service.gateway.model.ClaimsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire:86400000}")
    private long expireToken; // 1h


    @Value("${security.jwt.token.expire.refresh:86400000}")
    private long expireRefreshToken; // 1h

    private String tokenType = "Bearer";

    @Autowired
    private MyUserDetails myUserDetails;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<Role> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("type", TypeJWT.TOKEN.getValue());
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireToken);
        return regJwt(claims, now, validity);
    }

    public String createRefreshToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("type", TypeJWT.REFRESH_TOKEN.getValue());
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireRefreshToken);
        return regJwt(claims, now, validity);
    }

    private String regJwt(Claims claims, Date now, Date validity) {
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(tokenType + " ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String validateTokenReturnUserName(String token) {
        try {
            Claims claimsJwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            if (claimsJwt.get("type").equals(TypeJWT.TOKEN.getValue())) {
                return claimsJwt.getSubject();
            }
            throw new CustomException("Expired or invalid JWT accessToken", HttpStatus.UNAUTHORIZED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT accessToken", HttpStatus.UNAUTHORIZED);
        }
    }

    public String validateRefreshTokenReturnUserName(String token) {
        try {
            Claims claimsJwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            if (claimsJwt.get("type").equals(TypeJWT.REFRESH_TOKEN.getValue())) {
                return claimsJwt.getSubject();
            }
            throw new CustomException("Expired or invalid JWT RefreshToken", HttpStatus.UNAUTHORIZED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT RefreshToken", HttpStatus.UNAUTHORIZED);
        }
    }

    public ClaimsDTO getClaimsDTOFromToken(String token) {
        try {
            Claims claimsJwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return objectMapper.convertValue(claimsJwt, ClaimsDTO.class);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT RefreshToken", HttpStatus.UNAUTHORIZED);
        }
    }
}
