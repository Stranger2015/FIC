package org.stranger2015.opencv.fic.core.geom;

import org.locationtech.jts.geom.CoordinateFilter;
import org.locationtech.jts.geom.CoordinateSequenceComparator;
import org.stranger2015.opencv.fic.core.algorithm.Area;
import org.stranger2015.opencv.fic.core.algorithm.Orientation;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Represents a polygon with linear edges, which may include holes.
 * The outer boundary (shell)
 * and inner boundaries (holes) of the polygon are represented by {@link LinearRing}s.
 * The boundary rings of the polygon may have any orientation.
 * Polygons are closed, simple geometries by definition.
 * <p>
 * The polygon model conforms to the assertions specified in the
 * <A HREF="http://www.opengis.org/techno/specs.htm">OpenGIS Simple Features
 * Specification for SQL</A>.
 * <p>
 * A <code>Polygon</code> is topologically valid if and only if:
 * <ul>
 * <li>the coordinates which define it are valid coordinates
 * <li>the linear rings for the shell and holes are valid
 * (i.e. are closed and do not self-intersect)
 * <li>holes touch the shell or another hole at at most one point
 * (which implies that the rings of the shell and holes must not cross)
 * <li>the interior of the polygon is connected,
 * or equivalently no sequence of touching holes
 * makes the interior of the polygon disconnected
 * (i.e. effectively split the polygon into two pieces).
 * </ul>
 *
 * @version 1.7
 */
public
class Polygon
        extends Geometry
        implements IPolygonal {

    private static final long serialVersionUID = -3494792200821764533L;

    /**
     * The exterior boundary,
     * or <code>null</code> if this <code>Polygon</code>
     * is empty.
     */
    protected LinearRing shell = null;

    /**
     * The interior boundaries, if any.
     * This instance var is never null.
     * If there are no holes, the array is of zero length.
     */
    protected LinearRing[] holes;

    /**
     * Constructs a <code>Polygon</code> with the given exterior boundary.
     *
     * @param shell          the outer boundary of the new <code>Polygon</code>,
     *                       or <code>null</code> or an empty <code>LinearRing</code> if the empty
     *                       geometry is to be created.
     * @param precisionModel the specification of the grid of allowable points
     *                       for this <code>Polygon</code>
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>Polygon</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    Polygon ( LinearRing shell, PrecisionModel precisionModel, int SRID ) {
        this(shell, new LinearRing[]{}, new GeometryFactory(precisionModel, SRID));
    }

    /**
     * Constructs a <code>Polygon</code> with the given exterior boundary and
     * interior boundaries.
     *
     * @param shell          the outer boundary of the new <code>Polygon</code>,
     *                       or <code>null</code> or an empty <code>LinearRing</code> if the empty
     *                       geometry is to be created.
     * @param holes          the inner boundaries of the new <code>Polygon</code>
     *                       , or <code>null</code> or empty <code>LinearRing</code>s if the empty
     *                       geometry is to be created.
     * @param precisionModel the specification of the grid of allowable points
     *                       for this <code>Polygon</code>
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>Polygon</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    Polygon ( LinearRing shell, LinearRing[] holes, PrecisionModel precisionModel, int SRID ) {
        this(shell, holes, new GeometryFactory(precisionModel, SRID));
    }

    /**
     * Constructs a <code>Polygon</code> with the given exterior boundary and
     * interior boundaries.
     *
     * @param shell the outer boundary of the new <code>Polygon</code>,
     *              or <code>null</code> or an empty <code>LinearRing</code> if the empty
     *              geometry is to be created.
     * @param holes the inner boundaries of the new <code>Polygon</code>
     *              , or <code>null</code> or empty <code>LinearRing</code>s if the empty
     *              geometry is to be created.
     */
    public
    Polygon ( LinearRing shell, LinearRing[] holes, GeometryFactory factory ) {
        super(factory);
        if (shell == null) {
            shell = getFactory().createLinearRing();
        }
        if (holes == null) {
            holes = new LinearRing[]{};
        }
        if (hasNullElements(holes)) {
            throw new IllegalArgumentException("holes must not contain null elements");
        }
        if (shell.isEmpty() && hasNonEmptyElements(holes)) {
            throw new IllegalArgumentException("shell is empty but holes are not");
        }
        this.shell = shell;
        this.holes = holes;
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate getCoordinate () {
        return shell.getCoordinate();
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate[] getCoordinates () {
        Coordinate[] result;
        if (isEmpty()) {
            result = new Coordinate[]{};
        }
        else {
            Coordinate[] coordinates = new Coordinate[getNumPoints()];
            int k = -1;
            Coordinate[] shellCoordinates = shell.getCoordinates();
            for (Coordinate shellCoordinate : shellCoordinates) {
                k++;
                coordinates[k] = shellCoordinate;
            }
            for (LinearRing hole : holes) {
                Coordinate[] childCoordinates = hole.getCoordinates();
                for (Coordinate childCoordinate : childCoordinates) {
                    k++;
                    coordinates[k] = childCoordinate;
                }
            }
            result = coordinates;
        }
        return result;
    }

    /**
     * @return
     */
    @Override
    public
    int getNumPoints () {
        int numPoints = shell.getNumPoints();
        for (LinearRing hole : holes) {
            numPoints += hole.getNumPoints();
        }
        return numPoints;
    }

    public
    int getDimension () {
        return 2;
    }

    public
    int getBoundaryDimension () {
        return 1;
    }

    public
    boolean isEmpty () {
        return shell.isEmpty();
    }

    public
    boolean isRectangle () {
        if (getNumInteriorRing() != 0) {
            return false;
        }
        if (shell == null) {
            return false;
        }
        if (shell.getNumPoints() != 5) {
            return false;
        }

        CoordinateSequence seq = shell.getCoordinateSequence();

        // check vertices have correct values
        Envelope env = getEnvelopeInternal();
        for (int i = 0; i < 5; i++) {
            double x = seq.getX(i);
            if (!(x == env.getMinX() || x == env.getMaxX())) {
                return false;
            }
            double y = seq.getY(i);
            if (!(y == env.getMinY() || y == env.getMaxY())) {
                return false;
            }
        }

        // check vertices are in right order
        double prevX = seq.getX(0);
        double prevY = seq.getY(0);
        for (int i = 1; i <= 4; i++) {
            double x = seq.getX(i);
            double y = seq.getY(i);
            boolean xChanged = x != prevX;
            boolean yChanged = y != prevY;
            if (xChanged == yChanged) {
                return false;
            }
            prevX = x;
            prevY = y;
        }

        return true;
    }

    /**
     * @return
     */
    public
    LinearRing getExteriorRing () {
        return shell;
    }

    /**
     * @return
     */
    public
    int getNumInteriorRing () {
        return holes.length;
    }

    /**
     * @param n
     * @return
     */
    public
    LinearRing getInteriorRingN ( int n ) {
        return holes[n];
    }

    /**
     * @return
     */
    @Override
    public
    String getGeometryType () {
        return Geometry.TYPENAME_POLYGON;
    }

    /**
     * Returns the area of this <code>Polygon</code>
     *
     * @return the area of the polygon
     */
    public
    double getArea () {
        double area = 0.0;
        area += Area.ofRing(shell.getCoordinateSequence());
        for (LinearRing hole : holes) {
            area -= Area.ofRing(hole.getCoordinateSequence());
        }
        return area;
    }

    /**
     * Returns the perimeter of this <code>Polygon</code>
     *
     * @return the perimeter of the polygon
     */
    public
    double getLength () {
        double len = 0.0;
        len += shell.getLength();
        len += Arrays.stream(holes).mapToDouble(LineString::getLength).sum();

        return len;
    }

    /**
     * Computes the boundary of this geometry
     *
     * @return a lineal geometry (which may be empty)
     * @see Geometry#getBoundary
     */
    public
    Geometry getBoundary () {
        if (isEmpty()) {
            return getFactory().createMultiLineString();
        }
        LinearRing[] rings = new LinearRing[holes.length + 1];
        rings[0] = shell;
        IntStream.range(0, holes.length).forEachOrdered(i -> rings[i + 1] = holes[i]);
        // create LineString or MultiLineString as appropriate
        if (rings.length <= 1) {
            return getFactory().createLinearRing(rings[0].getCoordinateSequence());
        }

        return getFactory().createMultiLineString(rings);
    }

    /**
     * @return
     */
    @Override
    protected
    Envelope computeEnvelopeInternal () {
        return shell.getEnvelopeInternal();
    }

    /**
     * @param o   a <code>Geometry</code> having the same class as this <code>Geometry</code>
     * @param <T>
     * @return
     */
    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o ) {
        return 0;
    }

    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o, org.stranger2015.opencv.fic.core.geom.CoordinateSequenceComparator comp ) {
        return 0;
    }

    //    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

//    @Override
//    protected
//    <T extends Geometry> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
//        return 0;
//    }
//
    /**
     * @param o    a <code>Geometry</code> having the same class as this <code>Geometry</code>
     * @param comp a <code>CoordinateSequenceComparator</code>
     * @param <T>
     * @return
     */
//    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;//todo
    }

    public
    boolean equalsExact ( Geometry other, double tolerance ) {
        if (isEquivalentClass(other)) {
            return false;
        }
        Polygon otherPolygon = (Polygon) other;
        Geometry thisShell = shell;
        Geometry otherPolygonShell = otherPolygon.shell;
        if (!thisShell.equalsExact(otherPolygonShell, tolerance)) {
            return false;
        }
        if (holes.length != otherPolygon.holes.length) {
            return false;
        }

        return IntStream.range(0, holes.length)
                .allMatch(i -> ((Geometry) holes[i]).equalsExact(otherPolygon.holes[i], tolerance));
    }

    public
    void apply ( CoordinateFilter filter ) {
        shell.apply(filter);
        IntStream.range(0, holes.length).forEachOrdered(i -> holes[i].apply(filter));
    }

    public
    void apply ( CoordinateSequenceFilter filter ) {
        shell.apply(filter);
        if (!filter.isDone()) {
            for (LinearRing hole : holes) {
                hole.apply(filter);
                if (filter.isDone()) {
                    break;
                }
            }
        }
        if (filter.isGeometryChanged()) {
            geometryChanged();
        }
    }

    public
    void apply ( IGeometryFilter filter ) {
        filter.filter(this);
    }


    public
    void apply ( IGeometryComponentFilter filter ) {
        filter.filter(this);
        shell.apply(filter);
        for (int i = 0; i < holes.length; i++) {
            holes[i].apply(filter);
        }
    }

    /**
     * Creates and returns a full copy of this {@link Polygon} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    public
    Object clone () {

        return copy();
    }

    protected
    Polygon copyInternal () {
        LinearRing shellCopy = (LinearRing) shell.copy();
        LinearRing[] holeCopies = new LinearRing[this.holes.length];
        for (int i = 0; i < holes.length; i++) {
            holeCopies[i] = (LinearRing) holes[i].copy();
        }
        return new Polygon(shellCopy, holeCopies, factory);
    }

    public
    Geometry convexHull () {
        return getExteriorRing().convexHull();
    }

    public
    void normalize () {
        shell = normalized(shell, true);
        for (int i = 0; i < holes.length; i++) {
            holes[i] = normalized(holes[i], false);
        }
        Arrays.sort(holes);
    }

    protected
    int compareToSameClass ( Object o ) {
        Polygon poly = (Polygon) o;

        LinearRing thisShell = shell;
        LinearRing otherShell = poly.shell;
        int shellComp = thisShell.compareToSameClass(otherShell);
        if (shellComp != 0) return shellComp;

        int nHole1 = getNumInteriorRing();
        int nHole2 = ((Polygon) o).getNumInteriorRing();
        int i = 0;
        while (i < nHole1 && i < nHole2) {
            LinearRing thisHole = (LinearRing) getInteriorRingN(i);
            LinearRing otherHole = (LinearRing) poly.getInteriorRingN(i);
            int holeComp = thisHole.compareToSameClass(otherHole);
            if (holeComp != 0) return holeComp;
            i++;
        }
        if (i < nHole1) return 1;
        if (i < nHole2) return -1;
        return 0;
    }

    protected
    int compareToSameClass ( T poly, CoordinateSequenceComparator comp ) {
//        Polygon poly = (Polygon) o;

        LinearRing thisShell = shell;
        LinearRing otherShell = poly.shell;
        int shellComp = thisShell.compareToSameClass(otherShell, comp);
        if (shellComp != 0) return shellComp;

        int nHole1 = getNumInteriorRing();
        int nHole2 = poly.getNumInteriorRing();
        int i = 0;
        while (i < nHole1 && i < nHole2) {
            LinearRing thisHole = (LinearRing) getInteriorRingN(i);
            LinearRing otherHole = (LinearRing) poly.getInteriorRingN(i);
            int holeComp = thisHole.compareToSameClass(otherHole, comp);
            if (holeComp != 0) return holeComp;
            i++;
        }
        if (i < nHole1) return 1;
        if (i < nHole2) return -1;
        return 0;
    }

    protected
    int getTypeCode () {
        return Geometry.TYPECODE_POLYGON;
    }

    private
    LinearRing normalized ( LinearRing ring, boolean clockwise ) {
        LinearRing res = (LinearRing) ring.copy();
        normalize(res, clockwise);
        return res;
    }

    private
    void normalize ( LinearRing ring, boolean clockwise ) {
        if (ring.isEmpty()) {
            return;
        }

        CoordinateSequence seq = ring.getCoordinateSequence();
        int minCoordinateIndex = CoordinateSequences.minCoordinateIndex(seq, 0, seq.size() - 2);
        CoordinateSequences.scroll(seq, minCoordinateIndex, true);
        if (Orientation.isCCW(seq) == clockwise)
            CoordinateSequences.reverse(seq);
    }

    public
    Polygon reverse () {
        return (Polygon) super.reverse();
    }

    protected
    Polygon reverseInternal () {
        LinearRing shell = (LinearRing) getExteriorRing().reverse();
        LinearRing[] holes = new LinearRing[getNumInteriorRing()];
        for (int i = 0; i < holes.length; i++) {
            holes[i] = (LinearRing) getInteriorRingN(i).reverse();
        }

        return getFactory().createPolygon(shell, holes);
    }
}

