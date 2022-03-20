package ru.sstu.notepad.model.tag;

import lombok.Data;
import ru.sstu.notepad.model.task.TaskBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public abstract class TagBodyCommon {
    private Long id;
    @NotNull(message = "Не заполенено название тега")
    private String name;

    private List<TaskBody> tasks;
}
