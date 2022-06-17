package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class QuadTreeTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeTiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    QuadTreeTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
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
     * @return
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( @NotNull IImageBlock <A> imageBlock ) throws ValueError {
        int sideSize = imageBlock.getWidth() / 2;

        r[0] = new Square(0, 0, sideSize);
        r[1] = new Square(sideSize, 0, sideSize);
        r[2] = new Square(sideSize, sideSize, sideSize);
        r[3] = new Square(0, sideSize, sideSize);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException("QuadTreeTiler#segmentRectangle()");
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 4;
    }
}
