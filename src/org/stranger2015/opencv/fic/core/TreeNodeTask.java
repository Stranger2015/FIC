package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class TreeNodeTask<N extends TreeNode <N, A, G>, A extends IAddress <A>,G extends BitBuffer>
        extends Task <N, A, G> {

    private final List <LeafNode <N, A, G>> leaves = new ArrayList <>();
    private final List <TreeNode <N, A, G>> nodes = new ArrayList <>();

    /**
     * @param scheme
     * @param codec
     * @param tasks
     */
    public
    TreeNodeTask (
                   EPartitionScheme scheme,
                   ICodec <N, A, G> codec,
                   List <Task <N, A, G>> tasks
    ) {
        super(null, scheme, codec, tasks);
    }
//
//    public
//    TreeNodeTask ( EPartitionScheme scheme, List <IImageBlock <A>> domainPool, List <LeafNode<N, A, G>> leaves ) {
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
