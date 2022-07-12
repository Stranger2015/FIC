package org.stranger2015.opencv.fic.core.geom;

/**
 * A factory to create concrete instances of {@link ICoordinateSequence}s.
 * Used to configure {@link GeometryFactory}s
 * to provide specific kinds of CoordinateSequences.
 *
 * @version 1.7
 */
public
interface ICoordinateSequenceFactory {

    /**
     * Returns a {@link ICoordinateSequence} based on the given array.
     * Whether the array is copied or simply referenced
     * is implementation-dependent.
     * This method must handle null arguments by creating an empty sequence.
     *
     * @param coordinates the coordinates
     */
    ICoordinateSequence create ( Coordinate[] coordinates );

    /**
     * Creates a {@link ICoordinateSequence} which is a copy
     * of the given {@link ICoordinateSequence}.
     * This method must handle null arguments by creating an empty sequence.
     *
     * @param coordSeq the coordinate sequence to copy
     */
    ICoordinateSequence create ( ICoordinateSequence coordSeq );

    /**
     * Creates a {@link ICoordinateSequence} of the specified size and dimension.
     * For this to be useful, the {@link ICoordinateSequence} implementation must
     * be mutable.
     * <p>
     * If the requested dimension is larger than the ICoordinateSequence implementation
     * can provide, then a sequence of maximum possible dimension should be created.
     * An error should not be thrown.
     *
     * @param size      the number of coordinates in the sequence
     * @param dimension the dimension of the coordinates in the sequence (if user-specifiable,
     *                  otherwise ignored)
     */
    ICoordinateSequence create ( int size, int dimension );

    /**
     * Creates a {@link ICoordinateSequence} of the specified size and dimension with measure support.
     * For this to be useful, the {@link ICoordinateSequence} implementation must
     * be mutable.
     * <p>
     * If the requested dimension or measures are larger than the ICoordinateSequence implementation
     * can provide, then a sequence of maximum possible dimension should be created.
     * An error should not be thrown.
     *
     * @param size      the number of coordinates in the sequence
     * @param dimension the dimension of the coordinates in the sequence (if user-specifiable,
     *                  otherwise ignored)
     * @param measures  the number of measures of the coordinates in the sequence (if user-specifiable,
     *                  otherwise ignored)
     */
    default
    ICoordinateSequence create ( int size, int dimension, int measures ) {
        return create(size, dimension);
    }
}
