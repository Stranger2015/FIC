package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.highgui.HighGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.stranger2015.opencv.fic.core.EDirection.NORTH_WEST;
import static org.stranger2015.opencv.fic.core.Tree.DEFAULT_BOUNDING_BOX;

/**
 *
 */
public
class ImageProcessor<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends BidiTask <N, A, M, G>
        implements IImageProcessor <N, A, M, G> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final ICompressor <N, A, M, G> compressor;
    private final IDecompressor <N, A, M, G> decompressor;
    private final EPartitionScheme scheme;
    private final ICodec <N, A, M, G> codec;
    private final EtvColorSpace colorSpace;

    private M image;
    private EncodeTask <N, A, M, G> task;

    List <IImageProcessorListener> listeners = new ArrayList <>();

    /**
     * @param imageFilename
     * @param scheme
     * @param codec
     */
//    @SuppressWarnings("unchecked")
    public
    ImageProcessor ( String imageFilename,
                     ICompressor <N, A, M, G> compressor,
                     IDecompressor <N, A, M, G> decompressor,
                     EPartitionScheme scheme,
                     List <Task <N, A, M, G>> tasks,
                     ICodec <N, A, M, G> codec,
                     EtvColorSpace colorSpace ) {

        super(imageFilename, scheme, tasks);

        this.compressor = compressor;
        this.decompressor = decompressor;

        this.scheme = scheme;
        this.codec = codec;
        this.colorSpace = colorSpace;

        BidiTask <N, A, M, G> task1 = new NormalizeImageTask <>(imageFilename, scheme, tasks);
        BidiTask <N, A, M, G> task2 = new ColorspaceConversionTask <>(imageFilename, scheme, colorSpace,
                new FromRgbToTvColorspaceConversionTask <>(
                        imageFilename,
                        scheme,
                        colorSpace,
                        List.of()),
                new FromTvToRgbColorspaceConversionTask <N, A, M, G>(
                        imageFilename,
                        scheme,
                        colorSpace,
                        List.of())
        );

        final List <Task <N, A, M, G>> preprocTasks = new ArrayList <>(2);

        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        final List <Task <N, A, M, G>> postprocTasks = new ArrayList <>(2);

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

//        Codec.create()
//        codec = new DefaultCodec<>(scheme, new EncodeAction(filename), getFilename());
    }

    /**
     * @param <N>
     * @param <M>
     * @param <A>
     * @param filename
     * @return
     */
    public static
    <N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
    ImageProcessor <N, A, M, G> create ( String filename,
                                         ICompressor <N, A, M, G> compressor,
                                         IDecompressor <N, A, M, G> decompressor,
                                         EPartitionScheme scheme,
                                         List <Task <N, A, M, G>> tasks,
                                         ICodec <N, A, M, G> codec,
                                         EtvColorSpace colorSpace ) {

        return new ImageProcessor <>(
                filename,
                compressor,
                decompressor,
                scheme,
                tasks,
                codec,
                colorSpace);
    }

    /**
     * 1. Divide the image into range block.
     * <p>
     * 2. Divide  the  image  into  non-overlapping  domain  blocks,  Di.
     * <p>
     * The  union  of  the  domain  blocks  must  cover  the  entire
     * image, G, but they can be any size or shape [1].
     * <p>
     * 3. Define  a  finite  set  of  contractive  affine  transformations,  wi
     * (which map from a range block R to a domain block Di).
     * <p>
     * 4. For each domain block {
     * <p>
     * For each range block {
     * For each transformation {
     * Calculate the Hausdorff distance h(wi(R  G), Di  G) (or use another metric)
     * }
     * }
     * }
     * <p>
     * 5. Record the transformed domain block which is found to be the best approximation for
     * the current range block is assigned to that range block.
     * <p>
     * 6. Next domain block [1].
     *
     * @param image
     */
    @SuppressWarnings("unchecked")
    public
    M process ( M image ) throws ValueError {
//        List <M> rangeBlocks = createRangeBlocks(image, 4, 4);
//        List <M> domainBlocks = createDomainBlocks(image, 8, 8);
        IEncoder <N, A, M, G> encoder = codec.getEncoder();
        FractalModel<N,A,M,G> fractalModel = compressor.compress(image, -1, -1, -2);

        for (IImageProcessorListener listener : listeners) {
            listener.onProcess();
        }


        return (M) new CompressedImage <>(image);
    }

    /**
     * @param image
     * @param w
     * @param h
     * @return
     */
    protected @NotNull
    List <M> createRangeBlocks ( M image, int w, int h ) {
        return createBlocks(image, w, h);
    }

    /**
     * @param image
     * @param w
     * @param h
     * @return
     */
    @SuppressWarnings("unchecked")
    private @NotNull
    List <M> createBlocks ( M image, int w, int h ) {
        List <M> l = new ArrayList <>();
        for (int i = 0, width = image.getWidth(); i < width; i += w) {
            for (int j = 0, height = image.getHeight(); j < height; j += h) {
                l.add((M) image.submat(i, j, i + w, j + h));
            }
        }

        return l;
    }

    /**
     * @param image
     * @param w
     * @param h
     * @return
     * @throws ValueError
     */
    protected
    List <M> createDomainBlocks ( M image, int w, int h ) throws ValueError {
        List <M> l = new ArrayList <>();
        TreeNodeAction <N, A, M, G> action =
                new TreeNodeAction <N, A, M, G>(new ArrayList <>(), new ArrayList <>());
        final Tree <N, A, M, G> tree = Tree.create("??");//fixme
        new QuadTree <>(
                new QuadTreeNode <>(
                        null,
                        NORTH_WEST,
                        DEFAULT_BOUNDING_BOX
                ),
                image,
                action);

        final TreeNode <N, A, M, G> root = tree.getRoot();
        TreeNodeBase <N, A, M, G> node = root.getChildren().get(0);

        for (int i = 0, width = image.getWidth(); i < width / w; i++, width /= 2) {
            for (int j = 0, height = image.getHeight(); j < height / h; j++, height /= 2) {

            }
        }

        return l;
    }

    /**
     * @return
     */
    @Override
    public
    M getImage () {
        return image;
    }

//    def generate_all_transformed_blocks(img, source_size, destination_size, step):
//    factor = source_size // destination_size
//    transformed_blocks = []
//            for k in range((img.shape[0] - source_size) // step + 1):
//        for l in range((img.shape[1] - source_size) // step + 1):
//            # Extract the source block and reduce it to the shape of a destination block
//    S = reduce(img[k*step:k*step+source_size,l*step:l*step+source_size], factor)
//            # Generate all possible transformed blocks
//            for direction, angle in candidates:
//            transformed_blocks.append((k, l, direction, angle, apply_transform(S, direction, angle)))
//            return transformed_blocks

    /**
     * @param image
     */
    @SuppressWarnings("unchecked")
    protected
    M compressImage ( M image ) {
        M imageOut = (M) new CompressedImage <>(image);

        HighGui.namedWindow("OpenCV");

        System.out.println(imageOut.dump());

        HighGui.destroyAllWindows();

        image.release();
        imageOut.release();

        return imageOut;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     * @return
     */
    @Override
    public
    ICodec <N, A, M, G> getCodec () {
        return codec;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    M preprocess ( String filename ) {
        M image=null;
        if (this.image == null) {
            image=task.loadImage(filename);
        }

        for (IImageProcessorListener listener : listeners) {
            listener.onPreprocess();
        }

        return image;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M postprocess ( M image ) {
        for (IImageProcessorListener listener : listeners) {
            listener.onPostprocess();
        }

        return image;//todo
    }

    public
    EncodeTask <N, A, M, G> getAction () {
        return task;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    M execute ( String filename ) throws ValueError {
        super.execute(filename);

        M inputImage = preprocess(filename);
        M outputimage = process(inputImage);

        return postprocess(outputimage);
    }

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     * @see #andThen(Function)
     */
    @Override
    public
    <V> Function <V, M> compose ( @NotNull Function <? super V, ? extends String> before ) {
        return super.compose(before);
    }

    /**
     * @return
     */
   public
    ICompressor <N, A, M, G> getCompressor () {
        return compressor;
    }

    /**
     * @return
     */
    public
    IDecompressor <N, A, M, G> getDecompressor () {
        return decompressor;
    }

    /**
     * @return
     */
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     * @param image
     */
    public
    void setImage ( M image ) {
        this.image = image;
    }

    /**
     * @param task
     */
    public
    void setTask ( EncodeTask <N, A, M, G> task ) {
        this.task = task;
    }
}