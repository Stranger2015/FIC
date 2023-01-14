package org.stranger2015.opencv.fic.core.enumus.enumus.samples;

import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Argument;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Argument
public @interface Environment {
    String name();
    RuntimeEnvironment[] value();
}
