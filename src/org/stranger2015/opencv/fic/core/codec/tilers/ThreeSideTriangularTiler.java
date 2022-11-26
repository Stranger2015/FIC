package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class ThreeSideTriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    ThreeSideTriangularTiler ( IImage <A> image,
                               IIntSize rangeSize,
                               IIntSize domainSize,
                               IEncoder <N, A, G> encoder,
                               ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new ThreeSideTriangularTiler<>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
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
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        List.of();
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N, A, G> node,  IImageBlock <A> imageBlock ) throws ValueError {
        List.of();
    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi,
                                   int blockWidth,
                                   int blockHeight ) {
        return new ArrayList <>();
    }

    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( IImageBlock <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {
        return List.of();
    }

    @Override
    public
    int successorAmount () {
        return 1;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return List.of();
    }
}
