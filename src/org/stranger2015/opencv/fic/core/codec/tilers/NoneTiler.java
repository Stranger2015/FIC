package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
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
class NoneTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    NoneTiler ( IImage <A> image,
                IIntSize rangeSize,
                IIntSize domainSize,
                IEncoder <N, A, G> encoder,
                ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder, builder1);
    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return new NoneTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of();
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of();
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return new Vertex[0];
    }

    @Override
    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {
        return List.of();
    }

    @Override
    public
    int successorAmount () {
        return 0;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( //EShape imageBlockShape,
                                             IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue ) throws ValueError {

        logger.info("Segmenting shape ...");


        return List.of();
    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {
        logger.info("Finishing doTile() ...");
    }
}
