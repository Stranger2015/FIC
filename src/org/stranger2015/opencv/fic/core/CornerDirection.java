package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.Direction.*;

/**
 *
 */
public
enum CornerDirection implements ICornerDirection {
    NORTH_WEST("NW", 4),
    NORTH_EAST("NE", 5),
    SOUTH_EAST("SE", 6),
    SOUTH_WEST("SW", 7);

    private final String shortName;
    private final int ord;

    /**
     * @param shortName
     * @param ord
     */
    CornerDirection ( String shortName, int ord ) {
        this.shortName = shortName;
        this.ord = ord;
    }

    /**
     * @return
     */
    @Override
    public
    int northEast () {
        return NORTH_EAST.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    int southEast () {
        return SOUTH_EAST.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    int southWest () {
        return SOUTH_WEST.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    int northWest () {
        return NORTH_WEST.getOrd();
    }

    @Override
    public
    Direction opQuad () {
        switch (this) {
            case NORTH_EAST:
                return Direction.SOUTH_WEST;
            case SOUTH_EAST:
                return Direction.NORTH_WEST;
            case SOUTH_WEST:
                return Direction.NORTH_EAST;
            case NORTH_WEST:
                return Direction.SOUTH_EAST;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    /**
     * @param direction
     * @return
     */
    @Override
    public
    Direction commonSide ( Direction direction ) {
        switch (this) {
            case NORTH_EAST:
            case SOUTH_EAST:
                switch (direction) {
                    case NORTH_EAST:
                    case SOUTH_EAST:
                    case SOUTH_WEST:
                    case NORTH_WEST:
                        break;
                }
                break;
            case SOUTH_WEST:
            case NORTH_WEST:
                switch (direction) {
                    case NORTH_EAST:
                    case NORTH_WEST:
                    case SOUTH_WEST:
                    case SOUTH_EAST:
                        break;
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        throw new IllegalArgumentException("illegal input - " + this);
    }

    private
    Direction sideN ( Direction quadrant, boolean isOne ) {
        switch (quadrant) {
            case NORTH_WEST:
                if (isOne) {
                    return NORTH;
                }
                return WEST;
            case NORTH_EAST:
                if (isOne) {
                    return NORTH;
                }
                return EAST;
            case SOUTH_EAST:
                if (isOne) {
                    return SOUTH;
                }
                return EAST;

            case SOUTH_WEST:
                if (isOne) {
                    return SOUTH;
                }
                return WEST;
            default:
                throw new IllegalStateException(format("Wrong direction value <%s>. Only diagonal values allowed!",
                        quadrant));
        }
    }

    @Override
    public
    Direction side1 ( Direction quadrant ) {
        return sideN(quadrant, true);
    }

    @Override
    public
    Direction side2 ( Direction quadrant ) {
        return sideN(quadrant, false);
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int width ( Direction quadrant ) {
        return 0;
    }//todo

    @Override
    public
    int radius ( Direction quadrant ) {
        return 0;
    }//todo

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int xOf ( Direction quadrant ) {
        return 0;
    }//todo

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int yOf ( Direction quadrant ) {
        return 0;
    }//todo

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Object value ( Direction quadrant ) {
        return null;
    }//todo

    /**
     * @param x
     * @param y
     * @param width
     * @param color
     * @param d
     * @return
     */
    @Override
    public
    Direction insertReverseOrder ( int x, int y, int width, int color, int d ) {
        return null;
    }//todo

    /**
     * @return
     */
    @Override
    public
    EnumSet <Direction> toSideDirection () {
        EnumSet <Direction> set;
        switch (this) {
            case NORTH_EAST:
                set = EnumSet.of(NORTH, EAST);
                break;
            case SOUTH_EAST:
                set = EnumSet.of(SOUTH, EAST);
                break;
            case SOUTH_WEST:
                set = EnumSet.of(SOUTH, WEST);
                break;
            case NORTH_WEST:
                set = EnumSet.of(NORTH, WEST);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }

        return set;
    }

    /**
     * @return
     */
    public
    String getShortName () {
        return shortName;
    }

    /**
     * @return
     */
    public
    int getOrd () {
        return ord;
    }
}
