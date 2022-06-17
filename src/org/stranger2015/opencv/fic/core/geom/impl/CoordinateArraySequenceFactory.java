package org.stranger2015.opencv.fic.core.geom.impl;

import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.CoordinateSequence;
import org.stranger2015.opencv.fic.core.geom.CoordinateSequenceFactory;

import java.io.Serializable;

/**
 * Creates {@link CoordinateSequence}s represented as an array of {@link Coordinate}s.
 *
 * @version 1.7
 */
public final class CoordinateArraySequenceFactory
        implements CoordinateSequenceFactory, Serializable
{
    private static final long serialVersionUID = -4099577099607551657L;
    private static final CoordinateArraySequenceFactory instanceObject = new CoordinateArraySequenceFactory();

    private CoordinateArraySequenceFactory() {
    }

    private Object readResolve() {
        // http://www.javaworld.com/javaworld/javatips/jw-javatip122.html
        return instance();
    }

    /**
     * Returns the singleton instance of {@link CoordinateArraySequenceFactory}
     */
    public static
    CoordinateArraySequenceFactory instance() {
        return instanceObject;
    }

    /**
     * Returns a {@link CoordinateArraySequence} based on the given array (the array is
     * not copied).
     *
     * @param coordinates
     *            the coordinates, which may not be null nor contain null
     *            elements
     */
//    public CoordinateSequence create(Coordinate[] coordinates) {
//        return new CoordinateArraySequence(coordinates);
//    }

    /**
     * 
     */
    public CoordinateSequence create(CoordinateSequence coordSeq) {
        return new CoordinateArraySequence(coordSeq);
    }

    /**
     * Returns a {@link CoordinateSequence} based on the given array.
     * Whether the array is copied or simply referenced
     * is implementation-dependent.
     * This method must handle null arguments by creating an empty sequence.
     *
     * @param coordinates the coordinates
     */
    @Override
    public
    CoordinateSequence create ( Coordinate[] coordinates ) {
        return null;
    }

    /**
     * Returns a {@link CoordinateSequence} based on the given array.
     * Whether the array is copied or simply referenced
     * is implementation-dependent.
     * This method must handle null arguments by creating an empty sequence.
     *
     * @param coordinates the coordinates
     */
    @Override
    public
   CoordinateSequence create (Coordinate[] coordinates ) {
        return null;
    }

    /**
     * Creates a {@link CoordinateSequence} which is a copy
     * of the given {@link CoordinateSequence}.
     * This method must handle null arguments by creating an empty sequence.
     *
     * @param coordSeq the coordinate sequence to copy
     */
    @Override
    public
   CoordinateSequence create (CoordinateSequence coordSeq ) {
        return null;
    }

    /**
     * The created sequence dimension is clamped to be &lt;= 3.
     *
     * @seeCoordinateSequenceFactory#create(int, int)
     *
     */
    public CoordinateSequence create(int size, int dimension) {
        if (dimension > 3)
            dimension = 3;
        //throw new IllegalArgumentException("dimension must be <= 3");

        // handle bogus dimension
        if (dimension < 2)
            dimension = 2;

        return new CoordinateArraySequence(size, dimension);
    }

    public CoordinateSequence create(int size, int dimension, int measures) {
        int spatial = dimension - measures;

        if (measures > 1) {
            measures = 1; // clip measures
            //throw new IllegalArgumentException("measures must be <= 1");
        }
        if ((spatial) > 3) {
            spatial = 3; // clip spatial dimension
            //throw new IllegalArgumentException("spatial dimension must be <= 3");
        }

        if (spatial < 2)
            spatial = 2; // handle bogus spatial dimension

        return new CoordinateArraySequence(size, spatial+measures, measures);
    }
}
