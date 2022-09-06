package org.stranger2015.opencv.fic;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.geom.*;

public
class Irregular<A extends IAddress<A>> extends Geometry<?> {
    /**
     * @param address
     */
    public
    Irregular ( IAddress <A> address , IIntSize blockSize ) {
        super(address,/* vertices, image,*/ blockSize);
    }

    /**
     * @return
     */
    @Override
    public
    EShape getShapeKind () {
        return EShape.RECTANGLE;
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return 0;
    }

    @Override
    public
    IAddress <A> getAddress () {
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
    Geometry <?> reverseInternal () {
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
    int getTypeCode () {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( Geometry <?> o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( Geometry <?> o ) {
        return 0;
    }

    @Override
    public
    boolean equalsExact ( Geometry <?> other, double tolerance ) {
        return false;
    }

    @Override
    public
    int compareTo ( @NotNull Geometry <?> o ) {
        return 0;
    }
}
