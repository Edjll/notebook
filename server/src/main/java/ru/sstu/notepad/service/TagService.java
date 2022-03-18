package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.mapper.TagMapper;
import ru.sstu.notepad.model.tag.TagBody;
import ru.sstu.notepad.model.tag.TagBodyToSave;
import ru.sstu.notepad.repository.TagRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sstu.notepad.mapper.TagMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;
    private final RecordService recordService;

    public void create(TagBodyToSave body) {
        Tag tag;
        Set<Record> records = null;

        if(!CollectionUtils.isEmpty(body.getRecordIds())){
            records = recordService.getRecords(body.getRecordIds());
        }

        if (body.getId() != null){
            Optional<Tag> tagEntity = repository.findById(body.getId());
            if(tagEntity.isPresent()){
                tag = TagMapper.MAPPER.toEntity(body, tagEntity.get(), records);
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Не найдена вкладка с id = %d", body.getId()));
            }
        }else{
            tag = TagMapper.MAPPER.toEntity(body, records);
        }

        repository.save(tag);
    }



    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }



    @Transactional
    public List<TagBody> getAll() {
        return MAPPER.toDtoList(repository.findAll());
    }

    public Set<Long> getTagIds(Set<Long> ids) {
        return repository.findAllById(ids).stream().map(Tag::getId).collect(Collectors.toSet());
    }
}
