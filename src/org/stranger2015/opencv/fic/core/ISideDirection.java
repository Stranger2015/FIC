package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
interface ISideDirection {

    /**
     * @return
     */
    int north ();

    /**
     * @return
     */
    int east ();

    /**
     * @return
     */
    int south ();

    /**
     * @return
     */
    int west ();

    /**
     * @return
     */
    ISideDirection cSide ( );

    /**
     * @return
     */
    ISideDirection ccSide ( );

    /**
     * @return
     */
    ISideDirection opSide ( );

    /**
     * @param quadrant
     * @return
     */
    ICornerDirection reflect ( CornerDirection quadrant );

    /**
     * @param side
     * @return
     */
    ICornerDirection quadrant ( SideDirection side );

    /**
     * @param cornerDirection
     * @return
     */
    boolean adjacent ( CornerDirection cornerDirection );
}
