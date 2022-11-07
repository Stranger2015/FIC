package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeTopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeTiler <N, A, G>
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
    BinTreeTopDownTiler ( IImage <A> image,
                          IIntSize rangeSize,
                          IIntSize domainSize,
                          IEncoder <N, A, G> encoder,
                          ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return new BinTreeTopDownTiler<>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError {
        //return segmentRectangle(imageBlock);
    }

    @Override
    public
    void onAddNode ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {

    }

    @Override
    public
    void onAddLeafNode ( LeafNode <N, A, G> leafNode, IImageBlock <A> imageBlock ) {

    }

    @Override
    public
    Logger getLogger () {
        return logger;
    }

    @Override
    public
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
//        return List.of(imageBlock);
    }

    /**
     * @param <N>
     */
    public static
    class BuildTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
            extends TreeNodeTask <N, A, G> {
        /**
         * @param scheme
         * @param codec
         * @param tasks
         */
        public
        BuildTask ( EPartitionScheme scheme, ICodec <N, A, G> codec, List <Task <N, A, G>> tasks ) {
            super(scheme, codec, tasks);
        }
    }

    /**
         * @param domainPool
         */
        public
        void BuildTask ( List <IImageBlock<A>> domainPool ) {
//            super(domainPool, new ArrayList <>());
        }
    }

