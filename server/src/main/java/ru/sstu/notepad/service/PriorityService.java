package ru.sstu.notepad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sstu.notepad.entity.Priority;
import ru.sstu.notepad.model.priority.PriorityBody;
import ru.sstu.notepad.repository.PriorityRepository;

import java.util.List;
import java.util.Optional;

import static ru.sstu.notepad.mapper.PriorityMapper.PRIORITY_MAPPER;

@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository repository;

    public Optional<Priority> findById(Long id) {
        return repository.findById(id);
    }

    public List<PriorityBody> getAll() {
        return PRIORITY_MAPPER.toDtoList(repository.findAll());
    }
}
