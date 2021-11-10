package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

import static org.stranger2015.opencv.fic.core.SideDirection.*;

public
enum CornerDirection implements ICornerDirection {
    NORTH_WEST("NW", 4),
    NORTH_EAST("NE", 5),
    SOUTH_EAST("SE", 6),
    SOUTH_WEST("SW", 7);

    private final String shortName;
    private final int ord;

    CornerDirection ( String shortName, int ord ) {

        this.shortName = shortName;
        this.ord = ord;
    }

    @Override
    public
    int northEast () {
        return NORTH_EAST.ordinal();
    }

    @Override
    public
    int southEast () {
        return SOUTH_EAST.ordinal();
    }

    @Override
    public
    int southWest () {
        return SOUTH_WEST.ordinal();
    }

    @Override
    public
    int northWest () {
        return NORTH_WEST.ordinal();
    }

    //====================================

    @Override
    public
    CornerDirection opQuad () {
        switch (this) {
            case NORTH_EAST:
                return SOUTH_WEST;
            case SOUTH_EAST:
                return NORTH_WEST;
            case SOUTH_WEST:
                return NORTH_EAST;
            case NORTH_WEST:
                return SOUTH_EAST;
        }
        throw new IllegalArgumentException("" + this);

    }

    @Override
    public
    SideDirection commonSide ( CornerDirection direction2 ) {
        switch (this) {
            case NORTH_EAST:
            case SOUTH_EAST:
                switch (direction2) {
                    case NORTH_EAST:
                    case SOUTH_EAST:
                    case SOUTH_WEST:
                    case NORTH_WEST:
                        break;
                }
                break;
            case SOUTH_WEST:
            case NORTH_WEST:
                switch (direction2) {
                    case NORTH_EAST:
                    case NORTH_WEST:
                    case SOUTH_WEST:
                    case SOUTH_EAST:
                        break;
                }
                break;

        }
        throw new IllegalArgumentException("illegal input - " + this);
    }

    /**
     * @return
     */
    @Override
    public
    EnumSet <SideDirection> toSideDirection () {
        EnumSet <SideDirection> set = EnumSet.noneOf(SideDirection.class);
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
        }

        return set;
    }

    public
    String getShortName () {
        return shortName;
    }

    public
    int getOrd () {
        return ord;
    }
}
