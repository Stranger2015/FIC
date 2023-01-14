package org.stranger2015.opencv.fic.core.enumus.enumus.initializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;

@Argument
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value(name = "${name}", value = "${value}", type = Date.class, factory = SimpleDateFormat.class, factoryMethod = "parse")
@org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value(name = "factoryArgument", value = "${format}")
public @interface DateValue {
    String name();
    String value();
    String format() default "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; // example: 2001-07-04T12:08:56.235-0700
}
