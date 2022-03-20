package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByStartDateIsAfterAndEndDateIsBefore(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select task from Task task where task.completed = false and :currentDate <= task.endDate")
    List<Task> getActualTasks(@Param("currentDate") LocalDateTime date);

    @Query("select task from Task task where task.completed = true")
    List<Task> getCompletedTasks();

    @Query("select task from Task task where task.completed = false and :currentDate > task.endDate")
    List<Task> getExpiredTasks(@Param("currentDate") LocalDateTime date);
}
