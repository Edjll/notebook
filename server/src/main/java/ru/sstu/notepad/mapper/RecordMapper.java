package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.model.record.RecordBodyToSave;

import java.util.List;
import java.util.Set;

@Mapper
public interface RecordMapper {
    RecordMapper MAPPER = Mappers.getMapper(RecordMapper.class);

    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "startDate", source = "source.startDate")
    @Mapping(target = "endDate", source = "source.endDate")
    @Mapping(target = "priority", source = "source.priority")
    @Mapping(target = "statusActive", source = "source.statusActive")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "id", ignore = true)
    Record toEntity(RecordBodyToSave source, Set<Tag> tags);
    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "startDate", source = "source.startDate")
    @Mapping(target = "endDate", source = "source.endDate")
    @Mapping(target = "priority", source = "source.priority")
    @Mapping(target = "statusActive", source = "source.statusActive")
    @Mapping(target = "tags", source = "tags")
    Record toEntity(RecordBodyToSave source, @MappingTarget Record recordEntity, Set<Tag> tags);

    List<Record> toEntityList(List<RecordBodyToSave> source);

    @Mapping(target = "title", source = "source.title")
    @Mapping(target = "description", source = "source.description")
    @Mapping(target = "startDate", source = "source.startDate")
    @Mapping(target = "endDate", source = "source.endDate")
    @Mapping(target = "priority", source = "source.priority")
    @Mapping(target = "statusActive", source = "source.statusActive")
    @Mapping(target = "tags", source = "source.tags")
    @Mapping(target = "id", source = "source.id")
    RecordBody toDto(Record source);

    List<RecordBody> toDtoList(List<Record> source);
}
