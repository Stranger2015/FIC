package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeImageBlockGenerator <N, A, G> {

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    DelaunayImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                                  EPartitionScheme scheme,
                                  IEncoder <N, A, G> encoder,
                                  IImage <A> image,
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
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int rangeSize, int domainSize )
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
    List <IImageBlock <A>> createCodebookBlocks ( IImageBlock <A> roi, List <IImageBlock <A>> domainBlock )
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
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
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
    List <IImageBlock <A>> generateInitialRangeBlocks ( IImageBlock <A> roi,
                                                        int blockWidth,
                                                        int blockHeight )
            throws ValueError {

        return super.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
