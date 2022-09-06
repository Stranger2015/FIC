package org.stranger2015.opencv.fic.core.search.ann;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.SearchProcessor;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public abstract
class AnnSearchProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends SearchProcessor <N, A, G> {
//    /**
//     *
//     */
//    @Override
//    public
//    ITransform <A, G> searchForBestTransform () {
//        return null;
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    M search () {
//        return null;
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    double evaluate () {
//        return 0;
//    }

}
