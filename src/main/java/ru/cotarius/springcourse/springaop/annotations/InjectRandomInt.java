package ru.cotarius.springcourse.springaop.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min() default 0;
    int max() default 1000000;
}
