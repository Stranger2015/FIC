package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DelaunayPartitionProcessor<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends PartitionProcessor <N> {

    /**
     * @param tiler
     */
    public
    DelaunayPartitionProcessor ( ITiler <N> tiler, ImageBlockGenerator<N,A,G> imageBlockGenerator) {
        super(tiler, imageBlockGenerator);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> instance ( ITiler <N> tiler ) {
        return new DelaunayPartitionProcessor<>(tiler);
    }

    @Override
    public
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    @Override
    public
    List <IImageBlock > generateDomainBlocks ( List <IImageBlock > rangeBlocks, IIntSize rangeSize, IIntSize domainSize ) {
        return super.generateDomainBlocks(rangeBlocks,rangeSize,domainSize );
    }

    @Override
    public
    List <IImageBlock > generateRegions ( IImage image, List <Rectangle> rectangles ) {
        return null;
    }
}
