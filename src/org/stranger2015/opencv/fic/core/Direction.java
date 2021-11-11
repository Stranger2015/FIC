package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

/**
 *
 */
public
enum Direction implements ISideDirection, ICornerDirection {

    NORTH("N", 0, SideDirection.NORTH),
    EAST("E", 1, SideDirection.EAST),
    SOUTH("S", 2, SideDirection.SOUTH),
    WEST("W", 3, SideDirection.WEST),

    NORTH_WEST("NW", 4, CornerDirection.NORTH_WEST),
    NORTH_EAST("NE", 5, CornerDirection.NORTH_EAST),
    SOUTH_EAST("SE", 6, CornerDirection.SOUTH_EAST),
    SOUTH_WEST("SW", 7, CornerDirection.NORTH_WEST);

    private final String shortName;
    private final int ord;

    private final SideDirection sideDirection;
    private final CornerDirection cornerDirection;

    /**
     * @param shortName
     * @param ord
     * @param sideDirection
     */
    Direction ( String shortName, int ord, SideDirection sideDirection ) {
        this.shortName = shortName;
        this.ord = ord;
        this.sideDirection = sideDirection;
        this.cornerDirection = null;
    }

    /**
     * @param shortName
     * @param ord
     * @param cornerDirection
     */
    Direction ( String shortName, int ord, CornerDirection cornerDirection ) {
        this.shortName = shortName;
        this.ord = ord;
        this.cornerDirection = cornerDirection;
        this.sideDirection = null;
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

    /**
     * @return
     */
    @Override
    public
    CornerDirection opQuad () {
        return cornerDirection.opQuad();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    ISideDirection commonSide ( CornerDirection quadrant ) {
        return cornerDirection.commonSide(quadrant);
    }

    /**
     * @return
     */
    @Override
    public
    EnumSet <SideDirection> toSideDirection () {
        return cornerDirection.toSideDirection();
    }

    /**
     * @return
     */
    @Override
    public
    int north () {
        return NORTH.getOrd();
    }

    /**
     * @return
     */
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
    SideDirection cSide () {
        return sideDirection.cSide();
    }

    /**
     * @return
     */
    @Override
    public
    SideDirection ccSide () {
        return sideDirection.ccSide();
    }

    /**
     * @return
     */
    @Override
    public
    SideDirection opSide () {
        return sideDirection.opSide();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    CornerDirection reflect ( CornerDirection quadrant ) {
        return sideDirection.reflect(quadrant);
    }

    /**
     * @param side
     * @return
     */
    @Override
    public
    CornerDirection quadrant ( SideDirection side ) {
        return quadrant(side);
    }

    /**
     * @param  quadrant
     * @return
     */
    @Override
    public
    boolean adjacent ( CornerDirection quadrant ) {
        return sideDirection.adjacent(quadrant);
    }

    public
    int getOrd () {
        return ord;
    }

    public
    String getShortName () {
        return shortName;
    }

    public
    SideDirection getSideDirection () {
        return sideDirection;
    }

    public
    CornerDirection getCornerDirection () {
        return cornerDirection;
    }
}
