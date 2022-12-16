package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class AlterBinTreeTopDownTiler
        extends AlterBinTreeTiler
        implements ITopDownTiler  {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    AlterBinTreeTopDownTiler ( IImage image,
                               IIntSize rangeSize,
                               IIntSize domainSize,
                               IEncoder encoder,
                               ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new AlterBinTreeTopDownTiler(
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
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
        super.onSuccessors(node, imageBlock);
    }

    /**
     *
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
        super.onSuccessor(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        segmentSquareOrRectangle(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    private
    void segmentSquareOrRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
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
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentRectangle(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        super.segmentTriangle(node, imageBlock);
        
    }
}
