package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class AlterBinTreeTopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends AlterBinTreeTiler <N, A, G>
        implements ITopDownTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    AlterBinTreeTopDownTiler ( IImage <A> image,
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
        return new AlterBinTreeTopDownTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     *
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
        super.onSuccessors(node, imageBlock);
    }

    /**
     *
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
        super.onSuccessor(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        segmentSquareOrRectangle(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    private
    void segmentSquareOrRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        if (imageBlock.isSquare()) {
            segmentSquare(node, imageBlock);
        }
        else {
            segmentRectangle(node, imageBlock);
        }
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentRectangle(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
        super.segmentTriangle(node, imageBlock);
        
    }
}
