package ru.sstu.notepad.validation.record.record;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import ru.sstu.notepad.model.record.RecordBody;
import ru.sstu.notepad.service.RecordService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class RecordValidator implements ConstraintValidator<RecordConstraint, RecordBody> {
    private final RecordService recordService;
    @Override
    public void initialize(RecordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RecordBody recordBody, ConstraintValidatorContext context) {
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
