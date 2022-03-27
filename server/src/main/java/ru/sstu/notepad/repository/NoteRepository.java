package ru.sstu.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sstu.notepad.entity.Note;

import java.util.Date;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query( "select note " +
            "from Note note " +
            "where (cast(:searchDate as date) is null " +
                "or ((note.modifiedDate is null and cast(note.createdDate as date) = :searchDate) or (cast(note.modifiedDate as date) = :searchDate))) " +
            "and (:sectionId is null or note.section.id = :sectionId) " +
            "order by note.modifiedDate desc, note.createdDate desc" )
    List<Note> getAllBySectionIdAndDate(@Param("sectionId") Long sectionId, @Param("searchDate") Date searchDate);
}
