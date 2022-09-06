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
class OneSideTriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G> {
    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    OneSideTriangularTiler ( IImage <A> image,
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
        return new OneSideTriangularTiler<>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( IImageBlock <A> imageBlock, IIntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError {
//        List <IImageBlock <A>> list = ITiler.super.segmentShape(imageBlock, minRangeSize, queue);
        return List.of(imageBlock);
    }

    @Override
    public
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return new Vertex[0];
    }

    @Override
    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) throws ValueError {
        return null;
    }

    @Override
    public
    int successorAmount () {
        return 0;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) throws ValueError {
        return null;
    }
}
