package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
