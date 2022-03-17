package ru.sstu.notepad.validation.record.record;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;

public interface RecordDateValidatable {
    LocalDateTime getStartDate();
    void setStartDate(LocalDateTime date);

    LocalDateTime getEndDate();
    void setEndDate(LocalDateTime date);

    @AssertTrue(message = "Дата начала не может быть позже даты конца")
    default boolean isCorrectOrderDateValid(){
        return getStartDate().isEqual(getEndDate()) || getStartDate().isBefore(getEndDate());
    }

}
