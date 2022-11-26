package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
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
class DelaunayTriangularBottomUpTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
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
    DelaunayTriangularBottomUpTiler ( IImage <A> image,
                                      IIntSize rangeSize,
                                      IIntSize domainSize,
                                      IEncoder <N, A, G> encoder,
                                      ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <N, A, G> node,  IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N, A, G> node,  IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentSquare (TreeNodeBase <N, A, G> node,  IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentTriangle (TreeNodeBase <N, A, G> node,  IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentTriangle(node, imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new DelaunayTriangularBottomUpTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }
}
