package TeamTwo.TeamTwoProject.security;

import TeamTwo.TeamTwoProject.service.user.TokenBlacklistService;
import TeamTwo.TeamTwoProject.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    TokenBlacklistService tokenBlacklistService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) {
                if (tokenBlacklistService.isBlacklisted(token)) {
                    throw new ServletException("블랙리스트에 있는 토큰입니다.");
                }

                String userId = tokenProvider.validateAndGetUserId(token);

                Authentication authentication = new UsernamePasswordAuthenticationToken(String.valueOf(userId), null, AuthorityUtils.NO_AUTHORITIES);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("auth error {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    public String getBearerToken (HttpServletRequest request) {
        String bearer =  request.getHeader("Authorization");

        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {

            return bearer.substring(7);
        }
        return null;
    }

}
