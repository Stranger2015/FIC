package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class TriangularBottomUpTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G>
        implements IBottomUpTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    TriangularBottomUpTiler ( IImage <A> image,
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
        return new TriangularBottomUpTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }
//
//    /**
//     * @param imageBlock
//     * @param minRangeSize
//     * @param queue
//     * @return
//     * @throws ValueError
//     */
//    @Override
//    public
//    List <IImageBlock <A>> doTile ( IImageBlock <A> imageBlock,
//                                    IIntSize minRangeSize,
//                                    Deque <IImageBlock <A>> queue ) throws ValueError {
//
//        return super.doTile(imageBlock, minRangeSize, queue);
//    }

    @Override
    public
    IIntSize getRangeSize () {
        return null;
    }

    @Override
    public
    IIntSize getDomainSize () {
        return null;
    }

    @Override
    public
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }
}
