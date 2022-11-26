package org.stranger2015.opencv.fic.core.triangulation;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ILeaf;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.geom.Envelope;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayTriangulation<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends QuadEdgeSubdivision <N, A, G>
        implements ILeaf <N, A, G> {

    private final ITiler <N, A, G> subtiler;

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
    DelaunayTriangulation ( TreeNodeBase <N, A, G> parent, Envelope env, double tolerance, ITiler <N, A, G> subtiler ) {
        super(parent, env, tolerance);
        this.subtiler = subtiler;
    }
/

    /**
     *
     */
//    @Override
    public
    void initialize () {

    }

    IAddress <A> getAddress ( int row, int col ) throws ValueError {
        return null;
    }
}
