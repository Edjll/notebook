package ru.sstu.notepad.validation.record.record;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RecordValidator.class)
public @interface RecordConstraint {
    String message() default "{errorMessage}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
