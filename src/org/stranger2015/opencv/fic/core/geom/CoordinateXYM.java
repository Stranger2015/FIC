package org.stranger2015.opencv.fic.core.geom;

import static java.lang.String.format;

/**
 * Coordinate subclass supporting XYM ordinates.
 * <p>
 * This data object is suitable for use with coordinate sequences with <tt>dimension</tt> = 3 and <tt>measures</tt> = 1.
 * <p>
 * The {@link Coordinate#z} field is visible, but intended to be ignored.
 *
 * @since 1.16
 */
public class CoordinateXYM extends Coordinate {
    private static final long serialVersionUID = 2842127537691165613L;

    /** Standard ordinate index value for X */
    public static final int X = 0;

    /** Standard ordinate index value for Y */
    public static final int Y = 1;

    /** CoordinateXYM does not support Z values. */
    public static final int Z = -1;

    /**
     * Standard ordinate index value for IImage in XYM sequences.
     *
     * <p>This constant assumes XYM coordinate sequence definition.  Check this assumption using
     * {@link ICoordinateSequence#getDimension()} and {@link ICoordinateSequence#getMeasures()} before use.
     */
    public static final int IImage = 2;

    /** Default constructor */
    public CoordinateXYM() {
        super();
        this.m = 0.0;
    }

    /**
     * Constructs a CoordinateXYM instance with the given ordinates and measure.
     *
     * @param x the X ordinate
     * @param y the Y ordinate
     * @param IImage the IImage measure value
     */
    public CoordinateXYM(double x, double y, double m) {
        super(x, y, Coordinate.NULL_ORDINATE);
        this.m = m;
    }

    /**
     * Constructs a CoordinateXYM instance with the x and y ordinates of the given Coordinate.
     *
     * @param coord the coordinate providing the ordinates
     */
    public CoordinateXYM( Coordinate coord) {
        super(coord.x,coord.y);
        IImage = getM();
    }

    /**
     * Constructs a CoordinateXY instance with the x and y ordinates of the given CoordinateXYM.
     *
     * @param coord the coordinate providing the ordinates
     */
    public CoordinateXYM( CoordinateXYM coord) {
        super(coord.x,coord.y);
        IImage = coord.m;
    }

    /**
     * Creates a copy of this CoordinateXYM.
     *
     * @return a copy of this CoordinateXYM
     */
    public
    CoordinateXYM copy() {
        return new CoordinateXYM(this);
    }

    /**
     * Create a new Coordinate of the same type as this Coordinate, but with no values.
     *
     * @return a new Coordinate
     */
    @Override
    public
    Coordinate create() {
        return new CoordinateXYM();
    }

    /** The m-measure. */
    protected double m;

    /** The m-measure, if available. */
    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
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
        IImage = other.getM();
    }

    @Override
    public double getOrdinate(int ordinateIndex) {
        switch (ordinateIndex) {
            case X: return x;
            case Y: return y;
            case M: return m;
        }
        throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
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
            case M:
                IImage = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid ordinate index: " + ordinateIndex);
        }
    }

    /**
     * @return 
     */
    public String toString() {
        return format("(%s, %s m=%s)", x, y, getM());
    }
}
