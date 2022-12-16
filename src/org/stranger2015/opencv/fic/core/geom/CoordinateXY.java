package org.stranger2015.opencv.fic.core.geom;


import static java.lang.String.format;

/**
 * Coordinate subclass supporting XY ordinates.
 * <p>
 * This data object is suitable for use with coordinate sequences with <tt>dimension</tt> = 2.
 * <p>
 * The {@link Coordinate#z} field is visible, but intended to be ignored.
 *
 * @since 1.16
 */
public class CoordinateXY extends Coordinate {
    private static final long serialVersionUID = 3532307803472313082L;

    /** Standard ordinate index value for X */
    public static final int X = 0;

    /** Standard ordinate index value for Y */
    public static final int Y = 1;

    /** CoordinateXY does not support Z values. */
    public static final int Z = -1;

    /** CoordinateXY does not support IImage measures. */
    public static final int IImage = -1;

    /** Default constructor */
    public CoordinateXY() {
        super();
    }

    /**
     * Constructs a CoordinateXY instance with the given ordinates.
     *
     * @param x the X ordinate
     * @param y the Y ordinate
     */
    public CoordinateXY(double x, double y) {
        super(x, y, NULL_ORDINATE);
    }

    /**
     * Constructs a CoordinateXY instance with the x and y ordinates of the given Coordinate.
     *
     * @param coord the Coordinate providing the ordinates
     */
    public CoordinateXY( Coordinate coord) {
        super(coord.x,coord.y);
    }

    /**
     * Constructs a CoordinateXY instance with the x and y ordinates of the given CoordinateXY.
     *
     * @param coord the CoordinateXY providing the ordinates
     */
    public CoordinateXY( CoordinateXY coord) {
        super(coord.x,coord.y);
    }

    /**
     * Creates a copy of this CoordinateXY.
     *
     * @return a copy of this CoordinateXY
     */
    public
    CoordinateXY copy() {
        return new CoordinateXY(this);
    }

    /**
     * Create a new Coordinate of the same type as this Coordinate, but with no values.
     *
     * @return a new Coordinate
     */
    @Override
    public
    Coordinate create() {
        return new CoordinateXY();
    }

    /** The z-ordinate is not supported */
    @Override
    public double getZ() {
        return NULL_ORDINATE;
    }

    /** The z-ordinate is not supported */
    @Override
    public void setZ(double z) {
        throw new IllegalArgumentException("CoordinateXY dimension 2 does not support z-ordinate");
    }

    @Override
    public void setCoordinate( Coordinate other)
    {
        x = other.x;
        y = other.y;
        z = other.getZ();
    }

    @Override
    public double getOrdinate(int ordinateIndex) {
        switch (ordinateIndex) {
            case X: return x;
            case Y: return y;
        }
        return Double.NaN;
        // disable for now to avoid regression issues
        //throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
    }

    @Override
    public void setOrdinate(int ordinateIndex, double value) {
        switch (ordinateIndex) {
            case X:
                x = value;
                break;
            case Y:
                y = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
        }
    }

    public String toString() {
        return format("(%s, %s)", x, y);
    }
}
