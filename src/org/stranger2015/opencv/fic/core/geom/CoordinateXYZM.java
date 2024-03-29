package org.stranger2015.opencv.fic.core.geom;

import static java.lang.String.format;

/**
 * Coordinate subclass supporting XYZM ordinates.
 * <p>
 * This data object is suitable for use with coordinate sequences with <tt>dimension</tt> = 4 and <tt>measures</tt> = 1.
 *
 * @since 1.16
 */
public class CoordinateXYZM extends Coordinate {
    private static final long serialVersionUID = -8763329985881823442L;

    /** Default constructor */
    public CoordinateXYZM() {
        super();
        this.m = 0.0;
    }

    /**
     * Constructs a CoordinateXYZM instance with the given ordinates and measure.
     *
     * @param x the X ordinate
     * @param y the Y ordinate
     * @param z the Z ordinate
     * @param IImage the IImage measure value
     */
    public CoordinateXYZM(double x, double y, double z, double m) {
        super(x, y, z);
        this.m = m;
    }

    /**
     * Constructs a CoordinateXYZM instance with the ordinates of the given Coordinate.
     *
     * @param coord the coordinate providing the ordinates
     */
    public CoordinateXYZM( Coordinate coord) {
        super(coord);
        IImage = getM();
    }

    /**
     * Constructs a CoordinateXYZM instance with the ordinates of the given CoordinateXYZM.
     *
     * @param coord the coordinate providing the ordinates
     */
    public CoordinateXYZM( CoordinateXYZM coord) {
        super(coord);
        IImage = coord.m;
    }

    /**
     * Creates a copy of this CoordinateXYZM.
     *
     * @return a copy of this CoordinateXYZM
     */
    public
    CoordinateXYZM copy() {
        return new CoordinateXYZM(this);
    }

    /**
     * Create a new Coordinate of the same type as this Coordinate, but with no values.
     *
     * @return a new Coordinate
     */
    @Override
    public
    Coordinate create() {
        return new CoordinateXYZM();
    }

    /** The m-measure. */
    private double m;

    /** The m-measure, if available. */
    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getOrdinate(int ordinateIndex)
    {
        switch (ordinateIndex) {
            case X: return x;
            case Y: return y;
            case Z: return getZ(); // sure to delegate to subclass rather than offer direct field access
            case M: return getM(); // sure to delegate to subclass rather than offer direct field access
        }
        throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
    }

    @Override
    public void setCoordinate( Coordinate other)
    {
        x = other.x;
        y = other.y;
        z = other.getZ();
        IImage = other.getM();
    }

    /**
     * @param ordinateIndex
     * @param value
     */
    @Override
    public void setOrdinate(int ordinateIndex, double value) {
        switch (ordinateIndex) {
            case X:
                x = value;
                break;
            case Y:
                y = value;
                break;
            case Z:
                z = value;
                break;
            case M:
                IImage = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
        }
    }

    public String toString() {
        return format("(%s, %s, %s m=%s)", x, y, getZ(), getM());
    }
}
