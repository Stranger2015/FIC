package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BushTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeNode <N, A, G>
        implements ITree <N, A, G> {

    /**
     * @param parent
     * @param width
     * @param height
     */
    public
    BushTreeNode ( TreeNodeBase <N, A, G> parent, int width, int height ) throws ValueError {
        super(parent, width, height);
    }

    /**
     * @param parent
     * @param direction
     * @param rect
     * @throws ValueError
     */
    public
    BushTreeNode ( TreeNodeBase <N, A, G> parent, EDirection direction, IIntSize rect ) throws ValueError {
        super(parent, direction, rect);
    }

    /**
     * @param parent
     * @param address
     */
    public
    BushTreeNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
        super(parent, address);
    }

    /**
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNodeBase <N, A, G> createChild ( IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNode <N, A, G>> getNodes () {
        return List.of();
    }
}
