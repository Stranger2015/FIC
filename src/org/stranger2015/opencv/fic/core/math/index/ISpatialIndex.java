package org.stranger2015.opencv.fic.core.math.index;

import org.locationtech.jts.index.ItemVisitor;
import org.stranger2015.opencv.fic.core.geom.Envelope;

import java.util.List;

/**
 * The basic operations supported by classes
 * implementing spatial index algorithms.
 * <p>
 * A spatial index typically provides a primary filter for range rectangle queries.
 * A secondary filter is required to test for exact intersection.
 * The secondary filter may consist of other kinds of tests,
 * such as testing other spatial relationships.
 *
 * @version 1.7
 */
public interface ISpatialIndex
{
    /**
     * Adds a spatial item with an extent specified by the given {@link Envelope} to the index
     */
    void insert(Envelope itemEnv, Object item);

    /**
     * Queries the index for all items whose extents intersect the given search {@link Envelope}
     * Note that some kinds of indexes may also return objects which do not in fact
     * intersect the query envelope.
     *
     * @param searchEnv the envelope to query for
     * @return a list of the items found by the query
     */
    List query( Envelope searchEnv);

    /**
     * Queries the index for all items whose extents intersect the given search {@link Envelope},
     * and applies an {@link ItemVisitor} to them.
     * Note that some kinds of indexes may also return objects which do not in fact
     * intersect the query envelope.
     *
     * @param searchEnv the envelope to query for
     * @param visitor a visitor object to apply to the items found
     */
    void query(Envelope searchEnv, ItemVisitor visitor);

    /**
     * Removes a single item from the tree.
     *
     * @param itemEnv the Envelope of the item to remove
     * @param item the item to remove
     * @return <code>true</code> if the item was found
     */
    boolean remove(Envelope itemEnv, Object item);
}