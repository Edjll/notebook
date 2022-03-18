package ru.sstu.notepad.model.record;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
public abstract class RecordBodyCommon {
    private Long id;

    @NotBlank(message = "Не заполенен заголовок заметки")
    private String title;

    @NotBlank(message = "Не заполенено описание заметки")
    private String description;

    @NotNull(message = "Не заполенена дата начала заметки")
    private LocalDateTime startDate;

    @NotNull(message = "Не заполенена дата окончания заметки")
    private LocalDateTime endDate;

    @NotNull(message = "Не заполенен приориет заметки")
    private PriorityType priority;

    private boolean statusActive;
}
