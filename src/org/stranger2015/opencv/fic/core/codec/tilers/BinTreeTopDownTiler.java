package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreeTopDownTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTreeTiler <N>
        implements ITopDownTiler <N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    BinTreeTopDownTiler ( IImage image,
                          IIntSize rangeSize,
                          IIntSize domainSize,
                          IEncoder <N> encoder,
                          ITreeNodeBuilder <N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N> instance () {
        return new BinTreeTopDownTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        super.onSuccessors(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        super.onSuccessor(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
//        return List.of(imageBlock);
    }

    /**
     * @param <N>
     */
    public static
    class BuildTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
            extends TreeNodeTask <N> {
        /**
         * @param scheme
         * @param codec
         * @param tasks
         */
        public
        BuildTask ( EPartitionScheme scheme, ICodec <N> codec, List <Task <N>> tasks ) {
            super(scheme, codec, tasks);
        }
    }

    /**
     * @param domainPool
     */
    public
    void BuildTask ( List <IImageBlock > domainPool ) {
    }
}

