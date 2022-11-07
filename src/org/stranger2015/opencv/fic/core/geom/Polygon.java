package org.stranger2015.opencv.fic.core.geom;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.algorithm.Orientation;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.stranger2015.opencv.fic.core.algorithm.Area.ofRing;

/**
 * Represents a polygon with linear edges, which may include holes.
 * The outer boundary (shell)  and inner boundaries (holes) of the polygon are represented by {@link LinearRing}s.
 * The boundary rings of the polygon may have any orientation. Polygons are closed, simple geometries by definition.
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
class Polygon<T extends Polygon<T>>
        extends Geometry<T>
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
        if (shell.isEmpty() && Geometry.hasNonEmptyElements(holes)) {
            throw new IllegalArgumentException("shell is empty but holes are not");
        }
        this.shell = shell;
        this.holes = holes;
    }

    /**
     * @param shell
     */
    public
    Polygon ( LinearRing shell) {
        this(shell,  new LinearRing[0],new GeometryFactory());
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

        ICoordinateSequence seq = shell.getCoordinateSequence();

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
        return EType.POLYGON.getName();
    }

    /**
     * Returns the area of this <code>Polygon</code>
     *
     * @return the area of the polygon
     */
    public
    double getArea () {
        double area = 0.0;
        area += ofRing(shell.getCoordinateSequence());
        for (LinearRing hole : holes) {
            area -= ofRing(hole.getCoordinateSequence());
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
    Geometry<T> getBoundary () {
        if (isEmpty()) {
            return getFactory().createMultiLineString().getBoundary();
        }
        LinearRing[] rings = new LinearRing[holes.length + 1];
        rings[0] = shell;
        IntStream.range(0, holes.length).forEachOrdered(i -> rings[i + 1] = holes[i]);
        // create LineString or MultiLineString as appropriate
        if (rings.length == 1) {
            return getFactory().createLinearRing(rings[0].getCoordinateSequence());
        }

        return getFactory().createMultiLineString(rings).getBoundary();
    }

    /**
     * @return
     */
    @Override
    protected
    Envelope computeEnvelopeInternal () {
        return shell.getEnvelopeInternal();
    }


    @Override
    public
    boolean equalsExact ( Geometry<T> other, double tolerance ) {
        if (isEquivalentClass(other)) {
            return false;
        }
        Polygon <T> otherPolygon = (Polygon <T>) other;
        Geometry<T> thisShell = shell;
        Geometry<T> otherPolygonShell = otherPolygon.shell;
        if (!thisShell.equalsExact(otherPolygonShell, tolerance)) {
            return false;
        }
        if (holes.length != otherPolygon.holes.length) {
            return false;
        }

        return IntStream.range(0, holes.length)
                .allMatch(i -> ((Geometry<T>) holes[i])
                        .equalsExact(otherPolygon.holes[i], tolerance));
    }

    @Override
    public
    void apply ( ICoordinateFilter filter ) {
        shell.apply(filter);
        IntStream.range(0, holes.length).forEachOrdered(i -> holes[i].apply(filter));
    }

    @Override
    public
    void apply ( ICoordinateSequenceFilter filter ) {
        if (filter.isGeometryChanged()) {
            geometryChanged();
        }
        shell.apply(filter);
        if (!filter.isDone()) {
            for (LinearRing hole : holes) {
                hole.apply(filter);
                if (filter.isDone()) {
                    break;
                }
            }
        }
    }

    public
    void apply ( IGeometryFilter filter ) {
        filter.filter(this);
    }


    /**
     * @param filter the filter to apply to this <code>Geometry</code>.
     */
    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {
        filter.filter(this);
        shell.apply(filter);
        IntStream.range(0, holes.length).forEachOrdered(i -> holes[i].apply(filter));
    }

    /**
     * Creates and returns a full copy of this {@link Polygon} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    @Override
    public
    Object clone () {
        Object clone = super.clone();
        return copy();
    }

    protected
    Polygon <T> copyInternal () {
        LinearRing shellCopy = (LinearRing) shell.copy();
        LinearRing[] holeCopies = IntStream.range(0, this.holes.length).mapToObj(i -> (LinearRing) holes[i].copy())
                .toArray(LinearRing[]::new);

        return new Polygon <>(shellCopy, holeCopies, factory);
    }

    public
    Geometry<T> convexHull () {
        return getExteriorRing().convexHull();
    }

    public
    void normalize () {
        shell = normalized(shell, true);
        Arrays.setAll(holes, i -> normalized(holes[i], false));
        Arrays.sort(holes);
    }

    protected
    int compareToSameClass ( T poly ) {
        int result = 0;

        LinearRing thisShell = shell;
        LinearRing otherShell = poly.shell;
        int shellComp = thisShell.compareToSameClass(otherShell);
        if (shellComp != 0) {
            result = shellComp;
        }
        else {
            int nHole1 = getNumInteriorRing();
            int nHole2 = poly.getNumInteriorRing();
            int i = 0;
            if (i < nHole1 && i < nHole2) {
                LinearRing thisHole = getInteriorRingN(i);
                LinearRing otherHole = poly.getInteriorRingN(i);
                int holeComp = thisHole.compareToSameClass(otherHole);
                if (holeComp != 0) {
                    result = holeComp;
                }
                else {
                    i++;
                    while (i < nHole1 && i < nHole2) {
                        thisHole = getInteriorRingN(i);
                        otherHole = poly.getInteriorRingN(i);
                        holeComp = thisHole.compareToSameClass(otherHole);
                        if (holeComp != 0) {
                            result = holeComp;
                            break;
                        }
                        i++;
                    }
                }
            }
            if (result == 0) {
                if (i < nHole1) {
                    result = 1;
                }
                else if (i < nHole2) {
                    result = -1;
                }
            }
        }

        return result;
    }

    /**
     * @param poly a <code>Geometry</code> having the same class as this <code>Geometry</code>
     * @param comp a <code>CoordinateSequenceComparator</code>
     * @return
     */
    @Override
    protected
    int compareToSameClass ( @NotNull T poly, CoordinateSequenceComparator comp ) {
        int result = 0;

        LinearRing thisShell = shell;
        LinearRing otherShell = poly.shell;
        int shellComp = thisShell.compareToSameClass(otherShell, comp);
        if (shellComp != 0) {
            result = shellComp;
        }
        else {
            int nHole1 = getNumInteriorRing();
            int nHole2 = poly.getNumInteriorRing();
            int i = 0;
            while (i < nHole1 && i < nHole2) {
                LinearRing thisHole = getInteriorRingN(i);
                LinearRing otherHole = poly.getInteriorRingN(i);
                int holeComp = thisHole.compareToSameClass(otherHole, comp);
                if (holeComp != 0) {
                    result = holeComp;
                    break;
                }
                i++;
            }
            if (result == 0) {
                result = i < nHole1 ? 1 : i < nHole2 ? -1 : 0;
            }
        }

        return result;
    }

    /**
     * @return
     */
    @Override
    protected
    EType getTypeCode () {
        return EType.POLYGON;
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

        ICoordinateSequence seq = ring.getCoordinateSequence();
        int minCoordinateIndex = CoordinateSequences.minCoordinateIndex(seq, 0, seq.size() - 2);
        CoordinateSequences.scroll(seq, minCoordinateIndex, true);
        if (Orientation.isCCW(seq) == clockwise) {
            CoordinateSequences.reverse(seq);
        }
    }

    public
    Polygon <T> reverse () {
        return (Polygon <T>) super.reverse();
    }

    @Override
    protected
    Geometry <T> reverseInternal () {
        LinearRing shell = getExteriorRing().reverse();
        LinearRing[] holes = new LinearRing[getNumInteriorRing()];
        Arrays.setAll(holes, i -> getInteriorRingN(i).reverse());

        return getFactory().createPolygon(shell, holes).getExteriorRing();
    }
}

