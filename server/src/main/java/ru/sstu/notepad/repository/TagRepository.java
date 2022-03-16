package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
