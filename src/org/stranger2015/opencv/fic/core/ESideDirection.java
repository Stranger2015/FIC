package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;

import static java.lang.String.*;

public
enum ESideDirection implements ISideDirection {
    NORTH("N", 0),
    EAST("E", 1),
    SOUTH("S", 2),
    WEST("W", 3);

    private final String shortName;
    private final int ord;

    ESideDirection ( String shortName, int ord ) {
        this.shortName = shortName;
        this.ord = ord;
    }

    @NotNull
    @Override
    public
    EDirection reflect ( EDirection quadrant ) {
        switch (this) {
            case NORTH:
            case SOUTH:
                switch (quadrant) {
                    case NORTH_EAST:
                    case NORTH_WEST:
                    case SOUTH_WEST:
                        break;
                    case SOUTH_EAST:
                        return EDirection.NORTH_EAST;
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
                        return EDirection.SOUTH_WEST;
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
    EDirection quadrant ( EDirection side ) {
        switch (this) {
            case NORTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case EAST:
                        return EDirection.NORTH_EAST;
                    case WEST:
                        return EDirection.NORTH_WEST;
                    default:
                        throw new IllegalStateException(format("Unexpected value: %s", side));
                }
            case EAST:
                switch (side) {
                    case NORTH:
                        return EDirection.NORTH_EAST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case SOUTH:
                        return EDirection.SOUTH_EAST;
                }
                throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
            case SOUTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case EAST:
                        return EDirection.SOUTH_EAST;
                    case WEST:
                        return EDirection.SOUTH_WEST;
                    default:
                        throw new IllegalStateException(format("Unexpected value: %s", side));
                }
            case WEST:
                switch (side) {
                    case NORTH:
                        return EDirection.NORTH_WEST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException(format("illegal input - %s, %s", this, side));
                    case SOUTH:
                        return EDirection.SOUTH_WEST;
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
    boolean adjacent ( EDirection cornerDirection ) {
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
    EDirection cSide () {
        switch (this) {
            case NORTH:
                return EDirection.EAST;
            case EAST:
                return EDirection.SOUTH;
            case SOUTH:
                return EDirection.WEST;
            case WEST:
                return EDirection.NORTH;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
    }

    /**
     * @return
     */
    @Override
    public
    EDirection ccSide () {
        switch (this) {
            case NORTH:
                return EDirection.WEST;
            case WEST:
                return EDirection.SOUTH;
            case SOUTH:
                return EDirection.EAST;
            case EAST:
                return EDirection.NORTH;
            default:
                throw new IllegalStateException(format("Unexpected value: %s", this));
        }
    }

    /**
     * @return
     */
    @Override
    public
    EDirection opSide () {
        switch (this) {
            case NORTH:
                return EDirection.EAST;
            case EAST:
                return EDirection.SOUTH;
            case SOUTH:
                return EDirection.WEST;
            case WEST:
                return EDirection.NORTH;
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