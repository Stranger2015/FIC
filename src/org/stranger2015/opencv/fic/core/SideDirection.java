package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;

import static java.lang.String.*;

public
enum SideDirection implements ISideDirection {
    NORTH("N", 0),
    EAST("E", 1),
    SOUTH("S", 2),
    WEST("W", 3);

    private final String shortName;
    private final int ord;

    SideDirection ( String shortName, int ord ) {
        this.shortName = shortName;
        this.ord = ord;
    }

    @NotNull
    @Override
    public
    Direction reflect ( Direction quadrant ) {
        switch (this) {
            case NORTH:
            case SOUTH:
                switch (quadrant) {
                    case NORTH_EAST:
                    case NORTH_WEST:
                    case SOUTH_WEST:
                        break;
                    case SOUTH_EAST:
                        return Direction.NORTH_EAST;
                }
                break;
            case EAST:
            case WEST:
                switch (quadrant) {
                    case NORTH_EAST:
                    case SOUTH_WEST:
                    case NORTH_WEST:
                        break;
                    case SOUTH_EAST:
                        return Direction.SOUTH_WEST;
                }
                break;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
        throw new IllegalStateException(format("Unexpected value: %s", this));
    }

    /**
     * @param side
     * @return
     */
    @Override
    public
    Direction quadrant ( Direction side ) {
        switch (this) {
            case NORTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case EAST:
                        return Direction.NORTH_EAST;
                    case WEST:
                        return Direction.NORTH_WEST;
                    default:
                        throw new IllegalStateException(format("Unexpected value: %s", side));
                }
            case EAST:
                switch (side) {
                    case NORTH:
                        return Direction.NORTH_EAST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case SOUTH:
                        return Direction.SOUTH_EAST;
                }
                throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
            case SOUTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case EAST:
                        return Direction.SOUTH_EAST;
                    case WEST:
                        return Direction.SOUTH_WEST;
                    default:
                        throw new IllegalStateException(format("Unexpected value: %s", side));
                }
            case WEST:
                switch (side) {
                    case NORTH:
                        return Direction.NORTH_WEST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case SOUTH:
                        return Direction.SOUTH_WEST;
                    default:
                        throw new IllegalStateException(format("Unexpected value: %s", side));
                }
            default:
                throw new IllegalStateException(format("Unexpected value: %s", side));
        }
    }

    /**
     * return true if and only if quadrant Z is adjacent to boundary B of the node’s block,
     * e.g., ADJ(E, SE) is true.
     *
     * @param cornerDirection
     * @return
     */
    @Override
    public
    boolean adjacent ( Direction cornerDirection ) {
        return cornerDirection.toSideDirection().contains(this);
    }

    @Override
    public
    int north () {
        return NORTH.getOrd();
    }

    @Override
    public
    int east () {
        return EAST.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    int south () {
        return SOUTH.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    int west () {
        return WEST.getOrd();
    }

    /**
     * @return
     */
    @Override
    public
    Direction cSide () {
        switch (this) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
    }

    /**
     * @return
     */
    @Override
    public
    Direction ccSide () {
        switch (this) {
            case NORTH:
                return Direction.WEST;
            case WEST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
    }

    /**
     * @return
     */
    @Override
    public
    Direction opSide () {
        switch (this) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            case WEST:
                return Direction.NORTH;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
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