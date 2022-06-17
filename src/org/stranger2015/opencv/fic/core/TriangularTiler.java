package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static org.stranger2015.opencv.fic.core.IShape.*;

public abstract
class TriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G>{

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

    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }
}
