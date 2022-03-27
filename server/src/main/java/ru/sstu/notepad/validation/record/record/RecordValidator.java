package ru.sstu.notepad.validation.record.record;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import ru.sstu.notepad.model.task.TaskBody;
import ru.sstu.notepad.service.TaskService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class RecordValidator implements ConstraintValidator<RecordConstraint, TaskBody> {

    private final TaskService taskService;

    @Override
    public void initialize(RecordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TaskBody recordBody, ConstraintValidatorContext context) {
        ConstraintValidatorContextImpl constraintValidatorContext = (ConstraintValidatorContextImpl) context;
        if (taskService.hasIntersection(recordBody)) {
            constraintValidatorContext.addMessageParameter(
                    "errorMessage",
                    "Даты задачи пересекаются с другой задачей"
            );
            return false;
        }
        return true;
    }
}
