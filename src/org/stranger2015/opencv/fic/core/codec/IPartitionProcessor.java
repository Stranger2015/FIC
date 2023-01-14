package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifiable;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifier;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlockInfo;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface IPartitionProcessor extends IProcessor {

    /**
     * @param classifier
     */
    void setClassifier ( IClassifier classifier );

    /**
     * @return
     */
    IClassifier getClassifier ();

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
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError, IOException {

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
    Pool<IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError, IOException {

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
    Pool <IImageBlock> generateDomainBlocks ( IImageBlock roi, int blockWidth, int blockHeight, IImage.EColorType colorType )
            throws ValueError, IOException {

        return getTiler().generateDomainBlocks(roi, blockWidth, blockHeight, colorType);
    }

    /**
     * @param image
     * @param searchType
     * @return
     */
    default
    void partition ( IImageBlock imageBlock )
            throws Throwable {

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
    void doPartition ( IImageBlock imageBlock ) throws Throwable {
        getTiler().tile(imageBlock);
    }

    /**
     * @return
     */
    default
    IIntSize getRangeSize () {
        return getTiler().getCurrentRangeSize();
    }

    /**
     * @return
     */
    default
    IIntSize getDomainSize () {
        return getTiler().getCurrentDomainSize();
    }

    /**
     * @param imageBlocks
     * @param domainBlocks
     */
    void classify ( Pool <IImageBlock> imageBlocks, Pool <IImageBlock> domainBlocks ) throws IOException;

    Set <IClassifiable> classify ( Pool <IImageBlock> rangeBlocks, Pool <IImageBlock> domainBlocks, ImageBlockInfo[] level2Classes ) throws IOException;
}
