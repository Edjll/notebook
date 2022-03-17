package ru.sstu.notepad.validation.record.record;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import ru.sstu.notepad.model.RecordBody;
import ru.sstu.notepad.utils.DataUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class RecordValidator implements ConstraintValidator<RecordConstraint, RecordBody> {

    @Override
    public void initialize(RecordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RecordBody recordBody, ConstraintValidatorContext context) {
        ConstraintValidatorContextImpl constraintValidatorContext = (ConstraintValidatorContextImpl) context;
        LocalDateTime now = LocalDateTime.now();
        if(DataUtils.isIncludedInPeriods(now, now.plusDays(10), now, now.plusDays(2))){
            constraintValidatorContext.addMessageParameter(
                    "errorMessage",
                    "Даты заметки пересекаются с другими датами заметок!"
            );
            return false;
        }
        return true;
    }
}
