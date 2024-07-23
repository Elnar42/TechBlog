package com.techblog.blogtech.Config;

import com.techblog.blogtech.domain.Token;
import com.techblog.blogtech.repository.TokenRepository;
import com.techblog.blogtech.services.AuthorService;
import com.techblog.blogtech.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AuthorService authorService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        jwtService.extractAllClaims(token);
        String email = jwtService.extractUsername();
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Token> byToken = tokenRepository.findByToken(token);
            if (byToken.isPresent()) {
                Token accessToken = byToken.get();
                if (accessToken.isExpired() || accessToken.isRevoked()) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            UserDetails userDetails = authorService.loadUserByUserEmail(email);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }

        filterChain.doFilter(request, response);
    }
}
