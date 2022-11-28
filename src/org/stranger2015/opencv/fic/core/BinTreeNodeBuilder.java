package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TreeNodeBuilder <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param library
     * @param domainSize1
     * @param rangeSize1
     */
    public
    BinTreeNodeBuilder ( IImage <A> image,
                         IIntSize rangeSize,
                         IIntSize domainSize,
                         IEncoder <N, A, G> encoder,
                         Library <A> library) {

        super(image, rangeSize, domainSize, encoder, library);
    }

    /**
     * @return
     * @throws ValueError
     */
    @Override
    public
    Tree <N, A, G> buildTree ( IImageBlock <A> imageBlock ) throws ValueError {
        BinTreeNode <N, A,G> root = new BinTreeNode <>(null, imageBlock.getSize().getWidth(), imageBlock.getHeight());
        return new BinTree <>(root,imageBlock,new TreeNodeTask <>(root, codec, image));
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNodeBase <N, A, G>> getSuccessors () {
        return getLastNode().getChildren();
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNodeBase <N, A, G> node ) {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {
    }

    /**
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> getLastNode () {
        return lastNode;
    }

    /**
     * @return
     */
    @Override
    public
    LeafNode <N, A, G> getLastLeafNode () {
        return lastLeafNode;
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getDomainSize () {
        return domainSize;
    }

    /**
     * @return
     */
    @Override
    public
    ITreeNodeBuilder <N, A, G> newInstance () {
        return new BinTreeNodeBuilder<>();
    }
}
