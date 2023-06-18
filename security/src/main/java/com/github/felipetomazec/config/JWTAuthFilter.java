package com.github.felipetomazec.config;

import com.github.felipetomazec.exceptions.InvalidJwtException;
import com.github.felipetomazec.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.replace("Bearer ", "");

        try {
            var requesterEmail = jwtService.extractEmail(token);
            var isNotAuthenticated = Objects.isNull(SecurityContextHolder.getContext().getAuthentication());

            if (Objects.nonNull(requesterEmail) && isNotAuthenticated) {
                var user = userDetailsService.loadUserByUsername(requesterEmail);

                if (jwtService.isValid(token)) {
                    var authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            Collections.emptySet()
                    );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    filterChain.doFilter(request, response);
                }
            }
        } catch (InvalidJwtException | UsernameNotFoundException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
        }
    }
}
