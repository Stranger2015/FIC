package org.stranger2015.opencv.fic.core;

import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.EncodeAction;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.QUAD_TREE;
import static org.stranger2015.opencv.fic.core.codec.Codec.create;

/**
 *
 */
public
class ImageProcessor<N extends TreeNode <N>, M extends Image, C extends CompressedImage>
        extends CompositeTask <N, M>
        implements IImageProcessor <N, M, C> {

    private final M image;
    private final String imageFilename;
    private final EPartitionScheme scheme;
    private final Codec <M, C> codec;
    private final IImageProcessor <N, M, C> preprocessor;
    private final IImageProcessor <N, M, C> postprocessor;

    /**
     * @param image
     * @param scheme
     * @param imageFilename
     */
    public
    ImageProcessor ( M image, EPartitionScheme scheme, List <Task <N, M>> tasks, String imageFilename ) {
        super(tasks);

        this.image = image;
        this.scheme = scheme;
        this.imageFilename = imageFilename;

        List <Task <N, M>> preprocTasks = new ArrayList <>();
        List <Task <N, M>> postprocTasks = new ArrayList <>();
        BidiTask <N, M> task1 = new LoadImageTask(imageFilename, tasks);
        BidiTask <N, M> task2 = new NormalizeImageShapeTask(tasks);
        BidiTask <N, M> task3 = new SetImageYuvColorModelTask(tasks);
        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());
        preprocTasks.add(task3.getTask());

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());
        postprocTasks.add(task3.getInverseTask());

        preprocessor = new ImageProcessor <>(image, scheme, preprocTasks, imageFilename);
        postprocessor = new ImageProcessor <>(image, scheme, postprocTasks, imageFilename);
        codec = create(scheme, new EncodeAction(imageFilename));
    }

    /**
     *
     */
    public
    M process ( M inImage ) {
        M outImage = preprocessor.process(inImage);
        outImage = execute(outImage);

        return postprocessor.process(outImage);
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
    M getImage () {
        return image;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    public
    Codec <M, C> getCodec () {
        return codec;
    }

    @Override
    public
    IImageProcessor <N, M, C> getPreprocessor () {
        return preprocessor;
    }

    @Override
    public
    IImageProcessor <N, M, C> getPostprocessor () {
        return postprocessor;
    }

    /**
     * @param image
     */
    @Override
    public
    M execute ( M image ) {
        for (Task <N, M> task : tasks) {
            image = task.execute(image);
        }

        return image;
    }

    public
    String getImageFilename () {
        return imageFilename;
    }

    /**
     *
     */
    class LoadImageTask extends BidiTask <N, M> {
        private final String fileName;

        /**
         * @param fileName
         * @param tasks
         */
        public
        LoadImageTask ( String fileName, List <Task <N, M>> tasks ) {
            super(tasks);
            this.fileName = fileName;
        }

        /**
         * @param image
         */
        @SuppressWarnings("unchecked")
        @Override
        public
        M execute ( M image ) {
            M outImage = (M) new Image(Imgcodecs.imread(fileName, Imgcodecs.IMREAD_ANYCOLOR), image.size());
            if (outImage.empty()) {
                System.out.printf("Cannot read the image, `%s'", fileName);
                throw new RuntimeException();
            }

            return outImage;
        }

        /**
         * @return
         */
        public
        String getFileName () {
            return fileName;
        }
    }

    /**
     *
     */
    class NormalizeImageShapeTask extends BidiTask <N, M> {
        /**
         * @param tasks
         */
        public
        NormalizeImageShapeTask ( List <Task <N, M>> tasks ) {
            super(tasks);
        }

        /**
         * @param image
         */
        @SuppressWarnings("unchecked")
        @Override
        public
        M execute ( M image ) {
            int sideSize = getNearestGreaterPow2(Math.max(image.getHeight(), image.getWidth()));
            return (M) new Image(image, sideSize);
        }
    }

    /**
     *
     */
    class SetImageYuvColorModelTask extends BidiTask <N, M> {
        /**
         * @param tasks
         */
        public
        SetImageYuvColorModelTask ( List <Task <N, M>> tasks ) {
            super(tasks);
        }

        /**
         * @param image
         */
        @Override
        public
        M execute ( M image ) {
            image.convertTo();
            image.get;
            return null;
        }
    }
}