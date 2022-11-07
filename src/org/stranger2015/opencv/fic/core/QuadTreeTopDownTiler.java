package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITopDownTiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class QuadTreeTopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends QuadTreeTiler <N, A, G>
        implements ITopDownTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    QuadTreeTopDownTiler ( IImage <A> image,
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
        return new QuadTreeTopDownTiler <>(
                image,
                rangeSize,
                domainSize,
                encoder,
                builder);
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
    void onAddLeafNode ( LeafNode <N, A, G> leafNode, IImageBlock <A> imageBlock ) {
        leafNode.setImageBlock(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError {
        segmentSquare(imageBlock);
    }

    /**
     * QUADRANTS *********************************************
     * <p>
     * 0  |  1
     * 2  |  3
     * OR
     * 0  |  1
     * 3  |  2
     *
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( @NotNull IImageBlock <A> imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();

        int w = imageBlock.getWidth() / 2;

        getDeque().push(imageBlock.subImage(x, y, w, w));
        getDeque().push(imageBlock.subImage(x + w, y, w, w));
        getDeque().push(imageBlock.subImage(x, y + w, w, w));
        getDeque().push(imageBlock.subImage(x + w, y + w, w, w));
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
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
    }
}
