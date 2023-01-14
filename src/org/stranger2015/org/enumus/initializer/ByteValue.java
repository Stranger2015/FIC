package org.stranger2015.opencv.fic.core.enumus.enumus.initializer;

import java.lang.annotation.*;

@Argument
@Repeatable(value = ByteValues.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ByteValue {
    String name();
    byte[] value();
}

