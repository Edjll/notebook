package ru.sstu.notepad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.model.TagBody;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    Tag toEntity(TagBody source);

    List<Tag> toEntityList(List<TagBody> source);

    @Mapping(target = "name", source = "source.name")
    @Mapping(target = "id", source = "source.id")
    TagBody toDto(Tag source);

    List<TagBody> toDtoList(List<Tag> source);
}
