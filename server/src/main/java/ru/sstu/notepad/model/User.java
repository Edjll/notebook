package ru.sstu.notepad.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {
    @NotBlank(message = "Необходимо заполнить логин")
    private String username;
    @NotBlank(message = "Необходимо заполнить пароль")
    private String password;
}
