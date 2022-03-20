package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Task;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.tag.TagBody;
import ru.sstu.notepad.model.tag.TagBodyToSave;

import java.util.List;
import java.util.Set;

@Mapper(imports = TaskMapper.class)
public interface TagMapper {
    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "tasks", source = "tasks")
    Tag toEntity(TagBodyToSave source, @MappingTarget Tag target, Set<Task> tasks);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "tasks", source = "tasks")
    Tag toEntity(TagBodyToSave source,  Set<Task> tasks);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    @Mapping(target = "tasks", expression = "java(ru.sstu.notepad.mapper.TaskMapper.MAPPER.toDtoList(source.getTasks()))")
    TagBody toDto(Tag source);


    List<TagBody> toDtoList(List<Tag> source);
}
