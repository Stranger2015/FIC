package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.EInterpolationType;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

import static java.lang.Double.MAX_VALUE;

/**
 * fractal compressor instance. combines the imageBlockGenerator and
 * comparator classes to create a fractal image model
 */
@SuppressWarnings({"unchecked", "rawtype"})
@Deprecated
public
class Compressor<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        /* implements ICompressor <N, A, G>*/ {

    private final ScaleTransform <A, G> scaleTransform;
    private final ImageBlockGenerator <N, A, G> imageBlockGenerator;
    private final IDistanceator <M, A> comparator;
    private final Set <ImageTransform <M, A, G>> transforms;
    private final Set <IImageFilter <M, A>> filters;

    protected final FractalModel <N, A, G> fractalModel;

    /**
     * @param scaleTransform      the scale difference between the ranges and the domains
     * @param imageBlockGenerator the tiler used to tile the image
     * @param comparator          the comparator used to compare the tiles of the image
     * @param transforms          a list of transform to apply to the tiles of the image
     * @param fractalModel
     * @throws NullPointerException if any field is null
     * @see Observable
     * @see Observer#update(java.util.Observable, java.lang.Object)
     */
    public
    Compressor ( final ScaleTransform <A, G> scaleTransform,
                 final ImageBlockGenerator <N, A, G> imageBlockGenerator,
                 final IDistanceator <M, A> comparator,
                 final Set <ImageTransform <M, A, G>> transforms,
                 FractalModel <N, A, G> fractalModel )

            throws NullPointerException {
        //16 (12 in sip)??????
        this(scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                new HashSet <>(0),
                fractalModel);
    }

    /**
     * @param scaleTransform      the scale difference between the ranges and the domains
     * @param imageBlockGenerator the tiler used to tile the image
     * @param comparator          the comparator used to compare the tiles of the image
     * @param transforms          a set of transform to apply to the tiles of the image
     * @param filters             a set of filters to apply to the image for normalization
     *                            //     * @param observer            an observer receiving progress results from {@code compress} - allowed to be null
     * @param fractalModel
     * @throws NullPointerException if any field is null
     * @see Observable
     * @see Observer#update(java.util.Observable, java.lang.Object)
     */
    public
    Compressor ( final ScaleTransform <A, G> scaleTransform,
                 final ImageBlockGenerator <N, A, G> imageBlockGenerator,
                 final IDistanceator <M, A> comparator,
                 final Set <ImageTransform <M, A, G>> transforms,
                 final Set <IImageFilter <M, A>> filters,
                 FractalModel <N, A, G> fractalModel )
            throws NullPointerException {
        super();

        assert (comparator != null) && (transforms != null) && (filters != null)
                && (imageBlockGenerator != null) && (scaleTransform != null) : "Null elements now allowed";

        this.fractalModel = fractalModel;
        this.comparator = comparator;
        this.imageBlockGenerator = imageBlockGenerator;
        this.filters = filters;
        this.transforms = transforms;
        this.scaleTransform = scaleTransform;

//        if (observer != null) {
//            this.addObserver(observer);
//        }
    }

    /**
     * @return
     */
    public
    FractalModel <N, A, G> getFractalModel () {
        return fractalModel;
    }

    /**
     * @return
     */
    public
    ScaleTransform <A, G> getScaleTransform () {
        return scaleTransform;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <N, A, G> getImageBlockGenerator () {
        return imageBlockGenerator;
    }

    /**
     * Compress a given image. Compressions takes place as a mapping of
     * small images and transforms to points. Applying the transforms
     * to the images and placing the resulted transformed images
     * to the mapped points, the original image is reassembled.
     *
     * @param image the image to compress
     * @return a mapping of points to images and transforms.
     */
    public
    FractalModel <N, A, G> compress ( M image ) {
        assert image != null : "Cannot compress null image";
        /*
         * Normalization. Before tiling the image, pass it throw a set of filters.
         * This might improve results, if used wisely.
         */
        for (IImageFilter <M, A> filter : filters) {
            image = (M) filter.filter((GrayScaleImage <A>) image);
        }

        /*
         * range blocks are the tiles of the original (but filtered) image.
         */
        List <ImageBlock <A>> rangeBlocks = imageBlockGenerator.generateRangeBlocks((GrayScaleImage <A>) image);//fixme
        int rangesSize = rangeBlocks.size();

        /**
         * the domain image to tile, is a scaled factor of the size
         * of the original image {@code (scaleX * width; scaleY * height)}.
         * domain and range blocks have the same size, but, domain
         * blocks are less in amount than range blocks.
         *
         *     #domains = (scalex * w) * (scaley * h)
         *     #domains = scalex * scaley * #ranges
         *
         * We pre-calculate all transforms to hold all possible
         * transformed and original domain blocks.
         *
         *     #alldomains = #transforms * scalex * scaley * #ranges
         *
         * We map each result of a transform with the original
         * domain and transform operation which resulted it.
         */
        Map <M, Entry <M, ImageTransform <M, A, G>>> domainBlocks =
                new HashMap <>(
                        (int) (transforms.size() * rangesSize * scaleTransform.getScaleX() * scaleTransform.getScaleY()));

        //fixme
        for (ImageBlock <A> domainImg : imageBlockGenerator.generateDomainBlocks((GrayScaleImage <A>) scaleTransform.transform(
                image,
                (M) new Mat(),
                EInterpolationType.BILINEAR))
          ) {

            var imageTransformConsumer = (Consumer <? super ImageTransform <M, A, G>>) transform ->
                    domainBlocks.put(
                            transform.transform(
                                    (M) domainImg,
                                    (M) new Mat(),
                                    EInterpolationType.BILINEAR
                            ),
                            new SimpleEntry <>(
                                    (M) domainImg,
                                    transform
                            )
                    );

            transforms.forEach(imageTransformConsumer);
        }

        /*
          * mapping between a range position and
         * most suitable domainBlock and transform
         * that produce that range
         */
        Map <Point, Entry <M, ImageTransform <M, A, G>>> simpleModel = new HashMap <>(rangesSize);
        int width = image.getWidth() / rangeBlocks.get(0).getWidth();

        /*
         * After the end of each domain loop, or in other words, before
         * we iterate to the next range image, we have found the best
         * match for the current (and all previous) range images.
         * Thus, we do not need to hold a mapping between the range image
         * and the domain image along with its distance. We hold the best
         * (minimum) distance found so far, in the variable, which is
         * reset in every range image iteration.
         *
         * if the difference is smaller than the best current
         * then add the rangeBlock's position and appropriate
         * domainBlock and transformation.
         */
        for (int rangeIdx = 0; rangeIdx < rangesSize; rangeIdx++) {
            Point point = Utils.indexToPoint(rangeIdx, width);
            double mindiff = MAX_VALUE;

            for (M domainBlock : domainBlocks.keySet()) {
                double diff = comparator.distance((M) rangeBlocks.get(rangeIdx), domainBlock);

                /*
                 * If we haven't seen the image before (which means
                 * this is a new range image iteration), we store
                 * the distance and map the range and domain images.
                 * If we've seen the image before, but the comparison
                 * yields better results (the new difference is smaller
                 * than the best (minimum) so far), we update the best
                 * difference and map the range to the new domain image.
                 */
                if (!simpleModel.containsKey(point) || (mindiff > diff)) {
                    simpleModel.put(point, domainBlocks.get(domainBlock));
                    mindiff = diff;
                }
            }

//            this.setChanged();
//            this.notifyObservers(new int[]{rangeIdx, rangesSize});
        }

        return new FractalModel <>(simpleModel);
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
//    @Override
    public
    FractalModel <N, A, G> compress ( M image, int sourceSize, int destinationSize, int step ) {

        return new FractalModel <>(new HashMap <>());
    }

    /**
     * @return
     */
//    @Override
    public
    Set <ImageTransform <M, A, G>> getTransforms () {
        return transforms;
    }

    /**
     * @return
     */
//    @Override
    public
    FractalModel <N, A, G> getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
//    @Override
    public
    FractalModel <N, A, G> loadModel ( String filename ) {
        return null;
    }

    /**
     * @return
     */
    public
    Set <IImageFilter <M, A>> getFilters () {
        return filters;
    }

    /**
     * @return
     */
    public
    IDistanceator <M, A> getComparator () {
        return comparator;
    }
}
