package ru.sstu.notepad.validation.record.record;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import ru.sstu.notepad.model.record.RecordBodyToSave;
import ru.sstu.notepad.service.RecordService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class RecordValidator implements ConstraintValidator<RecordConstraint, RecordBodyToSave> {
    private final RecordService recordService;
    @Override
    public void initialize(RecordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RecordBodyToSave recordBody, ConstraintValidatorContext context) {
        ConstraintValidatorContextImpl constraintValidatorContext = (ConstraintValidatorContextImpl) context;
        if(recordService.isNotIntersection(recordBody)){
            constraintValidatorContext.addMessageParameter(
                    "errorMessage",
                    "Даты заметки пересекаются с другими датами заметок!"
            );
            return false;
        }
        return true;
    }
}
