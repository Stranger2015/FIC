package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

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

    @Override
    public
    Deque <IImageBlock <A>> getDeque () {
        return null;
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onAddNode ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {

    }

    /**
     * @param leafNode
     * @param imageBlock
     */
    @Override
    public
    void onAddLeafNode ( TreeNode.LeafNode <N, A, G> leafNode, IImageBlock <A> imageBlock ) {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError {
        segmentSquareOrRectangle(imageBlock);
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    private
    void segmentSquareOrRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        if (imageBlock.isSquare()) {
            segmentSquare(imageBlock);
        }
        else {
            segmentRectangle(imageBlock);
        }
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentRectangle(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentSquare(imageBlock);
    }
}
