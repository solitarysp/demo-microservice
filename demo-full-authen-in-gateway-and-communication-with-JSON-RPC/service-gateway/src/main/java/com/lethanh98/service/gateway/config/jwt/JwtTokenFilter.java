package com.lethanh98.service.gateway.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lethanh98.service.gateway.config.cachehttp.CachedBodyHttpServletReques;
import com.lethanh98.service.gateway.entity.Request;
import com.lethanh98.service.gateway.exception.CustomException;
import com.lethanh98.service.gateway.response.ErrorTokenRP;
import com.lethanh98.service.gateway.rpc.AuthenServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    AuthenServiceRpc authenServiceRpc;
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // sử dụng CachedBodyHttpServletReques để có thể gọi lại  InputStream nhiều lần được
        CachedBodyHttpServletReques cachedBodyHttpServletRequest =
                new CachedBodyHttpServletReques(httpServletRequest);
        try {

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBodyHttpServletRequest.getCachedBody());
            Request token = new ObjectMapper().readValue(byteArrayInputStream , Request.class);

            if (token != null && Objects.nonNull(token.getAuthentication())) {
                String authentication = authenServiceRpc.info(token.getAuthentication());
                Authentication auth = jwtTokenProvider.getAuthentication(token.getAuthentication());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else {
                SecurityContextHolder.getContext().setAuthentication(null);

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

        filterChain.doFilter(cachedBodyHttpServletRequest, httpServletResponse);

    }

}
