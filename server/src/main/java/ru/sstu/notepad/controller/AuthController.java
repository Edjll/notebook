package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sstu.notepad.model.User;
import ru.sstu.notepad.service.AuthService;

@RestController
@RequestMapping(ApiConstants.USERS)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiConstants.LOGIN)
    public ResponseEntity<String> login(@RequestBody User user) {
        return ResponseEntity.ok().body(authService.login(user));
    }
}
