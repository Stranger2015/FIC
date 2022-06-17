package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.core.geom.CoordinateXY;
import org.stranger2015.opencv.fic.core.geom.CoordinateXYM;
import org.stranger2015.opencv.fic.core.geom.CoordinateXYZM;

/**
 * Useful utility functions for handling Coordinate objects.
 */
public class Coordinates {
    /**
     * Factory method providing access to common Coordinate implementations.
     *
     * @param dimension
     * @return created coordinate
     */
    public static
    Coordinate create( int dimension)
    {
        return create(dimension, 0);
    }

    /**
     * Factory method providing access to common Coordinate implementations.
     *
     * @param dimension
     * @param measures
     * @return created coordinate
     */
    public static Coordinate create(int dimension, int measures)
    {
        if (dimension == 2) {
            return new CoordinateXY();
        } else if (dimension == 3 && measures == 0) {
            return new Coordinate();
        } else if (dimension == 3 && measures == 1) {
            return new CoordinateXYM();
        } else if (dimension == 4 && measures == 1) {
            return new CoordinateXYZM();
        }
        return new Coordinate();
    }

    /**
     * Determine dimension based on subclass of {@link Coordinate}.
     *
     * @param coordinate supplied coordinate
     * @return number of ordinates recorded
     */
    public static int dimension(Coordinate coordinate)
    {
        if (coordinate instanceof CoordinateXY) {
            return 2;
        } else if (coordinate instanceof CoordinateXYM) {
            return 3;
        } else if (coordinate instanceof CoordinateXYZM) {
            return 4;
        } else if (coordinate instanceof Coordinate) {
            return 3;
        }
        return 3;
    }

    /**
     * Determine number of measures based on subclass of {@link Coordinate}.
     *
     * @param coordinate supplied coordinate
     * @return number of measures recorded
     */
    public static int measures(Coordinate coordinate)
    {
        if (coordinate instanceof CoordinateXY) {
            return 0;
        } else if (coordinate instanceof CoordinateXYM) {
            return 1;
        } else if (coordinate instanceof CoordinateXYZM) {
            return 1;
        } else if (coordinate instanceof Coordinate) {
            return 0;
        }
        return 0;
    }

}
