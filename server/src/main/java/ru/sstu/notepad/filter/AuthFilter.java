package ru.sstu.notepad.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.controller.ApiConstants;
import ru.sstu.notepad.service.AuthService;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTH = "Authorization";

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userInBase64 = request.getHeader(HEADER_AUTH);

        if (request.getRequestURI().equals(ApiConstants.USERS + ApiConstants.LOGIN) ||
                userInBase64 != null && authService.validateCredentialsInBase64(userInBase64)) {
            filterChain.doFilter(request, response);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Не удалось авторизоваться!");
        }
    }
}
