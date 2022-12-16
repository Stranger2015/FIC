package org.stranger2015.opencv.fic.core.triangulation;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ILeaf;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.geom.Envelope;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;

/**
 * @param <N>
 * @param 
 * @param <G>
 */
public
class DelaunayTriangulation<N extends TreeNode <N>>
        extends QuadEdgeSubdivision <N>
        implements ILeaf <N> {

    private final ITiler subtiler;

    /**
     * Creates a new instance of a quad-edge subdivision based on a frame triangle
     * that encloses a supplied bounding box. A new super-bounding box that
     * contains the triangle is computed and stored.
     *
     * @param env       the bounding box to surround
     * @param tolerance the tolerance value for determining if two sites are equal
     * @param subtiler
     */
    public
    DelaunayTriangulation ( TreeNodeBase <N> parent, Envelope env, double tolerance, ITiler subtiler ) {
        super(parent, env, tolerance);

        this.subtiler = subtiler;
    }

    /**
     *
     */
//    @Override
    public
    void initialize () {

    }

    IAddress  getAddress ( int row, int col ) throws ValueError {
        return null;
    }

    public
    ITiler getSubtiler () {
        return subtiler;
    }
}
