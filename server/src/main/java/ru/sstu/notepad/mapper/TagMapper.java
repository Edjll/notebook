package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.tag.TagBody;
import ru.sstu.notepad.model.tag.TagBodyToSave;

import java.util.List;
import java.util.Set;

@Mapper(imports = RecordMapper.class)
public interface TagMapper {
    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "records", source = "records")
    Tag toEntity(TagBodyToSave source, @MappingTarget Tag target, Set<Record> records);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "records", source = "records")
    Tag toEntity(TagBodyToSave source,  Set<Record> records);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "records", expression = "java(ru.sstu.notepad.mapper.RecordMapper.MAPPER.toDtoList(source.getRecords()))")
    TagBody toDto(Tag source);


    List<TagBody> toDtoList(List<Tag> source);
}
