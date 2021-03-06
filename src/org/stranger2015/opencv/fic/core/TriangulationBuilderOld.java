package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.triangulation.DelaunayTriangulation;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class TriangulationBuilderOld<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    /**
     * @return
     */
    public abstract
    DelaunayTriangulation build ();
}
