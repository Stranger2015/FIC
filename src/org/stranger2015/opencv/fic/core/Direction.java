package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

/**
 *
 */
public
enum Direction implements ISideDirection, ICornerDirection {
    NORTH("N", 0),
    EAST("E", 1),
    SOUTH("S", 2),
    WEST("W", 3),

    NORTH_WEST("NW", 4),
    NORTH_EAST("NE", 5),
    SOUTH_EAST("SE", 6),
    SOUTH_WEST("SW", 7);

    private final String shortName;
    private final int ord;

    Direction ( String shortName, int ord2) {
        this.shortName = shortName;
        this.ord = ord2;
    }

    /**
     * @return
     */
    @Override
    public
    int northEast () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int southEast () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int southWest () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int northWest () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    ICornerDirection opQuad () {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    ISideDirection commonSide ( CornerDirection quadrant ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    EnumSet <SideDirection> toSideDirection () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int north () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int east () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int south () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int west () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    ISideDirection cSide () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    ISideDirection ccSide () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    ISideDirection opSide () {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    ICornerDirection reflect ( CornerDirection quadrant ) {
        return null;
    }

    /**
     * @param side
     * @return
     */
    @Override
    public
    ICornerDirection quadrant ( SideDirection side ) {
        return null;
    }

    /**
     * @param cornerDirection
     * @return
     */
    @Override
    public
    boolean adjacent ( CornerDirection cornerDirection ) {
        return false;
    }

    public
    int getOrd () {
        return ord;
    }

    public
    String getShortName () {
        return shortName;
    }
}
