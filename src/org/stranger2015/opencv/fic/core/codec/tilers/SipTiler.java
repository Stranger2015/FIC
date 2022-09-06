package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
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
class SipTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    SipTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder, builder1);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    List<IImageBlock<A>> segmentRectangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException("SipTiler#segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    List<IImageBlock<A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        if (imageBlock.getSize().getWidth() <= 1 || imageBlock.getSize().getWidth() != 3) {
            throw new ValueError("SIP Image block size must be equal to 3 x 3");
        }

        List <IImageBlock <A>> result = super.segmentSquare(imageBlock);

        ESplitKind dir = chooseDirection(imageBlock);
        int sideSize = imageBlock.getWidth();

//        if (sideSize == imageBlock.getWidth() / 2) {
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(sideSize, 0, sideSize);
//        }
//        else {
//            sideSize = imageBlock.getHeight() / 2;
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(0, sideSize, sideSize);
//        }

        return result;
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
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) throws ValueError {
        return List.of();
    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }

    /**
     * @return
     */
//    @Override
    public
    ITiler <N, A, G> instance () {
        return new SipTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return List.of();
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( //
                                             IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue ) {
        return List.of();
    }
}