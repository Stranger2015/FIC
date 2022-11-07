package org.stranger2015.opencv.fic.core.geom;

/**
 * Indicates the position of a location relative to a
 * node or edge component of a planar topological structure.
 *
 * @version 1.7
 */
public class Position {

    /** Specifies that a location is <i>on</i> a component */
    public static final int ON      = 0;

    /** Specifies that a location is to the <i>left</i> of a component */
    public static final int LEFT    = 1;

    /** Specifies that a location is to the <i>right</i> of a component */
    public static final int RIGHT   = 2;

    /**
     * Returns LEFT if the position is RIGHT, RIGHT if the position is LEFT, or the position
     * otherwise.
     */
    public static
    int opposite( int position)
    {
        if (position == LEFT) {
            return RIGHT;
        }
        if (position == RIGHT) {
            return LEFT;
        }

        return position;
    }
}
