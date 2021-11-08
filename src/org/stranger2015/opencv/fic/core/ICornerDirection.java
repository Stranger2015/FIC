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
    ICornerDirection opQuad ();

    /**
     * @param quadrant
     * @return
     */
    ISideDirection commonSide ( CornerDirection quadrant );

    /**
     * @return
     */
    EnumSet <SideDirection> toSideDirection();
}
