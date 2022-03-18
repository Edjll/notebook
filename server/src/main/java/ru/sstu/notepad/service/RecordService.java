package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.model.record.RecordBodyToSave;
import ru.sstu.notepad.repository.RecordRepository;
import ru.sstu.notepad.utils.DataUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sstu.notepad.mapper.RecordMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;
    private final TagService tagService;

    @Transactional
    public void save(RecordBodyToSave body) {
        Record record ;
        Set<Tag> tags = null;

        if(!CollectionUtils.isEmpty(body.getTagsIds())){
            tags = tagService.getTags(body.getTagsIds());
        }

        if (body.getId() != null){
            Optional<Record> recordEntity = repository.findById(body.getId());
            if(recordEntity.isPresent()){
                record = MAPPER.toEntity(body, recordEntity.get(), tags);
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Не найдена заметка с id = %d", body.getId()));
            }
        }else{

            record = MAPPER.toEntity(body, tags);
            record.setTags(tags);
        }

        repository.save(record);
    }

    @Transactional
    public List<RecordBody> getAllActiveRecords() {
        return repository.findAllByStatusActive(true).stream()
                .map(MAPPER::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RecordBody> getAll() {
        return repository.findAll().stream()
                .map(MAPPER::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    public boolean isNotIntersection(RecordBodyToSave recordBodyToSave) {
        return repository.findAll().stream().anyMatch(record ->
                !record.getId().equals(recordBodyToSave.getId()) &&
                        DataUtils.isIncludedInPeriods(
                                record.getStartDate(),
                                record.getEndDate(),
                                recordBodyToSave.getStartDate(),
                                recordBodyToSave.getStartDate()
                        ));
    }
}
