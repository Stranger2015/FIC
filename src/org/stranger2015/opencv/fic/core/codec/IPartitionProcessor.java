package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
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
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler );

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
    List <IImageBlock <A>> partition ( IImageBlock <A> imageBlock,
                                       IIntSize size,
                                       Deque <IImageBlock <A>> queue )
            throws ValueError {

        return doPartition(imageBlock, size, queue);
    }

    /**
     * @param imageBlock
     * @param size
     * @param queue
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock <A>> doPartition ( IImageBlock <A> imageBlock,
                                         IIntSize size,
                                         Deque <IImageBlock <A>> queue ) throws ValueError {

        return getTiler().tile(imageBlock, size, queue);
    }

    /**
     * @param image
     * @param searchType
     * @return
     */

}
