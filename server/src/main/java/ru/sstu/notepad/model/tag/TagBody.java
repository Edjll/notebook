package ru.sstu.notepad.model.tag;

import lombok.Data;
import lombok.ToString;
import ru.sstu.notepad.model.record.RecordBody;

import java.util.List;

@Data
@ToString(callSuper = true)
public class TagBody extends TagBodyCommon{

    private List<RecordBody> records;
}
