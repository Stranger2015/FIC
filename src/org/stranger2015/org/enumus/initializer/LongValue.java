package org.stranger2015.opencv.fic.core.enumus.enumus.initializer;

import java.lang.annotation.*;

@Argument
@Repeatable(org.stranger2015.opencv.fic.core.enumus.enumus.initializer.LongValues.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LongValue {
    String name();
    long[] value();
}

