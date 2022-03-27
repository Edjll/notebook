package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
