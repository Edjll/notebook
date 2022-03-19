package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Record;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByStatusActive(boolean statusActive);
    List<Record> findAllByStartDateIsAfterAndEndDateIsBefore(LocalDateTime startDate, LocalDateTime endDate);
}
