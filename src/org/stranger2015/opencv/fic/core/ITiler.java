package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <A>
 */
public interface ITiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @param <N>
     * @param <A>
     * @param <G>
     * @return
     */
    @Contract(pure = true)
    static <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    @NotNull ITiler <N, A, G> create ( IEncoder <N, A, G> encoder ) {
        @NotNull ITiler <N, A, G> tiler = encoder.getTiler().instance();

        return tiler;
    }

    ITiler <N, A, G> instance ();

    /**
     * @return
     */
    List <IImageBlock <A>> tile ( final IImage <A> image, IIntSize minRangeSize, Deque <IImageBlock <A>> queue )
            throws ValueError, MinimalRangeSizeReached;

    /**
     * @return
     */
    IIntSize getRangeSize ();

    /**
     * @return +
     */
    IIntSize getDomainSize ();

    /**
     * @return
     */
    default int successorAmount () {
        return 1;
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    void segmentShape (
            EShape imageBlockShape,
            IImageBlock <A> imageBlock,
            IIntSize minRangeSize,
            Deque <IImageBlock <A>> queue ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    default void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        throw new UnsupportedOperationException();
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param node
     */
    void addLeafNode ( TreeNode <N, A, G> node );
}
