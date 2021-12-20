package org.stranger2015.opencv.fic.core;

import org.opencv.highgui.HighGui;
import org.stranger2015.opencv.fic.core.LoadSaveImageTask.BidiImageColorModelTask;
import org.stranger2015.opencv.fic.core.LoadSaveImageTask.NormalizeImageShapeTask;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.EncodeAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.QUAD_TREE;

/**
 *
 */
public
class ImageProcessor<N extends TreeNode <N>, M extends Image, C extends CompressedImage>
        extends CompositeTask <M>
        implements IImageProcessor <N, M, C> {

    //    private final M image;
    private final String imageFilename;
    private final EPartitionScheme scheme;
    private final Codec <N,M, C> codec;

    /**
     * @param scheme
     * @param imageFilename
     */
    public
    ImageProcessor ( String imageFilename, EPartitionScheme scheme, List <Task <M>> tasks ) {
        super(tasks);

        this.scheme = scheme;
        this.imageFilename = imageFilename;

        final List <Task <M>> preprocTasks = new ArrayList <>();
        final List <Task <M>> postprocTasks = new ArrayList <>();
//        BidiTask <N, M> task1 = new LoadSaveImageTask(imageFilename, tasks);
        BidiTask < M> task1 = new NormalizeImageShapeTask <>(tasks);
        BidiTask < M> task2 = new BidiImageColorModelTask <>(tasks);
        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

        codec = (Codec <N,M, C>) create(scheme, new EncodeAction(imageFilename));
    }

    protected abstract
    IImageProcessor <N, M, C> create ( EPartitionScheme scheme, EncodeAction action ) {
        return null;
    }

//    public
//    ImageProcessor ( String filename ) {
//        super(filename);
//    }

    public static
    <N extends TreeNode <N>, M extends Image, C extends CompressedImage>
    IImageProcessor <N, M, C> create ( String filename ) {
        LoadSaveImageTask <M> loadSaveImageTask = new LoadSaveImageTask <>(filename, Collections.emptyList());
        loadSaveImageTask.execute();

        return new ImageProcessor <N, M, C>(filename, Collections.emptyList());
    }

    /**
     *
     */
    public
    M process ( M inImage ) {
        M outImage = preprocessor.process(inImage);
        outImage = execute();

        return postprocessor.process(outImage);
    }

    /**
     *
     */
    @Override
    public
    M process () {
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

        ImagePartitionProcessor <N, M, C> processor =
                new ImagePartitionProcessor <>(
                        imageOut,
                        QUAD_TREE);
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
    int getNearestGreaterPow2 ( int n ) {
        int ngp2 = 1;
        for (; ngp2 < n; ngp2 *= 2) {

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
    Codec <N,M, C> getCodec () {
        return codec;
    }

    /**
     * @return
     */
    @Override
    public
    M preprocess () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    M postprocess () {
        return null;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M preprocess ( M image ) {
        return null;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M postprocess ( M image ) {
        return null;
    }

    /**
     */
    @Override
    public
    M execute () {
        for (Task <M> task : tasks) {
            image = task.execute();
        }

        return image;
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
}