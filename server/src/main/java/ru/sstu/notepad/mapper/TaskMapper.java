package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Task;
import ru.sstu.notepad.model.task.TaskBody;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "startDate",      source = "source.startDate")
    @Mapping(target = "endDate",        source = "source.endDate")
    @Mapping(target = "completed",      source = "source.completed")
    @Mapping(target = "id",             ignore = true)
    Task toEntity(TaskBody source);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "startDate",      source = "source.startDate")
    @Mapping(target = "endDate",        source = "source.endDate")
    @Mapping(target = "completed",      source = "source.completed")
    Task toEntity(TaskBody source, @MappingTarget Task taskEntity);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "startDate",      source = "source.startDate")
    @Mapping(target = "endDate",        source = "source.endDate")
    @Mapping(target = "completed",      source = "source.completed")
    @Mapping(target = "id",             source = "source.id")
    TaskBody toDto(Task source);

    List<TaskBody> toDtoList(List<Task> source);
}
