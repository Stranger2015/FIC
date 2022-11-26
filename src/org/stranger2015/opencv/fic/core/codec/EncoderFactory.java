package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

public
class EncoderFactory<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    public
    EncoderFactory () {
    }

//    public
//    IEncoder<N,A,G> createEncoder(){
//        EPartitionScheme scheme,
//        ITreeNodeBuilder <N, A, G> nodeBuilder,
//        IPartitionProcessor <N, A, G> partitionProcessor,
//        ISearchProcessor <N, A, G> searchProcessor,
//        ScaleTransform <A, G> scaleTransform,
//        ImageBlockGenerator <N, A, G> imageBlockGenerator,
//        IDistanceator <A> comparator,
//        Set <ImageTransform <A, G>> imageTransforms,
//        Set <IImageFilter <A>> imageFilters,
//        FCImageModel <N, A, G> fractalModel    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     * @return
     */
    public
    ITiler <N, A, G> createTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) throws ReflectiveOperationException {

        return null;//encoder.getTilerClass().getConstructor().newInstance(
//                image,
//                rangeSize,
//                domainSize,
//                encoder,
//                builder);
    }
}
