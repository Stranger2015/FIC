package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.DelaunayTriangulationBuilder;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DtIterativeDynamicCacheBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends DelaunayTriangulationBuilder <N, A, G> {
    public
    DtIterativeDynamicCacheBuilder () {
        super(getClass());
    }

    @Override
    public
    Geometry getTriangles ( GeometryFactory geomFact ) {
        return super.getTriangles(geomFact);
    }
}
