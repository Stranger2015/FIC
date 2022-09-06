package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class QuadTreeTopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends QuadTreeTiler <N, A, G>
        implements ITopDownTiler <N, A, G>
{
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

    @Override
    public
    ITiler <N, A, G> instance () {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentSquare ( @NotNull IImageBlock <A> imageBlock ) throws ValueError {
        List <IImageBlock <A>> result1 = null;

        return result1;
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }
}
