package ru.sstu.notepad.model.record;

import lombok.Data;
import lombok.ToString;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.validation.record.record.RecordConstraint;

import java.util.List;

@Data
@ToString(callSuper = true)
@RecordConstraint
public class RecordBody extends RecordBodyCommon {
    private List<Tag> tags;
}
