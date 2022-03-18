package ru.sstu.notepad.model.record;

import lombok.Data;
import ru.sstu.notepad.validation.record.record.RecordConstraint;
import ru.sstu.notepad.validation.record.record.RecordDateValidatable;

import java.util.Set;

@Data
@RecordConstraint
public class RecordBodyToSave extends RecordBodyCommon implements RecordDateValidatable{

    private Set<Long> tagsIds;
}
