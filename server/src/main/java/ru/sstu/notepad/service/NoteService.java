package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Note;
import ru.sstu.notepad.entity.Priority;
import ru.sstu.notepad.entity.Section;
import ru.sstu.notepad.model.note.NoteBody;
import ru.sstu.notepad.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static ru.sstu.notepad.mapper.NoteMapper.NOTE_MAPPER;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;
    private final PriorityService priorityService;
    private final SectionService sectionService;

    @Transactional
    public void create(NoteBody body) {
        Priority priority = priorityService.findById(body.getPriority())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Section section = sectionService.findById(body.getSection())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Note note = NOTE_MAPPER.toEntity(body, priority, section);
        note.setCreatedDate(LocalDateTime.now());

        repository.save(note);
    }

    @Transactional
    public void update(Long id, NoteBody body) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Priority priority = priorityService.findById(body.getPriority())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Section section = sectionService.findById(body.getSection())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        note = NOTE_MAPPER.toEntity(body, note, priority, section);
        note.setModifiedDate(LocalDateTime.now());

        repository.save(note);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    public NoteBody getById(Long id) {
        return repository.findById(id)
                .map(NOTE_MAPPER::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<NoteBody> getAll(Long sectionId, Date searchDate) {
        return NOTE_MAPPER.toDtoList(repository.getAllBySectionIdAndDate(sectionId, searchDate));
    }
}
