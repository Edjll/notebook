package ru.sstu.notepad.model.note;

import lombok.Getter;
import lombok.Setter;
import ru.sstu.notepad.model.priority.PriorityBody;
import ru.sstu.notepad.model.section.SectionBody;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoteBody {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Long priority;

    private PriorityBody priorityBody;

    private Long section;

    private SectionBody sectionBody;
}
