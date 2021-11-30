package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.DomainBlock;
import org.stranger2015.opencv.fic.DomainPool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.stranger2015.opencv.fic.DomainBlock.H;
import static org.stranger2015.opencv.fic.DomainBlock.W;

public
class TreeNodeAction<N extends TreeNode<N>> implements Consumer <N> {
//    private final DomainPool domainPool;
    private final List <N> leaves;

    public
    List <N> getLeaves () {
        return leaves;
    }

    public
    TreeNodeAction ( /*DomainPool domainPool */) {
//        this.domainPool = domainPool;
        leaves = new ArrayList <>();
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param n the input argument
     */
    @SuppressWarnings("*")//fixme
    @Override
    public
    void accept ( N n ) {
        if (n.isLeaf()) {
            if (
                    W == n.boundingBox.width &&
                    H == n.boundingBox.height
            ) {
//                n = (N) new DomainBlock(n, ((Leaf) n).image, n.boundingBox);
//                domainPool.add((DomainBlock) n);//fixme is it meaningful
            }
            leaves.add(n);
        }
    }

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    @NotNull
    @Override
    public
    Consumer <N> andThen ( @NotNull Consumer <? super N> after ) {
        return Consumer.super.andThen(after);//todo default leaf action
    }
}
