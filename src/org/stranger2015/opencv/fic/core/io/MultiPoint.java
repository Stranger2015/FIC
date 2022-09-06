package org.stranger2015.opencv.fic.core.io;

import org.stranger2015.opencv.fic.core.geom.*;

/**
 * @param <T>
 */
public
class MultiPoint<T extends MultiPoint<T>>
        extends Geometry<T> {

    /**
     * Creates a new <code>Geometry</code> via the specified GeometryFactory.
     *
     * @param factory
     */
    public
    MultiPoint ( GeometryFactory factory, Object o ) {
        super(factory);
    }

    @Override
    public
    String getGeometryType () {
        return null;
    }

    @Override
    public
    Coordinate getCoordinate () {
        return null;
    }

    @Override
    public
    Coordinate[] getCoordinates () {
        return new Coordinate[0];
    }

    @Override
    public
    int getNumPoints () {
        return 0;
    }

    @Override
    public
    boolean isEmpty () {
        return false;
    }

    @Override
    public
    int getDimension () {
        return 0;
    }

    @Override
    public
    Geometry<T> getBoundary () {
        return null;
    }

    @Override
    public
    int getBoundaryDimension () {
        return 0;
    }

    @Override
    protected
    Geometry<T> reverseInternal () {
        return null;
    }

    @Override
    public
    void apply ( ICoordinateFilter filter ) {

    }

    @Override
    public
    void apply ( ICoordinateSequenceFilter filter ) {

    }

    @Override
    public
    void apply ( IGeometryFilter filter ) {

    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {

    }

    @Override
    public
    boolean equalsExact ( Geometry<T> other, double tolerance ) {
        return false;
    }

  @Override
    public
    void apply ( IGeometryFilter filter ) {

    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {

    }

    @Override
    protected
    Geometry copyInternal () {
        return null;
    }

    @Override
    public
    void normalize () {
    }

    @Override
    protected
    Envelope computeEnvelopeInternal () {
        return null;
    }

    @Override
    protected
    int compareToSameClass ( T o ) {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( T o, org.stranger2015.opencv.fic.core.geom.CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
     int compareToSameClass ( T o ) {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
    int getTypeCode () {
        return 0;
    }
}
