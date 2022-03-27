package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sstu.notepad.model.note.NoteBody;
import ru.sstu.notepad.model.task.TaskBody;

import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class ExportService {

    private static final String NOTE_FORMAT = "%20s %20s %20s %20s %20s %20s %20s %n";
    private static final String TASK_FORMAT = "%20s %20s %20s %20s %20s %20s %n";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy");

    private final NoteService noteService;
    private final TaskService taskService;

    public byte[] exportNotes() {
        Formatter formatter = new Formatter();
        formatter.format(NOTE_FORMAT, "ID", "Заголовок", "Описание", "Дата создания", "Дата изменения", "Приоритет", "Раздел");
        for (NoteBody noteBody : noteService.getAll(null, null)) {
            formatter.format(
                    NOTE_FORMAT,
                    noteBody.getId(),
                    noteBody.getTitle(),
                    noteBody.getDescription(),
                    noteBody.getCreatedDate().format(DATE_TIME_FORMATTER),
                    noteBody.getModifiedDate() == null ? "-" : noteBody.getModifiedDate().format(DATE_TIME_FORMATTER),
                    noteBody.getPriorityBody().getName(),
                    noteBody.getSectionBody().getName()
            );
        }
        return formatter.toString().getBytes();
    }

    public byte[] exportTasks() {
        Formatter formatter = new Formatter();
        formatter.format(TASK_FORMAT, "ID", "Заголовок", "Описание", "Дата начала", "Дата окончания", "Выполнена");
        for (TaskBody taskBody : taskService.getAll()) {
            formatter.format(
                    TASK_FORMAT,
                    taskBody.getId(),
                    taskBody.getTitle(),
                    taskBody.getDescription(),
                    taskBody.getStartDate().format(DATE_TIME_FORMATTER),
                    taskBody.getEndDate().format(DATE_TIME_FORMATTER),
                    taskBody.isCompleted()
            );
        }
        return formatter.toString().getBytes();
    }
}
