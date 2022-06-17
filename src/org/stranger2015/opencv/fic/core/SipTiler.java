package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class SipTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Tiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    SipTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException("SipTiler#segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        if (imageBlock.getSize().getWidth() <= 1 || imageBlock.getSize().getWidth() != 3) {
            throw new ValueError("SIP Image block size must be equal to 3 x 3");
        }

        super.segmentSquare(imageBlock);

        ESplitKind dir = chooseDirection(imageBlock);
        int sideSize = imageBlock.getWidth();

//        if (sideSize == imageBlock.getWidth() / 2) {
            r[0] = new Square(0, 0, sideSize);
            r[1] = new Square(sideSize, 0, sideSize);
//        }
//        else {
//            sideSize = imageBlock.getHeight() / 2;
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(0, sideSize, sideSize);
//        }
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

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
    @Override
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

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) {
    }
}