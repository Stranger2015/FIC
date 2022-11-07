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
class BinTreePartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends PartitionProcessor <N, A, G> {

    /**
     * @param tiler
     */
    public
    BinTreePartitionProcessor ( ITiler <N, A, G> tiler,
                                ImageBlockGenerator <N, A, G> imageBlockGenerator,
                                ITreeNodeBuilder <N, A, G> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 2;
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler,
                                             ImageBlockGenerator <N, A, G> imageBlockGenerator,
                                             ITreeNodeBuilder <N, A, G> nodeBuilder ) {

        return new BinTreePartitionProcessor <>(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @param rangeBlocks
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateDomainBlocks ( List <IImageBlock <A>> rangeBlocks,
                                                  IIntSize rangeSize,
                                                  IIntSize domainSize ) {

        return generateDomainBlocks(rangeBlocks,
                rangeSize,
                domainSize);
    }

    /**
     * @param image
     * @param rectangles
     * @return
     */
    @Override
    public
    List <RegionOfInterest <A>> generateRegions ( IImage <A> image, List <Rectangle> rectangles ) {
        return null;
    }
}
