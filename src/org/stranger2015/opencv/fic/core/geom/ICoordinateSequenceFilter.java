package org.stranger2015.opencv.fic.core.geom;

import org.locationtech.jts.geom.CoordinateFilter;
import org.stranger2015.opencv.fic.core.geom.util.GeometryEditor;
import org.stranger2015.opencv.fic.core.geom.util.GeometryTransformer;

/**
 *  An interface for classes which process the coordinates in a {@link ICoordinateSequence}.
 *  A filter can either record information about each coordinate,
 *  or change the value of the coordinate.
 *  Filters can be
 *  used to implement operations such as coordinate transformations, centroid and
 *  envelope computation, and many other functions.
 *  {@link Geometry} classes support the concept of applying a
 *  <code>CoordinateSequenceFilter</code> to each
 *  {@link ICoordinateSequence}s they contain.
 *  <p>
 *  For maximum efficiency, the execution of filters can be short-circuited by using the {@link #isDone} method.
 *  <p>
 *  <code>CoordinateSequenceFilter</code> is
 *  an example of the Gang-of-Four Visitor pattern.
 *  <p>
 * <b>Note</b>: In general, it is preferable to treat Geometrys as immutable.
 * Mutation should be performed by creating a new Geometry object (see {@link GeometryEditor}
 * and {@link GeometryTransformer} for convenient ways to do this).
 * An exception to this rule is when a new Geometry has been created via {@link Geometry#copy()}.
 * In this case mutating the Geometry will not cause aliasing issues,
 * and a filter is a convenient way to implement coordinate transformation.
 *
 * @see Geometry#apply(CoordinateFilter)
 * @see GeometryTransformer
 * @see GeometryEditor
 *
 *@see Geometry#apply(org.stranger2015.opencv.fic.core.geom.CoordinateSequenceFilter)
 *@author Martin Davis
 *@version 1.7
 */
public interface ICoordinateSequenceFilter
{
    /**
     * Performs an operation on a coordinate in a {@link ICoordinateSequence}.
     *
     *@param seq  the <code>ICoordinateSequence</code> to which the filter is applied
     *@param i the index of the coordinate to apply the filter to
     */
    void filter( ICoordinateSequence seq, int i);

    /**
     * Reports whether the application of this filter can be terminated.
     * Once this method returns <tt>true</tt>, it must
     * continue to return <tt>true</tt> on every subsequent call.
     *
     * @return true if the application of this filter can be terminated.
     */
    boolean isDone();

    /**
     * Reports whether the execution of this filter
     * has modified the coordinates of the geometry.
     * If so, {@link Geometry#geometryChanged} will be executed
     * after this filter has finished being executed.
     * <p>
     * Most filters can simply return a constant value reflecting
     * whether they are able to change the coordinates.
     *
     * @return true if this filter has changed the coordinates of the geometry
     */
    boolean isGeometryChanged();
}


