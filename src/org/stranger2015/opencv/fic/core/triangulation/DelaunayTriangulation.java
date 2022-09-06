package org.stranger2015.opencv.fic.core.triangulation;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.geom.Envelope;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.utils.BitBuffer;

public
class DelaunayTriangulation<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends QuadEdgeSubdivision
        implements ILeaf <N, A, G> {

    /**
     * Creates a new instance of a quad-edge subdivision based on a frame triangle
     * that encloses a supplied bounding box. A new super-bounding box that
     * contains the triangle is computed and stored.
     *
     * @param env       the bounding box to surround
     * @param tolerance the tolerance value for determining if two sites are equal
     */
    public
    DelaunayTriangulation ( Envelope env, double tolerance ) {
        super(env, tolerance);
    }

    @Override
    public
    int getX () {
        return 0;
    }

    @Override
    public
    int getY () {
        return 0;
    }

    @Override
    public
    IAddress <A> getAddress () {
        return null;
    }

    @Override
    public
    IImageBlock <A> getImageBlock () {
        return null;
    }

    @Override
    public
    MatOfInt getMat () {
        return null;
    }

    @Override
    public
    IImage <A> getImage () {
        return null;
    }

    @Override
    public
    IIntSize getBoundingBox () {
        return null;
    }
}
