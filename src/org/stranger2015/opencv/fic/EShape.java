package org.stranger2015.opencv.fic;

import org.stranger2015.opencv.fic.core.Circle;
import org.stranger2015.opencv.fic.core.Rectangle;
import org.stranger2015.opencv.fic.core.Square;
import org.stranger2015.opencv.fic.core.Triangle;

/**
 *
 */
public
enum EShape {
    CIRCLE(Circle.class),
    IRREGULAR(Irregular.class),
    RECTANGLE(Rectangle.class),
    SQUARE(Square.class),
    TRIANGLE(Triangle.class),

    ;

    Class <?> clazz;

    /**
     * @param clazz
     */
    EShape ( Class <?> clazz ) {
        this.clazz = clazz;
    }
}
