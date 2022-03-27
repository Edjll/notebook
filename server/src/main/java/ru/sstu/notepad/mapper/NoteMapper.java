package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Note;
import ru.sstu.notepad.entity.Priority;
import ru.sstu.notepad.entity.Section;
import ru.sstu.notepad.model.note.NoteBody;

import java.util.List;

@Mapper
public interface NoteMapper {
    NoteMapper NOTE_MAPPER = Mappers.getMapper(NoteMapper.class);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "priority",       source = "priority")
    @Mapping(target = "section",        source = "section")
    @Mapping(target = "id",             ignore = true)
    Note toEntity(NoteBody source, Priority priority, Section section);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "priority",       source = "priority")
    @Mapping(target = "section",        source = "section")
    @Mapping(target = "id",             ignore = true)
    @Mapping(target = "createdDate",    ignore = true)
    @Mapping(target = "modifiedDate",   ignore = true)
    Note toEntity(NoteBody source, @MappingTarget Note noteEntity, Priority priority, Section section);

    @Mapping(target = "title",          source = "source.title")
    @Mapping(target = "description",    source = "source.description")
    @Mapping(target = "createdDate",    source = "source.createdDate")
    @Mapping(target = "modifiedDate",   source = "source.modifiedDate")
    @Mapping(target = "priority",       expression  = "java(source.getPriority().getId())")
    @Mapping(target = "priorityBody",   expression  = "java(ru.sstu.notepad.mapper.PriorityMapper.PRIORITY_MAPPER.toDto(source.getPriority()))")
    @Mapping(target = "section",        expression  = "java(source.getSection().getId())")
    @Mapping(target = "sectionBody",    expression  = "java(ru.sstu.notepad.mapper.SectionMapper.SECTION_MAPPER.toDto(source.getSection()))")
    @Mapping(target = "id",             source = "source.id")
    NoteBody toDto(Note source);

    List<NoteBody> toDtoList(List<Note> source);
}
