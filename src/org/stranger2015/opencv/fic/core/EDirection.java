package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

/**
 *
 */
public
enum EDirection implements ISideDirection, ICornerDirection {

    NORTH("N", 0, ESideDirection.NORTH),
    EAST("E", 1, ESideDirection.EAST),
    SOUTH("S", 2, ESideDirection.SOUTH),
    WEST("W", 3, ESideDirection.WEST),

    NORTH_WEST("NW", 4, ECornerDirection.NORTH_WEST),
    NORTH_EAST("NE", 5, ECornerDirection.NORTH_EAST),
    SOUTH_EAST("SE", 6, ECornerDirection.SOUTH_EAST),
    SOUTH_WEST("SW", 7, ECornerDirection.NORTH_WEST);

    private final String shortName;
    private final int ord;

    private final ESideDirection sideDirection;
    private final ECornerDirection cornerDirection;

    /**
     * @param shortName
     * @param ord
     * @param sideDirection
     */
    EDirection ( String shortName, int ord, ESideDirection sideDirection ) {
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
    EDirection ( String shortName, int ord, ECornerDirection cornerDirection ) {
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
    EDirection opQuad () {
        return cornerDirection.opQuad();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    EDirection commonSide ( EDirection quadrant ) {
        return cornerDirection.commonSide(quadrant);
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    EDirection side1 ( EDirection quadrant ) {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    EDirection side2 ( EDirection quadrant ) {
        return null;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int width ( EDirection quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int radius ( EDirection quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int xOf ( EDirection quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    int yOf ( EDirection quadrant ) {
        return 0;
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    EDirection value ( EDirection quadrant ) {
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
    EDirection insertReverseOrder ( int x, int y, int width, int color, int d ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    EnumSet <EDirection> toSideDirection () {
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
    EDirection cSide () {
        return sideDirection.cSide();
    }

    /**
     * @return
     */
    @Override
    public
    EDirection ccSide () {
        return sideDirection.ccSide();
    }

    /**
     * @return
     */
    @Override
    public
    EDirection opSide () {
        return sideDirection.opSide();
    }

    /**
     * @param quadrant
     * @return
     */
    @Override
    public
    EDirection reflect ( EDirection quadrant ) {
        return sideDirection.reflect(quadrant);
    }

    /**
     * @param side
     * @return
     */
    @Override
    public
    EDirection quadrant ( EDirection side ) {
        return sideDirection.quadrant(side);
    }

    /**
     * @param  quadrant
     * @return
     */
    @Override
    public
    boolean adjacent ( EDirection quadrant ) {
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
    ESideDirection getSideDirection () {
        return sideDirection;
    }

    /**
     * @return
     */
    public
    ECornerDirection getCornerDirection () {
        return cornerDirection;
    }
}
