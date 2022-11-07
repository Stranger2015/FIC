package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface IPartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @return
     */
    int successorAmount ();

    /**
     * @return
     */
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler,
                                             ImageBlockGenerator <N, A, G> imageBlockGenerator,
                                             ITreeNodeBuilder <N, A, G>nodeBuilder );

    /**
     * @return
     */
    ITiler <N, A, G> getTiler ();

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    default
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return getTiler().generateVerticesSet(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        return getTiler().generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        return getTiler().generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param image
     * @param searchType
     * @return
     */
    default
    void partition ( IImageBlock <A> imageBlock,
                                       IIntSize size,
                                       Deque <IImageBlock <A>> queue )
            throws ValueError {

        doPartition(imageBlock, size, queue);
    }

    /**
     * @param imageBlock
     * @param size
     * @param queue
     * @return
     * @throws ValueError
     */
    default
    void doPartition ( IImageBlock <A> imageBlock,
                                         IIntSize size,
                                         Deque <IImageBlock <A>> queue ) throws ValueError {

        getTiler().tile(imageBlock);
    }

    List <IImageBlock <A>> generateDomainBlocks (
            List <IImageBlock <A>> rangeBlocks,
            IIntSize rangeSize,
            IIntSize domainSize );

    List<RegionOfInterest<A>> generateRegions ( IImage <A> image, List<Rectangle> rectangles );

    default
    IIntSize getRangeSize () {
        return getTiler().getRangeSize();
    }

    default
    IIntSize getDomainSize () {
        return getTiler().getDomainSize();
    }
}
