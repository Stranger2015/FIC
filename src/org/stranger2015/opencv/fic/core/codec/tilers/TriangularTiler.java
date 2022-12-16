package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public abstract
class TriangularTiler        extends Tiler{

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    TriangularTiler ( IImage image,
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
    public abstract
    ITiler instance ();

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentTriangle(node, imageBlock);

    }

    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);

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