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
    Direction cSide ( );

    /**
     * @return
     */
    Direction ccSide ( );

    /**
     * @return
     */
    Direction opSide ( );

    /**
     * @param quadrant
     * @return
     */
    Direction reflect ( Direction quadrant );

    /**
     * @param side
     * @return
    */
    Direction quadrant ( Direction side );

    /**
     * @param cornerDirection
     * @return
     */
    boolean adjacent ( Direction cornerDirection );
}
