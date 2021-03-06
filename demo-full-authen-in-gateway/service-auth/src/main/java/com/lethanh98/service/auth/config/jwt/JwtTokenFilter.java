package com.lethanh98.service.auth.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lethanh98.service.auth.exception.CustomException;
import com.lethanh98.service.auth.response.ErrorTokenRP;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && Objects.nonNull(jwtTokenProvider.validateTokenReturnUserName(token))) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (CustomException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorTokenRP errorTokenRP = new ErrorTokenRP();

            errorTokenRP.setStatus(ex.getHttpStatus().value());
            errorTokenRP.setMsgError(Collections.singletonList(ex.getMessage()));

            httpServletResponse.setStatus(errorTokenRP.getStatus());
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");

            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorTokenRP));

            httpServletResponse.getWriter().flush();

            //  httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
