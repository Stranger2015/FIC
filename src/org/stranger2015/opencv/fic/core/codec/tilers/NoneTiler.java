package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
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
        extends Tiler<N, A, G> {

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
                ITreeNodeBuilder<N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
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

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( @NotNull RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {
        return List.of(roi.getSubImage());
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */

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
        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }


    /**
     * @param imageBlockShape
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock
    ) throws ValueError {
        logger.info("Segmenting geometry ...");

        return List.of(imageBlock);
    }

    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    List <IImageBlock <A>> tile ( IImageBlock <A> imageBlock,
                                  IIntSize minRangeSize,
                                  @NotNull Deque <IImageBlock <A>> queue ) throws ValueError {
        return super.tile(imageBlock, minRangeSize, queue);
    }

    @Override
    public
    List <IImageBlock <A>> doTile ( IImageBlock <A> imageBlock,
                                    IIntSize minRangeSize,
                                    Deque <IImageBlock <A>> queue ) throws ValueError {
        return super.doTile(imageBlock, minRangeSize, queue);
    }

    @Override
    public
    void onFinish () {
        super.onFinish();
    }

}
