package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 */
public
class BuildTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TreeNodeTask <N, A, G> {

    /**
     * @param domainPool
     */
    public
    BuildTask (List<IImageBlock<A>> domainPool ) {
        super(domainPool, new ArrayList <>());
    }

//    /**
//     * Performs this operation on the given argument.
//     *
//     * @param n the input argument
//     */
//    @Override
//    public
//    void accept ( N n ) {
//        super.accept(n);
//    }
}
