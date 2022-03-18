package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.TagBody;
import ru.sstu.notepad.repository.TagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sstu.notepad.mapper.TagMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public void create(TagBody body) {
        repository.save(MAPPER.toEntity(body));
    }

    @Transactional
    public void update(Long id, TagBody body) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Tag tag =  MAPPER.toEntity(body);
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
        if(CollectionUtils.isEmpty(ids)){
            return new HashSet<>();
        }
        return new HashSet<>(repository.findAllById(ids));
    }

    @Transactional
    public List<TagBody> getAll() {
        return MAPPER.toDtoList(repository.findAll());
    }

    public Set<Long> getTagIds(Set<Long> ids) {
        return repository.findAllById(ids).stream().map(Tag::getId).collect(Collectors.toSet());
    }
}
