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
    Direction opQuad () {
        return cornerDirection.opQuad();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Direction commonSide ( Direction quadrant ) {
        return cornerDirection.commonSide(quadrant);
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Direction side1 ( Direction quadrant ) {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Direction side2 ( Direction quadrant ) {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int width ( Direction quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int radius ( Direction quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int xOf ( Direction quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int yOf ( Direction quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Direction value ( Direction quadrant ) {
        return null;
    }

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
    }

    /**
     * @return
     */
    @Override
    public
    EnumSet <Direction> toSideDirection () {
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
    Direction cSide () {
        return sideDirection.cSide();
    }

    /**
     * @return
     */
    @Override
    public
    Direction ccSide () {
        return sideDirection.ccSide();
    }

    /**
     * @return
     */
    @Override
    public
    Direction opSide () {
        return sideDirection.opSide();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    Direction reflect ( Direction quadrant ) {
        return sideDirection.reflect(quadrant);
    }

    /**
     * @param side
     * @return
     */
    @Override
    public
    Direction quadrant ( Direction side ) {
        return sideDirection.quadrant(side);
    }

    /**
     * @param  quadrant
     * @return
     */
    @Override
    public
    boolean adjacent ( Direction quadrant ) {
        return sideDirection.adjacent(quadrant);
    }

    /**
     * @return
     */
    public
    int getOrd () {
        return ord;
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
    SideDirection getSideDirection () {
        return sideDirection;
    }

    /**
     * @return
     */
    public
    CornerDirection getCornerDirection () {
        return cornerDirection;
    }
}
