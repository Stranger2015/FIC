package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.core.codec.tilers.SipTiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class SipbVrEncoder extends SipEncoder {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipbVrEncoder ( String fileName,
                    EPartitionScheme scheme,
                    ICodec codec,
                    List <Task> tasks,
                    EtvColorSpace colorSpace,
                    ITreeNodeBuilder <?> nodeBuilder,
                    IPartitionProcessor partitionProcessor,
                    ISearchProcessor searchProcessor,
                    ScaleTransform scaleTransform,
                    ImageBlockGenerator <?> imageBlockGenerator,
                    IDistanceator comparator,
                    Set <ImageTransform> imageTransforms,
                    Set <IImageFilter> imageFilters,
                    FCImageModel fractalModel ) {

        super(
                fileName,
                scheme,
                codec,
                tasks,
                colorSpace,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel
        );
    }

    /**
     * @return
     */
    @Override
    public
    ITiler createPartition0 () {
        return new SipTiler(
                getImage(),
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor createPartitionProcessor ( ITiler tiler ) {
        return super.createPartitionProcessor(tiler);
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel loadModel ( String filename ) throws Exception {
        return null;
    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    IImageBlock selectBest (POOL) {
//        return null;
//    }

    /**
     * @param roi
     * @param rangeBlocks
     * @return
     */
    @Override
    public
    IImageBlock iterateRangeBlocks ( IImageBlock roi, Pool <IImageBlock> rangeBlocks ) throws ValueError {
        return super.iterateRangeBlocks(roi, rangeBlocks);
    }

    /**
     * @param rangeBlock
     * @param domainBlocks
     * @param minDistance
     * @param bestTransform
     * @return
     */
    @Override
    public
    IImageBlock selectDomainBlocks ( IImageBlock rangeBlock,
                               Pool <IImageBlock> domainBlocks,
                               double[] minDistance,
                               ImageTransform bestTransform
    ) throws ValueError {

        super.selectDomainBlocks(rangeBlock, domainBlocks, minDistance, bestTransform);
    }

    /**
     * @param rangeBlock
     * @param domainBlock
     * @return
     */
    @Override
    public
    double[] getDistance ( IImageBlock rangeBlock, IImageBlock domainBlock ) {
        return super.getDistance(rangeBlock, domainBlock);
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    @NotNull
    @Override
    public
    <V> Function <String, V> andThen ( @NotNull Function <? super IImage, ? extends V> after ) {
        return null;
    }

    /**
     * @param outputImage
     * @return
     */
    @Override
    public
    CompressedImage postprocess ( CompressedImage outputImage ) {
        return super.postprocess(outputImage);
    }

    /**
     * @param inputImage
     * @return
     */
    @Override
    public
    IImage preprocess ( IImage inputImage ) throws ValueError {
        return super.preprocess(inputImage);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec codec ) {
        super.onCodecCreated(codec);
    }
}
