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
    EDirection opQuad ();

    /**
     * @param quadrant
     * @return
     */
    EDirection commonSide ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    EDirection side1 ( EDirection quadrant );
/**
     * @param quadrant
     * @return
     */
    EDirection side2 ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    int width ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    int radius ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    int xOf ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    int yOf ( EDirection quadrant );

    /**
     * @param quadrant
     * @return
     */
    Object value ( EDirection quadrant );//todo

    /**
     * @param x
     * @param y
     * @param width
     * @param color
     * @param d
     * @return
     */
    EDirection insertReverseOrder( int x, int y, int width, int color, int d);

    /**
     * @return
     */
    EnumSet <EDirection> toSideDirection();
}
