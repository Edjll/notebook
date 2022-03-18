package ru.sstu.notepad.model.tag;

import lombok.Data;

import java.util.Set;

@Data
public class TagBodyToSave extends TagBodyCommon{
    private Set<Long> recordIds;
}
