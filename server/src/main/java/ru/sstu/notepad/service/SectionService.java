package ru.sstu.notepad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sstu.notepad.entity.Section;
import ru.sstu.notepad.model.section.SectionBody;
import ru.sstu.notepad.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

import static ru.sstu.notepad.mapper.SectionMapper.SECTION_MAPPER;

@Service
@AllArgsConstructor
public class SectionService {

    private final SectionRepository repository;

    public Optional<Section> findById(Long id) {
        return repository.findById(id);
    }

    public List<SectionBody> getAll() {
        return SECTION_MAPPER.toDtoList(repository.findAll());
    }
}
