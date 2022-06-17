package org.stranger2015.opencv.fic.utils.converters;

import org.jetbrains.annotations.Contract;
import org.opencv.core.MatOfPoint;

/**
 *
 */
public
class PointToMatConverter {
    /**
     *
     */
    @Contract(pure = true)
    protected
    PointToMatConverter () {
    }

    public static
    MatOfPoint convert ( Polygon <?> polygon ) {

        MatOfPoint mat = new MatOfPoint();
        mat.fromList(polygon.getVertices());

        return mat;
    }
}
