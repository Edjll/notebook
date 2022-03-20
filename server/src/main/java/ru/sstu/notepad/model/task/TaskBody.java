package ru.sstu.notepad.model.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.sstu.notepad.validation.record.record.RecordConstraint;
import ru.sstu.notepad.validation.record.record.RecordDateValidatable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@RecordConstraint
@JsonIgnoreProperties({"correctOrderDateValid"})
public class TaskBody implements RecordDateValidatable {

    private Long id;

    @NotBlank(message = "Не заполенен заголовок заметки")
    private String title;

    @NotBlank(message = "Не заполенено описание заметки")
    private String description;

    @NotNull(message = "Не заполенена дата начала заметки")
    private LocalDateTime startDate;

    @NotNull(message = "Не заполенена дата окончания заметки")
    private LocalDateTime endDate;

    private boolean completed = false;
}
