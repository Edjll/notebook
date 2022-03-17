package ru.sstu.notepad.model;

import lombok.Data;
import ru.sstu.notepad.entity.Record;
import ru.sstu.notepad.validation.record.record.RecordConstraint;
import ru.sstu.notepad.validation.record.record.RecordDateValidatable;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@RecordConstraint
public class RecordBody implements RecordDateValidatable {

    private String title;

    private String description;

    private Set<Long> tagsIds;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean statusActive;

    public Record toRecord() {
        Record record = new Record();
        record.setTitle(title);
        record.setDescription(description);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        return record;
    }
}
