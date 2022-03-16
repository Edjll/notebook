package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.TagBody;
import ru.sstu.notepad.repository.TagRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public void create(TagBody body) {
        repository.save(body.toTag());
    }

    @Transactional
    public void update(Long id, TagBody body) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Tag tag = body.toTag();
        tag.setId(id);
        repository.save(tag);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    public Set<Tag> getTags(Set<Long> ids) {
        return new HashSet<>(repository.findAllById(ids));
    }
}
