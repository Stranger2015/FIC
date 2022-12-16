package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DelaunayImageBlockGenerator<N extends TreeNode <N>>
        extends BinTreeImageBlockGenerator <N> {

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    DelaunayImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                                  EPartitionScheme scheme,
                                  IEncoder encoder,
                                  IImage image,
                                  IIntSize rangeSize,
                                  IIntSize domainSize ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int rangeSize, int domainSize )
            throws ValueError {

        return super.generateRangeBlocks(roi, rangeSize, domainSize);
    }

    /**
     * @param roi
     * @param list
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock > createCodebookBlocks ( IImageBlock  roi, List <IImageBlock > domainBlock )
            throws ValueError {

        return super.createCodebookBlocks(roi, domainBlock);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return super.generateVerticesSet(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    protected
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight )
            throws ValueError {

        return super.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
