package org.stranger2015.opencv.fic.core.geom;

/**
 * Models an OGC SFS <code>LinearRing</code>.
 * A <code>LinearRing</code> is a {@link LineString} which is both closed and simple.
 * In other words,
 * the first and last coordinate in the ring must be equal,
 * and the ring must not self-intersect.
 * Either orientation of the ring is allowed.
 * <p>
 * A ring must have either 0 or 3 or more points.
 * The first and last points must be equal (in 2D).
 * If these conditions are not met, the constructors throw
 * an {@link IllegalArgumentException}.
 * A ring with 3 points is invalid, because it is collapsed
 * and thus has a self-intersection.  It is allowed to be constructed
 * so that it can be represented, and repaired if needed.
 *
 * @version 1.8
 */
public class LinearRing extends LineString
{
    /**
     * The minimum number of vertices allowed in a valid non-empty ring.
     * Empty rings with 0 vertices are also valid.
     */
    public static final int MINIMUM_VALID_SIZE = 3;

    private static final long serialVersionUID = -4261142084085851829L;

    /**
     * Constructs a <code>LinearRing</code> with the given points.
     *
     *@param  points          points forming a closed and simple linestring, or
     *      <code>null</code> or an empty array to create the empty geometry.
     *      This array must not contain <code>null</code> elements.
     *
     *@param  precisionModel  the specification of the grid of allowable points
     *      for this <code>LinearRing</code>
     *@param  SRID            the ID of the Spatial Reference System used by this
     *      <code>LinearRing</code>
     * @throws IllegalArgumentException if the ring is not closed, or has too few points
     *
     * @deprecated Use GeometryFactory instead
     */
    public LinearRing( Coordinate[] points, PrecisionModel precisionModel, int SRID) {
        this(points, new GeometryFactory(precisionModel, SRID));
        validateConstruction();
    }

    /**
     * This method is ONLY used to avoid deprecation warnings.
     * @param points
     * @param factory
     * @throws IllegalArgumentException if the ring is not closed, or has too few points
     */
    private LinearRing( Coordinate[] points, GeometryFactory factory) {
        this(factory.getCoordinateSequenceFactory().create(points), new LinearRing[]{}, factory);
    }
    
    /**
     * Constructs a <code>LinearRing</code> with the vertices
     * specified by the given {@link ICoordinateSequence}.
     *
     * @param points      a sequence points forming a closed and simple linestring, or
     *                    <code>null</code> to create the empty geometry.
     * @param linearRings @throws IllegalArgumentException if the ring is not closed, or has too few points
     */
    public LinearRing( ICoordinateSequence points, LinearRing[] linearRings, GeometryFactory factory) {
        super(points, factory);
        
        validateConstruction();
    }

    private void validateConstruction() {
        if (!isEmpty() && ! super.isClosed()) {
            throw new IllegalArgumentException("Points of LinearRing do not form a closed linestring");
        }
        if (getCoordinateSequence().size() >= 1 && getCoordinateSequence().size() < MINIMUM_VALID_SIZE) {
            throw new IllegalArgumentException("Invalid number of points in LinearRing (found "
                    + getCoordinateSequence().size() + " - must be 0 or >= " + MINIMUM_VALID_SIZE + ")");
        }
    }

    /**
     * Returns <code>Dimension.FALSE</code>, since by definition LinearRings do
     * not have a boundary.
     *
     * @return Dimension.FALSE
     */
    public int getBoundaryDimension() {
        return Dimension.FALSE;
    }

    /**
     * Tests whether this ring is closed.
     * Empty rings are closed by definition.
     *
     * @return true if this ring is closed
     */
    public boolean isClosed() {
        if (isEmpty()) {
            // empty LinearRings are closed by definition
            return true;
        }
        
        return super.isClosed();
    }


    public String getGeometryType() {
        return Geometry.TYPENAME_LINEARRING;
    }

    protected
    EType getTypeCode() {
        return Geometry.TYPECODE_LINEARRING;
    }

    protected
    LinearRing copyInternal() {
        return new LinearRing(points.copy(), new LinearRing[]{}, factory);
    }

    public
    LinearRing reverse()
    {
        return (LinearRing) super.reverse();
    }

    @Override
    public
    Geometry <?> reverseInternal() {
        ICoordinateSequence seq = points.copy();
    
        CoordinateSequences.reverse(seq);

        return getFactory().createLinearRing(seq);
    }
}
