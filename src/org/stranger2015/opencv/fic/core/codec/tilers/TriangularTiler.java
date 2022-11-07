package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class TriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    TriangularTiler ( IImage <A> image,
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
    public abstract
    ITiler <N, A, G> instance ();

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
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry (
            IImageBlock <A> imageBlock ) throws ValueError {

        return List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        return super.segmentSquare(imageBlock);
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