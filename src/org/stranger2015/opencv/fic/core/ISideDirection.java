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
    EDirection cSide ( );

    /**
     * @return
     */
    EDirection ccSide ( );

    /**
     * @return
     */
    EDirection opSide ( );

    /**
     * @param quadrant
     * @return
     */
    EDirection reflect ( EDirection quadrant );

    /**
     * @param side
     * @return
    */
    EDirection quadrant ( EDirection side );

    /**
     * @param cornerDirection
     * @return
     */
    boolean adjacent ( EDirection cornerDirection );
}
