package org.stranger2015.opencv.fic.core;

import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.geom.Coordinate;

/**
 *
 */
public
interface IShape {

    /**
     * @return
     */
    boolean isHomogeneous () throws ValueError;

    /**
     *
     */
    enum EShape {
        RECTANGLE,
        SQUARE,
        QUADRILATERAL,
        TRIANGLE,
        HEXAGON,
        CIRCLE,
        SQUIRAL,
        IRREGULAR,
//        TRIANGULATION
    }

    /**
     * @return
     */
    EShape getShapeKind ();

    /**
     * @return
     */
    double area ();

    /**
     * @return
     */
    IAddress <?> getAddress ();

    /**
     * @return
     */
    Coordinate getCentroid ();

    /**
     * @return
     */
//    static
    Point[] tVertices ();

    /**
     * @return
     */
    double perimeter ();
}
