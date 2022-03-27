package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Priority;
import ru.sstu.notepad.model.priority.PriorityBody;

import java.util.List;

@Mapper
public interface PriorityMapper {
    PriorityMapper PRIORITY_MAPPER = Mappers.getMapper(PriorityMapper.class);

    @Mapping(target = "name",       source = "source.name")
    @Mapping(target = "className",  source = "source.className")
    @Mapping(target = "id",         source = "source.id")
    PriorityBody toDto(Priority source);

    List<PriorityBody> toDtoList(List<Priority> source);
}
