package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class QuadTreeBottomUpTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends QuadTreeTiler <N, A, G>
        implements IBottomUpTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    QuadTreeBottomUpTiler ( IImage <A> image,
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
        return new QuadTreeBottomUpTiler<> (
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of();
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of();
    }
}
