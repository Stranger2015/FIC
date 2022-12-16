package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

public
class EncoderFactory {
    public
    EncoderFactory () {
    }

//    public
//    IEncoder<N,A,G> createEncoder(){
//        EPartitionScheme scheme,
//        ITreeNodeBuilder <N> nodeBuilder,
//        IPartitionProcessor <N> partitionProcessor,
//        ISearchProcessor <N> searchProcessor,
//        ScaleTransform  scaleTransform,
//        ImageBlockGenerator <N> imageBlockGenerator,
//        IDistanceator  comparator,
//        Set <ImageTransform> imageTransforms,
//        Set <IImageFilter > imageFilters,
//        FCImageModel <N> fractalModel    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     * @return
     */
    public
    ITiler createTiler (
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder encoder,
            ITreeNodeBuilder <?> builder ) throws ReflectiveOperationException {

        return null;//encoder.getTilerClass().getConstructor().newInstance(
//                image,
//                rangeSize,
//                domainSize,
//                encoder,
//                builder);
    }
}
