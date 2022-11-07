package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.core.algorithm.Length;
import org.stranger2015.opencv.fic.core.operation.BoundaryOp;
import org.stranger2015.opencv.fic.core.operation.ILineal;

/**
 *  Models an OGC-style <code>LineSt¶ring</code>.
 *  A LineString consists of a sequence of two or more vertices,
 *  along with all points along the linearly-interpolated curves
 *  (line segments) between each
 *  pair of consecutive vertices.
 *  Consecutive vertices may be equal.
 *  The line segments in the line may intersect each other (in other words,
 *  the linestring may "curl back" in itself and self-intersect.
 *  Linestrings with exactly two identical points are invalid.
 *  <p>
 * A linestring must have either 0 or 2 or more points.
 * If these conditions are not met, the constructors throw
 * an {@link IllegalArgumentException}
 *
 *@version 1.8
 */
public class LineString<T extends LineString<T>>
        extends Geometry<T>
        implements ILineal {
    
    private static final long serialVersionUID = 3110669828065365560L;
    /**
     *  The points of this <code>LineString</code>.
     */
    protected ICoordinateSequence points;

    /**
     *  Constructs a <code>LineString</code> with the given points.
     *
     *@param  points the points of the linestring, or <code>null</code>
     *      to create the empty geometry. This array must not contain <code>null</code>
     *      elements. Consecutive points may be equal.
     *@param  precisionModel  the specification of the grid of allowable points
     *      for this <code>LineString</code>
     *@param  SRID            the ID of the Spatial Reference System used by this
     *      <code>LineString</code>
     * @throws IllegalArgumentException if too few points are provided
     */
    /** @deprecated Use GeometryFactory instead */
    public LineString( Coordinate[] points, PrecisionModel precisionModel, int SRID)
    {
        super(image, address, blockSize, new GeometryFactory(precisionModel, SRID));

        init(getFactory().getCoordinateSequenceFactory().create(points));
    }

    /**
     * Constructs a <code>LineString</code> with the given points.
     *
     *@param  points the points of the linestring, or <code>null</code>
     *      to create the empty geometry.
     * @throws IllegalArgumentException if too few points are provided
     */
    public LineString( ICoordinateSequence points, GeometryFactory factory) {
        super(image, address, blockSize, factory);
        init(points);
    }

    private void init( ICoordinateSequence points)
    {
        if (points == null) {

            points = getFactory().getCoordinateSequenceFactory().create(new Coordinate[]{});
        }
        if (points.size() == 1) {
            throw new IllegalArgumentException("Invalid number of points in LineString (found "
                    + points.size() + " - must be 0 or >= 2)");
        }
        this.points = points;
    }
    public Coordinate[] getCoordinates() {
        return points.toCoordinateArray();
    }

    public
    ICoordinateSequence getCoordinateSequence() {
        return points;
    }

    public
   Coordinate getCoordinateN( int n) {
        return points.getCoordinate(n);
    }

    public
   Coordinate getCoordinate()
    {
        if (isEmpty()) return null;
        return points.getCoordinate(0);
    }

    public int getDimension() {
        return 1;
    }

    public int getBoundaryDimension() {
        if (isClosed()) {
            return Dimension.FALSE;
        }

        return 0;
    }

    public boolean isEmpty() {
        return points.size() == 0;
    }

    public int getNumPoints() {
        return points.size();
    }

    public
    Point<T> getPointN( int n) {
        return (Point <T>) getFactory().createPoint(points.getCoordinate(n));
    }

    public
   Point<T> getStartPoint() {
        if (isEmpty()) {
            return null;
        }

        return getPointN(0);
    }

    public
    Point getEndPoint() {
        if (isEmpty()) {
            return null;
        }
        return getPointN(getNumPoints() - 1);
    }

    public boolean isClosed() {
        if (isEmpty()) {
            return false;
        }

        return getCoordinateN(0).equals2D(getCoordinateN(getNumPoints() - 1));
    }

    public boolean isRing() {
        return isClosed() && isSimple();
    }

    public String getGeometryType() {
        return Geometry.TYPENAME_LINESTRING;
    }

    /**
     *  Returns the length of this <code>LineString</code>
     *
     *@return the length of the linestring
     */
    public double getLength()
    {
        return Length.ofLine(points);
    }

    /**
     * Gets the boundary of this geometry.
     * The boundary of a lineal geometry is always a zero-dimensional geometry (which may be empty).
     *
     * @return the boundary geometry
     * @seeGeometry#getBoundary
     */
    public
   Geometry<T> getBoundary() {
        return (new BoundaryOp(this)).getBoundary();
    }

    /**
     * Creates a {@linkLineString} whose coordinates are in the reverse
     * order of this objects
     *
     * @return a {@link LineString} with coordinates in the reverse order
     */
    public
    Geometry <T> reverse() {
        return (LineString<T>) super.reverse();
    }

    @Override
    protected
    Geometry <?> reverseInternal()
    {
        ICoordinateSequence seq = points.copy();
        CoordinateSequences.reverse(seq);

        return getFactory().createLineString(seq);
    }

    @Override
    public
    void apply ( ICoordinateFilter filter ) {

    }

    @Override
    public
    void apply ( ICoordinateSequenceFilter filter ) {

    }

//    @Override
//    public
//    void apply ( IGeometryFilter filter ) {
//
//    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {

    }

//    @Override
//    public
//    boolean equalsExact ( Geometry <T> other, double tolerance ) {
//        return false;
//    }

    /**
     *  Returns true if the given point is a vertex of this <code>LineString</code>.
     *
     *@param  pt  the <code>Coordinate</code> to check
     *@return     <code>true</code> if <code>pt</code> is one of this <code>LineString</code>
     *      's vertices
     */
    public boolean isCoordinate( Coordinate pt) {
        for (int i = 0; i < points.size(); i++) {
            if (points.getCoordinate(i).equals(pt)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected
    Envelope computeEnvelopeInternal() {
        if (isEmpty()) {
            return new Envelope();
        }
        return points.expandEnvelope(new Envelope());
    }

    @Override
    protected
    int compareToSameClass ( T o ) {
        return 0;
    }


    @Override public int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    public boolean equalsExact( Geometry<T> other, double tolerance) {
        if (!isEquivalentClass(other)) {
            return false;
        }
       LineString<T> otherLineString = (LineString<T>) other;
        if (points.size() != otherLineString.points.size()) {
            return false;
        }
        for (int i = 0; i < points.size(); i++) {
            if (!equal(points.getCoordinate(i), otherLineString.points.getCoordinate(i), tolerance)) {
                return false;
            }
        }
        return true;
    }
//
//    public void apply( ICoordinateFilter filter) {
//        for (int i = 0; i < points.size(); i++) {
//            filter.filter(points.getCoordinate(i));
//        }
//    }

//    public void apply( ICoordinateSequenceFilter filter)
//    {
//        if (points.size() == 0)
//            return;
//        for (int i = 0; i < points.size(); i++) {
//            filter.filter(points, i);
//            if (filter.isDone())
//                break;
//        }
//        if (filter.isGeometryChanged())
//            geometryChanged();
//    }

    public void apply( IGeometryFilter filter) {
        filter.filter(this);
    }


    /**
     * Creates and returns a full copy of this {@linkLineString} object.
     * (including all coordinates contained by it).
     *
     * @return a clone of this instance
     * @deprecated
     */
    public Object clone() {
        return copy();
    }

    protected
   LineString<T> copyInternal() {
        return new LineString<>(points.copy(), factory);
    }

    /**
     * Normalizes a LineString.  A normalized linestring
     * has the first point which is not equal to it's reflected point
     * less than the reflected point.
     */
    public void normalize()
    {
        for (int i = 0; i < points.size() / 2; i++) {
            int j = points.size() - 1 - i;
            // skip equal points on both ends
            if (!points.getCoordinate(i).equals(points.getCoordinate(j))) {
                if (points.getCoordinate(i).compareTo(points.getCoordinate(j)) > 0) {
                    ICoordinateSequence copy = points.copy();
                    CoordinateSequences.reverse(copy);
                    points = copy;
                }
                return;
            }
        }
    }

    protected boolean isEquivalentClass( Geometry<T> other) {
        return other instanceof LineString;
    }

    protected int compareToSameClass(Object o)
    {
       LineString line = (LineString) o;
        // MD - optimized implementation
        int i = 0;
        int j = 0;
        while (i < points.size() && j < line.points.size()) {
            int comparison = points.getCoordinate(i).compareTo(line.points.getCoordinate(j));
            if (comparison != 0) {
                return comparison;
            }
            i++;
            j++;
        }
        if (i < points.size()) {
            return 1;
        }
        if (j < line.points.size()) {
            return -1;
        }
        return 0;
    }

    protected int compareToSameClass(Object o, CoordinateSequenceComparator comp)
    {
       LineString line = (LineString) o;
        return comp.compare(this.points, line.points);
    }

    protected
    EType getTypeCode() {
        return Geometry.TYPECODE_LINESTRING;
    }
}