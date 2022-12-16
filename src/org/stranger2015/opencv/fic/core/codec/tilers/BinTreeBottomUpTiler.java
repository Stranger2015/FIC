package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreeBottomUpTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTreeTiler <N>
        implements IBottomUpTiler <N> {


    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    BinTreeBottomUpTiler ( IImage image,
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
        return new BinTreeBottomUpTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
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
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    boolean isBottomUpTiler () {
        return true;
    }
}
