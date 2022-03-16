package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.RecordBody;
import ru.sstu.notepad.repository.RecordRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;
    private final TagService tagService;

    @Transactional
    public void create(RecordBody body) {
        Set<Tag> tags = tagService.getTags(body.getTagsIds());
        Record record = body.toRecord();
        record.setTags(tags);
        repository.save(record);
    }

    @Transactional
    public void update(Long id, RecordBody body) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Set<Tag> tags = tagService.getTags(body.getTagsIds());
        Record record = body.toRecord();
        record.setTags(tags);
        record.setId(id);
        repository.save(record);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }
}
