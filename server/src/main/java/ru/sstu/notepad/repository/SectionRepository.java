package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
