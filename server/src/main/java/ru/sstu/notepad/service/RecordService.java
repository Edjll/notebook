package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.repository.RecordRepository;
import ru.sstu.notepad.utils.DataUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sstu.notepad.mapper.RecordMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;

    @Transactional
    public void save(RecordBody body) {
        Record record;
        if (body.getId() != null){
            Optional<Record> recordEntity = repository.findById(body.getId());
            if(recordEntity.isPresent()){
                record = MAPPER.toEntity(body, recordEntity.get());
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Не найдена заметка с id = %d", body.getId()));
            }
        }else{

            record = MAPPER.toEntity(body);
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

    public boolean isNotIntersection(RecordBody recordBodyToSave) {
        return repository.findAll().stream().anyMatch(record ->
                !record.getId().equals(recordBodyToSave.getId()) &&
                        DataUtils.isIncludedInPeriods(
                                record.getStartDate(),
                                record.getEndDate(),
                                recordBodyToSave.getStartDate(),
                                recordBodyToSave.getStartDate()
                        ));
    }
    public Set<Record> getRecords(Set<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return new HashSet<>();
        }
        return new HashSet<>(repository.findAllById(ids));
    }

    public List<RecordBody> findByDate(LocalDateTime startDate, LocalDateTime endDate){
        return MAPPER.toDtoList(repository.findAllByStartDateIsAfterAndEndDateIsBefore(startDate, endDate));
    }
}
