package ru.sstu.notepad.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.model.User;

import java.util.Base64;

@Service
public class AuthService {

    @Value("${user.username}")
    private String username;

    @Value("${user.password}")
    private String password;

    public boolean validateCredentialsInBase64(String userInBase64) {
        try {
            String[] credentials = new String(Base64.getDecoder().decode(userInBase64)).split(":");
            return credentials.length == 2
                    && username.equals(credentials[0])
                    && password.equals(credentials[1]);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String login(User user) {
        if (!username.equals(user.getUsername()) || !password.equals(user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
