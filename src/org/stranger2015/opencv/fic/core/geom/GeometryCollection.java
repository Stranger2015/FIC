package org.stranger2015.opencv.fic.core.geom;

import org.locationtech.jts.geom.CoordinateFilter;
import org.locationtech.jts.geom.GeometryComponentFilter;
import org.locationtech.jts.geom.GeometryFilter;

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
public class GeometryCollection extends Geometry {
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
        super(factory);
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
    public org.stranger2015.opencv.fic.core.geom.Coordinate[] getCoordinates() {
        org.stranger2015.opencv.fic.core.geom.Coordinate[] coordinates = new org.stranger2015.opencv.fic.core.geom.Coordinate[getNumPoints()];
        int k = -1;
        for (int i = 0; i < geometries.length; i++) {
            Coordinate[] childCoordinates = geometries[i].getCoordinates();
            for (int j = 0; j < childCoordinates.length; j++) {
                k++;
                coordinates[k] = childCoordinates[j];
            }
        }
        return coordinates;
    }

    public boolean isEmpty() {
        for (int i = 0; i < geometries.length; i++) {
            if (!geometries[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getDimension() {
        int dimension = Dimension.FALSE;
        for (int i = 0; i < geometries.length; i++) {
            dimension = Math.max(dimension, geometries[i].getDimension());
        }
        return dimension;
    }

    public int getBoundaryDimension() {
        int dimension = Dimension.FALSE;
        for (int i = 0; i < geometries.length; i++) {
            dimension = Math.max(dimension, geometries[i].getBoundaryDimension());
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
        int numPoints = 0;
        for (int i = 0; i < geometries.length; i++) {
            numPoints += geometries[i].getNumPoints();
        }
        return numPoints;
    }

    public String getGeometryType() {
        return Geometry.TYPENAME_GEOMETRYCOLLECTION;
    }

    public
    org.stranger2015.opencv.fic.core.geom.Geometry getBoundary() {
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
        double area = 0.0;
        for (int i = 0; i < geometries.length; i++) {
            area += geometries[i].getArea();
        }
        return area;
    }

    public double getLength()
    {
        double sum = 0.0;
        for (int i = 0; i < geometries.length; i++) {
            sum += (geometries[i]).getLength();
        }
        return sum;
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
    void apply ( org.stranger2015.opencv.fic.core.geom.CoordinateFilter filter ) {

    }

    @Override
    public
    void apply ( org.stranger2015.opencv.fic.core.geom.CoordinateSequenceFilter filter ) {

    }

    public void apply( CoordinateFilter filter) {
        for (int i = 0; i < geometries.length; i++) {
            geometries[i].apply(filter);
        }
    }

    public void apply( CoordinateSequenceFilter filter) {
        if (geometries.length == 0) {
            return;
        }
        for (int i = 0; i < geometries.length; i++) {
            geometries[i].apply(filter);
            if (filter.isDone()) {
                break;
            }
        }
        if (filter.isGeometryChanged())
            geometryChanged();
    }

    public void apply( GeometryFilter filter) {
        filter.filter(this);
        for (int i = 0; i < geometries.length; i++) {
            geometries[i].apply(filter);
        }
    }

    @Override
    public
    void apply ( GeometryComponentFilter filter ) {

    }

    public void apply(GeometryComponentFilter filter) {
        filter.filter(this);
        for (int i = 0; i < geometries.length; i++) {
            geometries[i].apply(filter);
        }
    }

    /**
     * Creates and returns a full copy of this {@link org.stranger2015.opencv.fic.core.geom.GeometryCollection} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    public Object clone() {
        return copy();
    }

    protected
    MultiLineString copyInternal() {
        Geometry[] geometries = new Geometry[this.geometries.length];
        for (int i = 0; i < geometries.length; i++) {
            geometries[i] = this.geometries[i].copy();
        }
        return new org.stranger2015.opencv.fic.core.geom.GeometryCollection(geometries, factory);
    }

    public void normalize() {
        for (int i = 0; i < geometries.length; i++) {
            geometries[i].normalize();
        }
        Arrays.sort(geometries);
    }

    protected
    org.stranger2015.opencv.fic.core.geom.Envelope computeEnvelopeInternal() {
        org.stranger2015.opencv.fic.core.geom.Envelope envelope = new Envelope();
        for (int i = 0; i < geometries.length; i++) {
            envelope.expandToInclude(geometries[i].getEnvelopeInternal());
        }
        return envelope;
    }

    protected int compareToSameClass(Object o) {
        TreeSet theseElements = new TreeSet(Arrays.asList(geometries));
        TreeSet otherElements = new TreeSet(Arrays.asList(((org.stranger2015.opencv.fic.core.geom.GeometryCollection) o).geometries));
        return compare(theseElements, otherElements);
    }

    protected int compareToSameClass(Object o, CoordinateSequenceComparator comp) {
        org.stranger2015.opencv.fic.core.geom.GeometryCollection gc = (org.stranger2015.opencv.fic.core.geom.GeometryCollection) o;

        int n1 = getNumGeometries();
        int n2 = gc.getNumGeometries();
        int i = 0;
        while (i < n1 && i < n2) {
            Geometry thisGeom = getGeometryN(i);
            Geometry otherGeom = gc.getGeometryN(i);
            int holeComp = thisGeom.compareToSameClass(otherGeom, comp);
            if (holeComp != 0) return holeComp;
            i++;
        }
        if (i < n1) return 1;
        if (i < n2) return -1;
        return 0;

    }

    protected int getTypeCode() {
        return Geometry.TYPECODE_GEOMETRYCOLLECTION;
    }

    /**
     * Creates a {@link org.stranger2015.opencv.fic.core.geom.GeometryCollection} with
     * every component reversed.
     * The order of the components in the collection are not reversed.
     *
     * @return a {@link org.stranger2015.opencv.fic.core.geom.GeometryCollection} in the reverse order
     */
    public
    GeometryCollection reverse() {
        return (GeometryCollection) super.reverse();
    }

    protected
    GeometryCollection reverseInternal()
    {
        Geometry[] geometries = new Geometry[this.geometries.length];
        for (int i = 0; i < geometries.length; i++) {
            geometries[i] = this.geometries[i].reverse();
        }
        return new GeometryCollection(geometries, factory);
    }
}

