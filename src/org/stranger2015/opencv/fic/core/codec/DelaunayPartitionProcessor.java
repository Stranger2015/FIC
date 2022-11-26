package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayPartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends PartitionProcessor <N, A, G> {

    /**
     * @param tiler
     */
    public
    DelaunayPartitionProcessor ( ITiler <N, A, G> tiler, ImageBlockGenerator<N,A,G> imageBlockGenerator) {
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
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler ) {
        return new DelaunayPartitionProcessor<>(tiler);
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    @Override
    public
    List <IImageBlock <A>> generateDomainBlocks ( List <IImageBlock <A>> rangeBlocks, IIntSize rangeSize, IIntSize domainSize ) {
        return super.generateDomainBlocks(rangeBlocks,rangeSize,domainSize );
    }

    @Override
    public
    List <IImageBlock <A>> generateRegions ( IImage <A> image, List <Rectangle> rectangles ) {
        return null;
    }
}
