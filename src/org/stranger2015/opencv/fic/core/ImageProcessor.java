package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.DomainPool;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EDirection.NORTH_WEST;
import static org.stranger2015.opencv.fic.core.Tree.DEFAULT_BOUNDING_BOX;

/**
 *
 */
public
class ImageProcessor<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends CompositeTask
        implements IImageProcessor <N, A, M> {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final EPartitionScheme scheme;
    private final Codec <N, A, M> codec;
    private M image;
    private EncodeAction action;
    List <IImageProcessorListener> listeners = new ArrayList <>();

    /**
     * @param scheme
     * @param imageFilename
     */
//    @SuppressWarnings("unchecked")
    public
    ImageProcessor ( String imageFilename, EPartitionScheme scheme, List <Task> tasks ) {
        super(imageFilename, tasks);

        this.scheme = scheme;
        BidiTask task1 = new NormalizeImageShapeTask(filename, scheme, tasks);//############
        BidiTask task2 = new BidiImageColorModelTask(filename, scheme, tasks);

        final List <Task> preprocTasks = new ArrayList <>(2);
        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        final List <Task> postprocTasks = new ArrayList <>(2);
        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

        LoadSaveImageTask loadSaveImageTask = new LoadSaveImageTask(filename, scheme, tasks);
        loadSaveImageTask.accept(filename);

        Codec.
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
    <N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
    ImageProcessor <N, A, M> create ( String filename, EPartitionScheme scheme, List <Task> tasks ) {

        return new ImageProcessor <>(filename, scheme, tasks);
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
     * <p>
     * 5. Record the transformed domain block which is found to be the best approximation for
     * the current range block is assigned to that range block.
     * <p>
     * 6. Next domain block [1].
     */
    @SuppressWarnings("unchecked")
    public
    M process ( M image ) {
//        List <M> rangeBlocks = createRangeBlocks(image, 4, 4);
//        List <M> domainBlocks = createDomainBlocks(image, 8, 8);
        IEncoder <N, A, M> encoder = codec.getEncoder(image,
                createRangeBlocks(image, 4, 4),
                createDomainBlocks(image, 8, 8));
        List <ImageTransform <M>> img = encoder.compress(image, -1, -1, -2);

        return (M) new CompressedImage(image);
    }

    /**
     * @param image
     * @param w
     * @param h
     * @return
     */
    private @NotNull
    List <M> createRangeBlocks ( M image, int w, int h ) {
        return createBlocks(image, w, h);
    }

    @SuppressWarnings("unchecked")
    private
    List <M> createBlocks ( M image, int w, int h ) {
        List <M> l = new ArrayList <>();
        for (int i = 0, width = image.width(); i < width; i += w) {
            for (int j = 0, height = image.height(); j < height; j += h) {
                l.add((M) image.submat(i, j, i + w, j + h));
            }
        }

        return l;
    }

    protected
    List <M> createDomainBlocks ( M image, int w, int h ) throws ValueError {
        List <M> l = new ArrayList <>();
        TreeNodeAction <N, A, M> action = new TreeNodeAction <>(new DomainPool<>(), new NodeList <>());
        final Tree <N, A, M> tree = Tree.create();
                new QuadTree <>(
                        new QuadTreeNode <>(
                                null,
                                NORTH_WEST,
                                DEFAULT_BOUNDING_BOX
                        ),
                        image,
                        action);

        final TreeNode <N> root = quadTree.getRoot();
        TreeNodeBase <N> node = root.getChildren().get(0);

        for (int i = 0, width = image.width(); i < width / w; i++, width /= 2) {
            for (int j = 0, height = image.height(); j < height / h; j++, height /= 2) {

            }
        }

        return l;
    }

    /**
     *
     */
    @Override
    public
    M process () {
        logger.info("Performing " + getClass());

        accept(filename);

        for (IImageProcessorListener listener : listeners) {
            listener.onProcess();
        }

        return null;
    }

    /**
     * @return
     */
    @Override
    public
    M getImage () {
        return null;
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
        M imageOut = (M) new CompressedImage(image);

        HighGui.namedWindow("OpenCV");

        System.out.println(imageOut.dump());

//        ImageProcessor <N, A, M> processor = new ImagePartitionProcessor <N, A, M>(imageOut, QUAD_TREE);
        HighGui.destroyAllWindows();

        image.release();
        imageOut.release();

        return imageOut;
    }

    /**
     * @param n
     * @return
     */
    public static
    int getNearestGreaterPowBase ( int n, int base ) {

        // pow(base, n);
        int ngp2 = 1;
        while (ngp2 < n) {
            ngp2 *= base;
        }

        return ngp2;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    public
    Codec <N, A, M> getCodec () {
        return codec;
    }

    /**
     * @return
     */
    @Override
    public
    M preprocess () {
        return null;//filename;
    }

    /**
     * @return
     */
    @Override
    public
    M postprocess () {
        return null;//todo
    }

    public
    EncodeAction getAction () {
        return action;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {
        for (Task task : tasks) {
            task.accept(s);
        }
    }
}