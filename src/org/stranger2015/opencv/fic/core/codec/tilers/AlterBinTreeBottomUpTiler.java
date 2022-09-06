package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
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
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    /**
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue ) throws ValueError {
        return List.of();
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
}