package org.stranger2015.opencv.fic.core.enumus.enumus.initializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Argument
@org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value(name = "${name}", value = {"${regex}", "${options}"}, type = Pattern.class, factory = Pattern.class, factoryMethod = "compile")
@org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value(name = "factoryMethodArgument", value = "${regex}")
@org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value(name = "factoryMethodArgument", value = "${options}")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PatternValue {
    String name();
    String regex();
    int options() default 0;
}
