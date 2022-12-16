package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.IBottomUpTiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class QuadTreeBottomUpTiler
        extends QuadTreeTiler
        implements IBottomUpTiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    QuadTreeBottomUpTiler ( IImage image,
                            IIntSize rangeSize,
                            IIntSize domainSize,
                            IEncoder encoder,
                            ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new QuadTreeBottomUpTiler(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     *
     */
    @Override
    public
    void onLeaf ( LeafNode <?> leafNode, IImageBlock  imageBlock) throws ValueError {
        super.onLeaf(leafNode, imageBlock);
    }

    /**
     *
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
        super.onSuccessors(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
        super.onSuccessor(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {

    }
}