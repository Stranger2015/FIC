package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.geom.*;


public
class Circle<T extends Circle<T>> extends Geometry<T> {

private int radius;

    public
    Circle () throws ValueError {
        super();
    }

    @Override
    public
    boolean isHomogeneous () throws ValueError {
        return false;
    }

    /**
     * @return
     */
    @Override
    public
    IShape.EShape getShapeKind () {
        return CIRCLE;
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return (int) (Math.PI*2*radius);//fixme
    }

    @Override
    public
    IAddress <?> getAddress () {
        return null;
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
    Coordinate getCentroid () {
        return null;
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
    Geometry <T> reverseInternal () {
        return null;
    }

    @Override
    public
    boolean equalsExact ( Geometry <T> other, double tolerance ) {
        return false;
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
    int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
    int getTypeCode () {
        return 0;
    }

    @Override
    public
    Point[] tVertices () {
        return new Point[0];
    }

    @Override
    public
    double perimeter () {
        return 0;
    }

    @Override
    public
    int compareTo ( @NotNull T o ) {
        return 0;
    }
}
