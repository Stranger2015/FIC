package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 * @param <N>
 * @param
 * @param <G>
 */
public
class TriangularBottomUpTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TriangularTiler <N>
        implements IBottomUpTiler <N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    TriangularBottomUpTiler ( IImage image,
                              IIntSize rangeSize,
                              IIntSize domainSize,
                              IEncoder <N> encoder,
                              ITreeNodeBuilder <N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N> instance () {
        return new TriangularBottomUpTiler <>(
                getImage(),
                getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder());
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle (TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N> node,IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }
}
