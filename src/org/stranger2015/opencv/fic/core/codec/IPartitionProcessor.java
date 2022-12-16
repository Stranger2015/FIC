package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface IPartitionProcessor {

    /**
     * @return
     */
    int successorAmount ();

    /**
     * @return
     */
    IPartitionProcessor instance ( ITiler tiler,
                                   ImageBlockGenerator <?> imageBlockGenerator,
                                   ITreeNodeBuilder <?> nodeBuilder );

    /**
     * @return
     */
    ITiler getTiler ();

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    default
    List <Vertex> generateVerticesSet ( IImageBlock roi, int blockWidth, int blockHeight ) {
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
    List <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return getTiler().generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */ //move to tiler
    default
    List <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return getTiler().generateRangeBlocks(roi, blockWidth, blockHeight);
    }


    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock> generateDomainBlocks ( List <IImageBlock> roi, IIntSize blockWidth, IIntSize blockHeight )
            throws ValueError {

        return getTiler().generateDomainBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param image
     * @param searchType
     * @return
     */
    default
    void partition ( IImageBlock imageBlock )
            throws ValueError, DepthLimitExceeded {

        doPartition(imageBlock);
    }

    /**
     * @param imageBlock
     * @param size
     * @param queue
     * @return
     * @throws ValueError
     */
    default
    void doPartition ( IImageBlock imageBlock ) throws ValueError, DepthLimitExceeded {
        getTiler().tile(imageBlock);
    }

    default
    IIntSize getRangeSize () {
        return getTiler().getRangeSize();
    }

    default
    IIntSize getDomainSize () {
        return getTiler().getDomainSize();
    }
}
