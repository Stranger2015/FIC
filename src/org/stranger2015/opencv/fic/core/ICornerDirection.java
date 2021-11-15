package org.stranger2015.opencv.fic.core;

import java.util.EnumSet;

/**
 *
 */
public
interface ICornerDirection {
    /**
     * @return
     */
    int northEast ();

    /**
     * @return
     */
    int southEast ();

    /**
     * @return
     */
    int southWest ();

    /**
     * @return
     */
    int northWest ();

    /**
     * @return
     */
    Direction opQuad ();

    /**
     * @param quadrant
     * @return
     */
    Direction commonSide (Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    Direction side1 ( Direction quadrant );
/**
     * @param quadrant
     * @return
     */
    Direction side2 ( Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    int width ( Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    int radius ( Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    int xOf ( Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    int yOf ( Direction quadrant );

    /**
     * @param quadrant
     * @return
     */
    Object value ( Direction quadrant );//todo

    /**
     * @param x
     * @param y
     * @param width
     * @param color
     * @param d
     * @return
     */
    Direction insertReverseOrder(int x, int y, int width, int color, int d);

    /**
     * @return
     */
    EnumSet <Direction> toSideDirection();
}
