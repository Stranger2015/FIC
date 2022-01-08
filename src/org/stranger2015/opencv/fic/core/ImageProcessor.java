package org.stranger2015.opencv.fic.core;

import org.opencv.highgui.HighGui;
import org.stranger2015.opencv.fic.core.LoadSaveImageTask.BidiImageColorModelTask;
import org.stranger2015.opencv.fic.core.LoadSaveImageTask.NormalizeImageShapeTask;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.EncodeAction;
import org.stranger2015.opencv.fic.core.codec.IImageProcessorListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.QUAD_TREE;
import static org.stranger2015.opencv.fic.core.codec.IAddressMath.pow;

/**
 *
 */
public
class ImageProcessor<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends CompositeTask <M>
        implements IImageProcessor <N, A, M> {

    private String imageFilename;
    private EPartitionScheme scheme;
    private Codec <N, A, M> codec;
    private M image;
    private EncodeAction action;
    List <IImageProcessorListener> listeners = new ArrayList <>();

    /**
     * @param scheme
     * @param imageFilename
     */
    @SuppressWarnings("unchecked")
    public
    ImageProcessor ( String imageFilename, EPartitionScheme scheme, List <Task <M>> tasks ) {
        super(tasks);

        this.scheme = scheme;
        this.imageFilename = imageFilename;

        final List <Task <M>> preprocTasks = new ArrayList <>();
        final List <Task <M>> postprocTasks = new ArrayList <>();

        BidiTask <M> task1 = new NormalizeImageShapeTask <>(tasks);
        BidiTask <M> task2 = new BidiImageColorModelTask <>(tasks);

        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

//        EncodeAction action;
        codec = new Codec <>(scheme, new EncodeAction(imageFilename, "??<--------------!!!!!"));
    }

    /**
     * @param image
     * @param scheme
     * @param tasks
     */
    @SafeVarargs
    public
    ImageProcessor ( M image, EPartitionScheme scheme, Task <M>... tasks ) {
        this(image, scheme,Arrays.asList(tasks), "?????????");
    }

    public
    ImageProcessor ( M image, EPartitionScheme scheme, List<Task<M>> of, String s ) {
        super(image);
    }

    /**
     * @param filename
     * @param <N>
     * @param <M>
     * @param <A>
     * @return
     */
    public static
    <N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
    IImageProcessor <N, A, M> create ( String filename, EPartitionScheme scheme, List <Task <M>> tasks ) {
        LoadSaveImageTask <M> loadSaveImageTask = new LoadSaveImageTask <>(filename, List.of());
        loadSaveImageTask.execute();

        return new ImageProcessor <>(filename, scheme, tasks);
    }

    /**
     *
     */
    public
    M process ( M inImage ) {
//        M outImage = preprocessor.process(inImage);
//        outImage = execute();
//
//        return postprocessor.process(outImage);
        return inImage;//todo
    }

    /**
     *
     */
    @Override
    public
    M process () {
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

        ImagePartitionProcessor <N, A, M> processor = new ImagePartitionProcessor <>(imageOut, QUAD_TREE);
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
        return null;//todo
    }

    /**
     * @return
     */
    @Override
    public
    M postprocess () {
        return null;//todo
    }

    /**
     *
     */
    @Override
    public
    M execute () {
        M outputImage = image;
        for (Task <M> task : tasks) {
            outputImage = task.execute();
        }

        return outputImage;
    }

    /**
     * @return
     */
    public
    String getImageFilename () {
        return imageFilename;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param s the function argument
     * @return the function result
     */
    @Override
    public
    M apply ( String s ) {
        return null;
    }

    public
    EncodeAction getAction () {
        return action;
    }
}