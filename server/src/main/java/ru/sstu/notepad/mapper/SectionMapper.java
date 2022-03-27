package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Section;
import ru.sstu.notepad.model.section.SectionBody;

import java.util.List;

@Mapper
public interface SectionMapper {
    SectionMapper SECTION_MAPPER = Mappers.getMapper(SectionMapper.class);

    @Mapping(target = "name",   source = "source.name")
    @Mapping(target = "id",     source = "source.id")
    SectionBody toDto(Section source);

    List<SectionBody> toDtoList(List<Section> source);
}