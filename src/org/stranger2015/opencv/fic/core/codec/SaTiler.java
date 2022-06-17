package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.IShape.*;

public
class SaTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>

        implements ITiler <N, A, G> {

    /**
     * @param image
     * @param minRangeSize
     * @param queue
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> tile ( IImage <A> image, IntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError, MinimalRangeSizeReached {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IntSize getRangeSize () {
        return null;
    }

    /**
     * @return +
     */
    @Override
    public
    IntSize getDomainSize () {
        return null;
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
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) throws ValueError {

    }
}
