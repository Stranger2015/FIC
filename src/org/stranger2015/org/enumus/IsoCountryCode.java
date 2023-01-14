package org.stranger2015.opencv.fic.core.enumus.enumus;


import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Argument;
import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Value(name = "${name}", value = "${iso}", type = org.stranger2015.opencv.fic.core.enumus.enumus.IsoAlpha2.class, factory = org.stranger2015.opencv.fic.core.enumus.enumus.IsoAlpha2.class)
@Argument
public @interface IsoCountryCode {
    String name();
    org.stranger2015.opencv.fic.core.enumus.enumus.IsoAlpha2 iso();
}
