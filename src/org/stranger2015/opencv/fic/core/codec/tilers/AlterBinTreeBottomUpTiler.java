package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class AlterBinTreeBottomUpTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends AlterBinTreeTiler <N, A, G>
        implements IBottomUpTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    AlterBinTreeBottomUpTiler ( IImage <A> image,
                                IIntSize rangeSize,
                                IIntSize domainSize,
                                IEncoder <N, A, G> encoder,
                                ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new AlterBinTreeBottomUpTiler <>(
                getImage(),
                getDomainSize(),
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
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        List.of(imageBlock);
    }
}