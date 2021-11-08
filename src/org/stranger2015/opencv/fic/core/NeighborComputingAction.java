package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.DomainPool;

import java.util.function.Consumer;

/**
 * @param <N>
 */
public
class NeighborComputingAction<N extends TreeNode <N>> extends TreeNodeAction <N> {

    private final N node;
    private final TreeTraverser.QuadDirection direction;

    public
    NeighborComputingAction ( DomainPool domainPool, final N node, TreeTraverser.QuadDirection direction) {
        super(domainPool);
        this.node = node;
        this.direction = direction;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param n the input argument
     */
    @Override
    public
    void accept ( N n ) {
        if (n == node) {
            return;
        }
        if (node.compareTo(n) <= 0 && TreeTraverser.shareCommonBorder(node, n, direction)) {
            return;
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
        return super.andThen(after);
    }

    public
    TreeTraverser.QuadDirection getDirection () {
        return direction;
    }
}
