package org.stranger2015.opencv.fic.core.geom;
/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.utils.Assert;

/**
 * Represents a single point.
 * <p>
 * A <code>Point</code> is topologically valid if and only if:
 * <ul>
 * <li>the coordinate which defines it (if any) is a valid coordinate
 * (i.e. does not have an <code>NaN</code> X or Y ordinate)
 * </ul>
 *
 * @version 1.8
 */
public
class Point<T extends Geometry <T>>
        extends Geometry <T>
        implements IPuntal {
    private static final long serialVersionUID = 4902022702746614570L;
    /**
     * The <code>Coordinate</code> wrapped by this <code>Point</code>.
     */
    private ICoordinateSequence coordinates;

    /**
     * Constructs a <code>Point</code> with the given coordinate.
     *
     * @param coordinate     the coordinate on which to base this <code>Point</code>
     *                       , or <code>null</code> to create the empty geometry.
     * @param precisionModel the specification of the grid of allowable points
     *                       for this <code>Point</code>
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>Point</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    Point ( Coordinate coordinate, PrecisionModel precisionModel, int SRID ) {
        super(image, address, blockSize, new GeometryFactory(precisionModel, SRID));

        init(getFactory().getCoordinateSequenceFactory().create(
                coordinate != null ? new Coordinate[]{coordinate} : new Coordinate[]{}));
    }

    /**
     * @param coordinates contains the single coordinate on which to base this <code>Point</code>
     *                    , or <code>null</code> to create the empty geometry.
     */
    public
    Point ( ICoordinateSequence coordinates, GeometryFactory factory ) {
        super(image, address, blockSize, factory);

        init(coordinates);
    }

    private
    void init ( ICoordinateSequence coordinates ) {
        if (coordinates == null) {
            coordinates = getFactory().getCoordinateSequenceFactory().create(new Coordinate[]{});
        }
        Assert.isTrue(coordinates.size() <= 1);

        this.coordinates = coordinates;
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate[] getCoordinates () {
        return isEmpty() ? new Coordinate[]{} : new Coordinate[]{getCoordinate()};
    }

    public
    int getNumPoints () {
        return isEmpty() ? 0 : 1;
    }

    public
    boolean isEmpty () {
        return coordinates.size() == 0;
    }

    public
    boolean isSimple () {
        return true;
    }

    public
    int getDimension () {
        return 0;
    }

    public
    int getBoundaryDimension () {
        return Dimension.FALSE;
    }

    public
    double getX () {
        if (getCoordinate() == null) {
            throw new IllegalStateException("getX called on empty Point");
        }

        return getCoordinate().x;
    }

    public
    double getY () {
        if (getCoordinate() == null) {
            throw new IllegalStateException("getY called on empty Point");
        }

        return getCoordinate().y;
    }

    public
    Coordinate getCoordinate () {
        return coordinates.size() != 0 ? coordinates.getCoordinate(0) : null;
    }

    public
    String getGeometryType () {
        return Geometry.TYPENAME_POINT;
    }

    /**
     * Gets the boundary of this geometry.
     * Zero-dimensional geometries have no boundary by definition,
     * so an empty GeometryCollection is returned.
     *
     * @return an empty GeometryCollection
     * @see Geometry#getBoundary
     */
    public
    Geometry <T> getBoundary () {
        return getFactory().createGeometryCollection();
    }

    protected
    Envelope computeEnvelopeInternal () {
        if (isEmpty()) {
            return new Envelope();
        }
        Envelope env = new Envelope();
        env.expandToInclude(coordinates.getX(0), coordinates.getY(0));

        return env;
    }

    protected
    int compareToSameClass ( T o ) {
        return 0;
    }

    //    @Override
    public
    int compareToSameClass ( Point <T> o ) {
        return 0;
    }

//       @Override
    protected
    int compareToSameClass ( Point <T> o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    public
    boolean equalsExact ( Geometry <T> other, double tolerance ) {
        if (isEquivalentClass(other)) {
            return false;
        }
        if (isEmpty() && other.isEmpty()) {
            return true;
        }
        if (isEmpty() != other.isEmpty()) {
            return false;
        }

        return equal(other.getCoordinate(), this.getCoordinate(), tolerance);
    }

    public
    void apply ( ICoordinateFilter filter ) {
        if (isEmpty()) {
            return;
        }
        filter.filter(getCoordinate());
    }

    @Override
    public
    void apply ( ICoordinateSequenceFilter filter ) {
        if (!isEmpty()) {
            filter.filter(coordinates, 0);
            if (filter.isGeometryChanged()) {
                geometryChanged();
            }
        }
    }

    public
    void apply ( IGeometryFilter filter ) {
        filter.filter(this);
    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {
        filter.filter(this);
    }


    /**
     * Creates and returns a full copy of this {@link Point} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    public
    Object clone () {
        Object clone = super.clone();//fixme
        return copy();
    }

    protected
    Point<T> copyInternal () {
        return new Point<>(coordinates.copy(), factory);
    }

    /**
     * @return
     */
    @Override
    public
    Geometry <T> reverse () {
        return (Point) super.reverse();
    }

    /**
     * @return
     */
    @Override
    protected
    Geometry <?> reverseInternal () {
        return getFactory().createPoint(coordinates.copy());
    }

    /**
     * a Point is always in normalized form
     */
    @Override
    public
    void normalize () {
    }

    /**
     * @param other
     * @param comp
     * @return
     */
    @Override
    protected
    int compareToSameClass ( T other, CoordinateSequenceComparator comp ) {
        Point<T> point = (Point<T>) other;

        return comp.compare(this.coordinates, point.coordinates);
    }

    /**
     * @return
     */
    @Override
    protected
    EType getTypeCode () {
        return Geometry.TYPECODE_POINT;
    }

    /**
     * @return
     */
    public
    ICoordinateSequence getCoordinateSequence () {
        return coordinates;
    }

    @Override
    public
    int compareTo ( @NotNull Geometry <T> o ) {
        return 0;
    }

    @Override
    public
    int compareTo ( @NotNull T o ) {
        return 0;
    }
}

