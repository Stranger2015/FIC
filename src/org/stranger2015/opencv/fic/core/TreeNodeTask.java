package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class TreeNodeTask
        extends Task {

    private final List <LeafNode <?>> leaves = new ArrayList <>();
    private final List <TreeNode <?>> nodes = new ArrayList <>();

    /**
     * @param scheme
     * @param codec
     * @param tasks
     */
    public
    TreeNodeTask (
                   EPartitionScheme scheme,
                   ICodec codec,
                   List <Task> tasks
    ) {
        super(null, scheme, codec, tasks);
    }
//
//    public
//    TreeNodeTask ( EPartitionScheme scheme, List <IImageBlock > domainPool, List <LeafNode<N>> leaves ) {
//        super(scheme,domainPool,leaves);
//    }

//    /**
//     * Performs this operation on the given argument.
//     *
//     * @param n the input argument
//     */
//    @SuppressWarnings("*")//fixme
//    @Override
//    public
//    void apply ( N n ) {
//        if (n.isLeaf()) {
//            if (
//                    W == n.boundingBox.width &&
//                            H == n.boundingBox.height
//            ) {
////                n = (N) new DomainBlock(n, ((LeafNode) n).image, n.boundingBox);
////                domainPool.add((DomainBlock) n);//fixme is it meaningful
//            }
//            leaves.add(n);
//        }
//    }

}
