package ru.sstu.notepad.model;

import lombok.Data;
import ru.sstu.notepad.entity.Record;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class RecordBody {

    private String title;

    private String description;

    private Set<Long> tagsIds;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Record toRecord() {
        Record record = new Record();
        record.setTitle(title);
        record.setDescription(description);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        return record;
    }
}
