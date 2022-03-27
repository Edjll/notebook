package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Task;
import ru.sstu.notepad.model.task.TaskBody;
import ru.sstu.notepad.model.task.TaskStatus;
import ru.sstu.notepad.repository.TaskRepository;
import ru.sstu.notepad.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.sstu.notepad.mapper.TaskMapper.TASK_MAPPER;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    @Transactional
    public TaskBody getTask(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TASK_MAPPER.toDto(task);
    }

    @Transactional
    public void save(TaskBody body) {
        Task task;
        if (body.getId() != null) {
            Optional<Task> recordEntity = repository.findById(body.getId());
            if (recordEntity.isPresent()) {
                task = TASK_MAPPER.toEntity(body, recordEntity.get());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Не найдена заметка с id = %d", body.getId()));
            }
        } else {

            task = TASK_MAPPER.toEntity(body);
        }
        repository.save(task);
    }

    @Transactional
    public void updateStatus(Long id, TaskStatus taskStatus) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        task.setCompleted(taskStatus.isCompleted());
        repository.save(task);
    }

    @Transactional
    public List<TaskBody> getAll() {
        return repository.findAll().stream()
                .map(TASK_MAPPER::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    public boolean hasIntersection(TaskBody recordBodyToSave) {
        return repository.findAll().stream().anyMatch(record ->
                !record.getId().equals(recordBodyToSave.getId())
                    && (DateUtils.isIncludedInPeriods(
                        record.getStartDate(),
                        record.getEndDate(),
                        recordBodyToSave.getStartDate(),
                        recordBodyToSave.getEndDate()
                    )
                    || DateUtils.isIncludedInPeriods(
                        recordBodyToSave.getStartDate(),
                        recordBodyToSave.getEndDate(),
                        record.getStartDate(),
                        record.getEndDate()
                    ))
        );
    }

    public List<TaskBody> getActualTasks() {
        return TASK_MAPPER.toDtoList(repository.getActualTasks(LocalDateTime.now()));
    }

    public List<TaskBody> getCompletedTasks() {
        return TASK_MAPPER.toDtoList(repository.getCompletedTasks());
    }

    public List<TaskBody> getExpiredTasks() {
        return TASK_MAPPER.toDtoList(repository.getExpiredTasks(LocalDateTime.now()));
    }
}