package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IComposite;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class CompositeTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G>
        implements IComposite <N, A, G>{

    protected final List <ITiler <N, A, G>> tilersPipeline = new ArrayList <>();

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    CompositeTiler ( IImage <A> image,
                     IIntSize rangeSize,
                     IIntSize domainSize,
                     IEncoder <N, A, G> encoder,
                     ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
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
                        Deque <IImageBlock <A>> queue )

            throws ValueError {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }

    /**
     * @return
     */
    @Override
    public
    List <ITiler <N, A, G>> getPipeline () {
        return tilersPipeline;//todo
    }
}
