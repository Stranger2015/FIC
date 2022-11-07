package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.utils.Assert;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * Models a collection of {@link Geometry}s of
 * arbitrary type and dimension.
 *
 *
 *@version 1.7
 */
public class GeometryCollection extends Geometry<?> {
    //  With contributions from Markus Schaber [schabios@logi-track.com] 2004-03-26
    private static final long serialVersionUID = -5694727726395021467L;
    /**
     *  Internal representation of this <code>GeometryCollection</code>.
     */
    protected Geometry[] geometries;

    /** @deprecated Use GeometryFactory instead */
    public GeometryCollection( Geometry[] geometries, PrecisionModel precisionModel, int SRID) {
        this(geometries, new GeometryFactory(precisionModel, SRID));
    }


    /**
     * @param geometries
     *            the <code>Geometry</code>s for this <code>GeometryCollection</code>,
     *            or <code>null</code> or an empty array to create the empty
     *            geometry. Elements may be empty <code>Geometry</code>s,
     *            but not <code>null</code>s.
     */
    public GeometryCollection(Geometry[] geometries, GeometryFactory factory) {
        super(image, address, blockSize, factory);
        if (geometries == null) {
            geometries = new Geometry[]{};
        }
        if (hasNullElements(geometries)) {
            throw new IllegalArgumentException("geometries must not contain null elements");
        }
        this.geometries = geometries;
    }

    public
    Coordinate getCoordinate() {
        if (isEmpty()) {
            return null;
        }

        return geometries[0].getCoordinate();
    }

    /**
     * Collects all coordinates of all subgeometries into an Array.
     *
     * Note that while changes to the coordinate objects themselves
     * may modify the Geometries in place, the returned Array as such
     * is only a temporary container which is not synchronized back.
     *
     * @return the collected coordinates
     *    */
    public Coordinate[] getCoordinates() {
        Coordinate[] coordinates = new Coordinate[getNumPoints()];
        int k = -1;
        for (Geometry geometry : geometries) {
            Coordinate[] childCoordinates = geometry.getCoordinates();
            for (Coordinate childCoordinate : childCoordinates) {
                k++;
                coordinates[k] = childCoordinate;
            }
        }
        return coordinates;
    }

    public boolean isEmpty() {
        return IntStream.range(0, geometries.length).allMatch(i -> geometries[i].isEmpty());
    }

    public int getDimension() {
        int dimension = Dimension.FALSE;
        for (Geometry geometry : geometries) {
            dimension = Math.max(dimension, geometry.getDimension());
        }

        return dimension;
    }

    public int getBoundaryDimension() {
        int dimension = Dimension.FALSE;
        for (Geometry geometry : geometries) {
            dimension = Math.max(dimension, geometry.getBoundaryDimension());
        }

        return dimension;
    }

    public int getNumGeometries() {
        return geometries.length;
    }

    public Geometry getGeometryN(int n) {
        return geometries[n];
    }

    public int getNumPoints() {
        return Arrays.stream(geometries).mapToInt(Geometry::getNumPoints).sum();
    }

    public String getGeometryType() {
        return Geometry.TYPENAME_GEOMETRYCOLLECTION;
    }

    public
    Geometry<T> getBoundary() {
        checkNotGeometryCollection(this);
        Assert.shouldNeverReachHere();

        return null;
    }

    /**
     *  Returns the area of this <code>GeometryCollection</code>
     *
     * @return the area of the polygon
     */
    public double getArea()
    {
        return Arrays.stream(geometries).mapToDouble(Geometry::getArea).sum();
    }

    public double getLength()
    {
        return Arrays.stream(geometries).mapToDouble(Geometry::getLength).sum();
    }

    public boolean equalsExact(Geometry other, double tolerance) {
        if (isEquivalentClass(other)) {
            return false;
        }
        GeometryCollection otherCollection = (GeometryCollection) other;
        if (geometries.length != otherCollection.geometries.length) {
            return false;
        }
        return IntStream.range(0, geometries.length)
                .allMatch(i -> geometries[i].equalsExact(otherCollection.geometries[i], tolerance));
    }

    @Override
    public
    void apply ( ICoordinateFilter filter ) {
        IntStream.range(0, geometries.length)
                .forEachOrdered(i -> geometries[i].apply(filter));
    }

    /**
     * @param filter the filter to apply
     */
    @Override
    public void apply( ICoordinateSequenceFilter filter) {
        if (geometries.length != 0) {
            for (Geometry geometry : geometries) {
                geometry.apply(filter);
                if (filter.isDone()) {
                    break;
                }
            }
            if (filter.isGeometryChanged())
                geometryChanged();
        }
    }

    /**
     * @param filter the filter to apply to this <code>Geometry</code> (and
     */
    @Override
    public void apply( IGeometryFilter filter) {
        filter.filter(this);
        IntStream.range(0, geometries.length)
                .forEachOrdered(i -> geometries[i].apply(filter));
    }

    @Override
    public void apply(IGeometryComponentFilter filter) {
        filter.filter(this);
        IntStream.range(0, geometries.length).forEachOrdered(i -> geometries[i].apply(filter));
    }

    /**
     * Creates and returns a full copy of this {@link GeometryCollection} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    @Override
    public Object clone() {
//        Object clone = super.copy();
        return copy();///fixme
    }

    protected
    MultiLineString copyInternal() {
        Geometry[] geometries = new Geometry[this.geometries.length];
        Arrays.setAll(geometries, i -> this.geometries[i].copy());

        return new MultiLineString( geometries, factory);
    }

    public void normalize() {
        IntStream.range(0, geometries.length).forEachOrdered(i -> geometries[i].normalize());
        Arrays.sort(geometries);
    }

    protected
    Envelope computeEnvelopeInternal() {
        Envelope envelope = new Envelope();
        Arrays.stream(geometries).map(Geometry::getEnvelopeInternal)
                .forEachOrdered(envelope::expandToInclude);

        return envelope;
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

//    @Override
    protected
    <T extends Geometry<T>> int compareToSameClass ( T o ) {
        return 0;
    }

//    @Override
    protected
    <T extends Geometry<T>> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    protected int compareToSameClass(Object o) {
        TreeSet theseElements = new TreeSet(Arrays.asList(geometries));
        TreeSet otherElements = new TreeSet(Arrays.asList(((GeometryCollection) o).geometries));

        return compare(theseElements, otherElements);
    }

    protected int compareToSameClass(Object o, CoordinateSequenceComparator comp) {
        GeometryCollection gc = (GeometryCollection) o;

        int n1 = getNumGeometries();
        int n2 = gc.getNumGeometries();
        int i = 0;
        while (i < n1 && i < n2) {
            Geometry<T> thisGeom = getGeometryN(i);
            Geometry<T> otherGeom = gc.getGeometryN(i);
            int holeComp = thisGeom.compareToSameClass(otherGeom, comp);
            if (holeComp != 0) {
                return holeComp;
            }
            i++;
        }

        return i < n1 ? 1 : i < n2 ? -1 : 0;

    }

    protected
    EType getTypeCode() {
        return Geometry.TYPECODE_GEOMETRYCOLLECTION;
    }

    /**
     * Creates a {@link GeometryCollection} with
     * every component reversed.
     * The order of the components in the collection are not reversed.
     *
     * @return a {@link GeometryCollection} in the reverse order
     */
    public
    Geometry <?> reverse() {
        return (GeometryCollection) super.reverse();
    }

    @Override
    protected
    Geometry <?> reverseInternal()
    {
        Geometry<?>[] geometries = new Geometry[this.geometries.length];
        for (int i = 0; i < geometries.length; i++) {
            geometries[i] = this.geometries[i].reverse();
        }
        return new GeometryCollection(geometries, factory);
    }
}

