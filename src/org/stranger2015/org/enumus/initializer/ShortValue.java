package org.stranger2015.opencv.fic.core.enumus.enumus.initializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Argument
@Repeatable(org.stranger2015.opencv.fic.core.enumus.enumus.initializer.ShortValues.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ShortValue {
    String name();
    short[] value();
}

