package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITopDownTiler;
import org.stranger2015.opencv.utils.BitBuffer;

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
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <N, A, G> node ) {
        leaves.add(node);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        segmentPolygon(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock )
            throws ValueError {
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
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();

        int w = imageBlock.getWidth() / 2;

        IImageBlock <A> r1 = imageBlock.getSubImage(x, y, w, w);
        IImageBlock <A> r2 = imageBlock.getSubImage(x + w, y, w, w);
        IImageBlock <A> r3 = imageBlock.getSubImage(x, y + w, w, w);
        IImageBlock <A> r4 = imageBlock.getSubImage(x + w, y + w, w, w);

        getImageBlockDeque().push(r1);
        getImageBlockDeque().push(r2);
        getImageBlockDeque().push(r3);
        getImageBlockDeque().push(r4);

        getNodeDeque().push(node.createNode(node, r1.getAddress(r1.getX(), r1.getY())));
        getNodeDeque().push(node.createNode(node, r2.getAddress(r2.getX(), r2.getY())));
        getNodeDeque().push(node.createNode(node, r3.getAddress(r3.getX(), r3.getY())));
        getNodeDeque().push(node.createNode(node, r4.getAddress(r4.getX(), r4.getY())));
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        segmentSquare(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }
}
