package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BushTreeLeaf<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends LeafNode <N, A, G> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @throws ValueError
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    /**
     * @param parent
     * @param rect
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, IIntSize rect ) {
        super(parent, rect);
    }

    /**
     * @param parent
     * @param addr
     * @throws ValueError
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, int addr ) throws ValueError {
        super(parent, addr);
    }

    /**
     * @param parent
     * @param address
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
        super(parent, address);
    }

    /**
     * @param parent
     * @param imageBlock
     * @param address
     * @throws ValueError
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        super(parent, imageBlock, address);
    }

    /**
     * @param parent
     * @param imageBlock
     * @throws ValueError
     */
    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock ) throws ValueError {
        super(parent, imageBlock);
    }

    public
    BushTreeLeaf ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IIntSize rect ) throws ValueError {
        super(parent, imageBlock, rect);
    }
}
