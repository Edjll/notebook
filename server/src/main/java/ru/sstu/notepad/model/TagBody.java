package ru.sstu.notepad.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagBody {
    private Long id;
    @NotNull(message = "Не заполенено название тега")
    private String name;
}
