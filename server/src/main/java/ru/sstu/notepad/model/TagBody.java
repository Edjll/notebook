package ru.sstu.notepad.model;

import lombok.Data;
import ru.sstu.notepad.entity.Tag;

@Data
public class TagBody {

    private String name;

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
