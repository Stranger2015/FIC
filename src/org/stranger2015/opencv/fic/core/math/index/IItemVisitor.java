package org.stranger2015.opencv.fic.core.math.index;

/**
 * A visitor for items in a {@link ISpatialIndex}.
 *
 * @version 1.8
 */
public
interface IItemVisitor {
    /**
     * Visits an item in the index.
     *
     * @param item the index item to be visited
     */
    void visitItem ( Object item );
}
