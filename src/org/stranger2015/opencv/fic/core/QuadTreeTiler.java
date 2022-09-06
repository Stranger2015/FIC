package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.AlterBinTreeTiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class QuadTreeTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends AlterBinTreeTiler <N, A, G> {

       /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    QuadTreeTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public abstract
    ITiler <N, A, G> instance ();

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
    List <IImageBlock <A>> segmentSquare ( @NotNull IImageBlock <A> imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();
        int w = imageBlock.getWidth() / 2;

        IImageBlock <A> result1 = imageBlock.subImage(x, y, w, w);
        IImageBlock <A> result2 = imageBlock.subImage(x + w, y, w, w);
        IImageBlock <A> result3 = imageBlock.subImage(x, y + w, w, w);
        IImageBlock <A> result4 = imageBlock.subImage(x + w, y + w, w, w);

        return List.of(result1, result2, result3, result4);
    }

    /**
     * @param imageBlock
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) {
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
