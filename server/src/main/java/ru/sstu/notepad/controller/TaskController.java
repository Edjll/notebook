package ru.sstu.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.sstu.notepad.model.task.TaskBody;
import ru.sstu.notepad.model.task.TaskStatus;
import ru.sstu.notepad.service.TaskService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.TASKS)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskBody getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping(ApiConstants.TASKS_ACTUAL)
    public List<TaskBody> getActualTasks() {
        return taskService.getActualTasks();
    }

    @GetMapping(ApiConstants.TASKS_COMPLETED)
    public List<TaskBody> getCompletedTasks() {
        return taskService.getCompletedTasks();
    }

    @GetMapping(ApiConstants.TASKS_EXPIRED)
    public List<TaskBody> getExpiredTasks() {
        return taskService.getExpiredTasks();
    }

    @GetMapping(ApiConstants.RECORDS_GET_ALL)
    public List<TaskBody> getAll() {
        return taskService.getAll();
    }

    @PostMapping
    public void save(@RequestBody @Valid TaskBody body) {
        taskService.save(body);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestBody TaskStatus taskStatus) {
        taskService.updateStatus(id, taskStatus);
    }

    @GetMapping(ApiConstants.RECORDS_FIND)
    public List<TaskBody> find(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
       return taskService.findByDate(startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}