package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

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
    void segmentPolygon (TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
        return List.of();
    }

    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( @NotNull IImageBlock <A> roi,
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
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock
    ) throws ValueError {
        logger.info("Segmenting geometry ...");

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {

    }


    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <N, A, G> node ) {

    }

    /**
     *
     */
    @Override
    public
    void onFinish () {
        super.onFinish();
    }

}
