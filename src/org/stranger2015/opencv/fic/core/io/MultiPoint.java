package org.stranger2015.opencv.fic.core.io;

import org.locationtech.jts.geom.CoordinateSequenceComparator;
import org.stranger2015.opencv.fic.core.algorithm.GeometryComponentFilter;
import org.stranger2015.opencv.fic.core.geom.*;

public
class MultiPoint extends Geometry {
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
    Geometry getBoundary () {
        return null;
    }

    @Override
    public
    int getBoundaryDimension () {
        return 0;
    }

    @Override
    protected
    Geometry reverseInternal () {
        return null;
    }

    @Override
    public
    boolean equalsExact ( Geometry other, double tolerance ) {
        return false;
    }

    @Override
    public
    void apply ( CoordinateFilter filter ) {

    }

    @Override
    public
    void apply ( CoordinateSequenceFilter filter ) {

    }

    @Override
    public
    void apply ( GeometryFilter filter ) {

    }

    @Override
    public
    void apply ( GeometryComponentFilter filter ) {

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
    <T extends Geometry> int compareToSameClass ( T o ) {
        return 0;
    }

    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
    int getTypeCode () {
        return 0;
    }
}
