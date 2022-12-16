package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreeNodeBuilder<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TreeNodeBuilder <N> {

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
    BinTreeNodeBuilder ( IImage image,
                         IIntSize rangeSize,
                         IIntSize domainSize,
                         IEncoder <N> encoder,
                         Library  library) {

        super(image, rangeSize, domainSize, encoder, library);
    }

    /**
     * @return
     * @throws ValueError
     */
    @Override
    public
    Tree <N> buildTree ( IImageBlock  imageBlock ) throws ValueError {
        BinTreeNode <N, A,G> root = new BinTreeNode <>(null, imageBlock.getSize().getWidth(), imageBlock.getHeight());
        return new BinTree <>(root,imageBlock,new TreeNodeTask <>(root, codec, image));
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNodeBase <?>> getSuccessors () {
        return getLastNode().getChildren();
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNodeBase <?> node ) {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {
    }

    /**
     * @return
     */
    @Override
    public
    TreeNode <N> getLastNode () {
        return lastNode;
    }

    /**
     * @return
     */
    @Override
    public
    LeafNode <N> getLastLeafNode () {
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
    ITreeNodeBuilder <N> newInstance () {
        return new BinTreeNodeBuilder<>();
    }
}
