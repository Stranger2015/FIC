package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 */
public
class QuadTreeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeEncoder <N, A, G> {

    /**
     * @param inputImage
     */
    public
    QuadTreeEncoder ( EPartitionScheme scheme,
                      TreeNodeBuilder <N, A, G> nodeBuilder,
                      IPartitionProcessor <N, A, G> partitionProcessor,
                      ISearchProcessor <N, A, G> searchProcessor,
                      ScaleTransform <A, G> scaleTransform,
                      ImageBlockGenerator <N, A, G> imageBlockGenerator,
                      IDistanceator <A> comparator,
                      Set <ImageTransform <A, G>> transforms,
                      Set <IImageFilter <A>> filters,
                      FractalModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return null;//todo
    }
//

    /**
     * /**
     *
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                                                         EPartitionScheme scheme,
                                                         IEncoder <N, A, G> encoder,
                                                         IImage <A> image,
                                                         IIntSize rangeSize,
                                                         IIntSize domainSize ) {
        return new QuadTreeImageBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> getPartitionProcessor () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> getModel () {
        return null;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> encode ( IImage <A> image ) {
        return image;
    }

    /**
     * @param image
     * @param axis
     * @return
     */
    @Override
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return image;
    }

    /**
     * @return
     */
//    @Override
    public
    ISearchProcessor <N, A, G> createSearchProcessor () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FractalModel <N, A, G> loadModel ( String filename ) {
        FractalReader <N, A, G> reader = new FractalReader <>(new File(filename));

        return new FractalModel <>(null);
    }
}
