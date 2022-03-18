package ru.sstu.notepad.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.sstu.notepad.entity.Tag;
import ru.sstu.notepad.validation.record.record.RecordConstraint;

import java.util.List;

@Data
@RecordConstraint
public class RecordBody extends RecordBodyCommon {
    private List<Tag> tags;
}
