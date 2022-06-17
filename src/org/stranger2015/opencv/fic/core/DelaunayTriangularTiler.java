package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.IncrementalDelaunayTriangulator;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayTriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G> {
    /**
     * @return
     */
    public
    IncrementalDelaunayTriangulator <N, A, G> getTriangulator () {
        return triangulator;
    }

    /**
     *
     */
    protected
    IncrementalDelaunayTriangulator <N, A, G> triangulator;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    DelaunayTriangularTiler ( IImage <A> image,
                              IIntSize rangeSize,
                              IIntSize domainSize,
                              IEncoder <N, A, G> encoder,
                              ITreeNodeBuilder <N, A, G> builder
    ) {

        super(image, rangeSize, domainSize, encoder, builder);

        triangulator = new IncrementalDelaunayTriangulator <>(points, toler);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new DelaunayTriangularTiler <>(
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
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentTriangle(imageBlock);
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

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }
}
