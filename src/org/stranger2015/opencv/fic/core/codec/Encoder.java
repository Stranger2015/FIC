package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ImageBlock.EMPTY_ARRAY;

//=====================================================================================

/**
 * @param <N>
 * @param <M>
 * @param <C>
 */
public abstract
class Encoder<N extends TreeNode <N, A>, M extends Image, C extends CompressedImage, A extends IAddress <A>>
        implements IEncoder <N, M, C, A>, IConstants {

    public static final Size ZERO_SIZE = new Size(0, 0);

    protected final ImageBlockGenerator blockGenerator;
    protected final List <ImageBlock <M>> rangeBlocks = List.of();
    /**
     *
     */
    protected final List <ImageBlock <M>> domainBlocks = List.of();

    protected M inputImage;
    protected C outputImage;

    private final List <ImageTransform <M, C>> transforms = List.of();

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    @SuppressWarnings("unchecked")
    protected
    Encoder ( M inputImage, Size rangeSize, Size domainSize ) {
        this.inputImage = inputImage;
        outputImage = (C) new CompressedImage(inputImage);
        outputImage.originalImageWidth = inputImage.getWidth();
        outputImage.originalImageHeight = inputImage.getHeight();

        blockGenerator = createBlockGenerator(rangeSize, domainSize);
//        rangeBlocks = blockGenerator.generateRangeBlocks(inputImage);
//        domainBlocks = blockGenerator.generateDomainBlocks(inputImage);

//        outputImage.rangeBlocks = rangeBlocks;
//        outputImage.domainBlocks = domainBlocks;
    }

    public static
    <M extends Image, N extends TreeNode <N, A>, C extends CompressedImage, A extends IAddress <A>>
    IEncoder <N, M, C, A> create ( EPartitionScheme scheme, EncodeAction action ) {
        return create(scheme, action, new LoadSaveImageTask <>(""));
    }

    /**
     *
     */
    public
    void segmentImage () {
        blockGenerator.generateRangeBlocks(inputImage);
    }

    /**
     * @param rangeSize
     * @param domainSize
     * @return
     */
    protected
    ImageBlockGenerator createBlockGenerator ( Size rangeSize, Size domainSize ) {
        return new SquareImageBlockGenerator(rangeSize, domainSize);
    }

    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A>, M extends Image, C extends CompressedImage, A extends IAddress <A>>
    IEncoder <N, M, C, A> create ( EPartitionScheme scheme, EncodeAction action, LoadSaveImageTask <M> lit ) {
        try {
            Class <IEncoder <N, M, C, A>> encoderClass = (Class <IEncoder <N, M, C, A>>) Class.forName(scheme.getEncoderClassName());
            Constructor <IEncoder <N, M, C, A>> ctor = encoderClass.getDeclaredConstructor(
                    Image.class,
                    Size.class,
                    Size.class);

            return ctor.newInstance(lit.loadImage(action.getFn()), ZERO_SIZE, ZERO_SIZE);//fixme
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            System.exit(0);
        }

        throw new IllegalStateException();
    }

    /**
     * @param imageFn
     * @return
     */
    M loadImage ( String imageFn ) {
        List <Task <M>> tasks = new ArrayList <>();
        LoadSaveImageTask <M> lit = new LoadSaveImageTask <>(imageFn, tasks);

        return lit.loadImage(imageFn);
    }

    /**
     * @return
     */
    public
    M encode ( M image ) {
        segmentImage();
        createDomainPool();
        createCodeBook();

//        for (ImageBlock rangeBlock : rangeBlocks) {
//            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
//            System.err.printf("%d%%", percent);
//            ImageTransform <M, C> bestTransform = ImageTransform.create(image);
//            int minDistance = Integer.MAX_VALUE;
//
//            for (ImageBlock domainBlock : domainBlocks) {
//                double alpha = 0;
//                for (int i = 0; i < domainBlock.width; i++) {
//                    for (int j = 0; j < domainBlock.height; j++) {
//                        int[] data = new int[0];//fixme
//                        alpha += (domainBlock.get(i, j, data) - domainBlock.meanPixelValue);
////                        (rangeBlock.get(i,j,data) - rangeBlock.meanPixelValue);
//                    }
//                }
//
//                double contrast = alpha / domainBlock.beta;
//                int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);
//
//                for (int index = 0; index < 8; index++) {
//                    ImageBlock transformedDomainBlock = new ImageBlock(
//                            domainBlock.x,
//                            domainBlock.y,
//                            domainBlock.width,
//                            domainBlock.height);
//
//                    for (int x = 0; x < domainBlock.width; x++) {
//                        for (int y = 0; y < domainBlock.height; y++) {
//                            int newX = x * dihedralAffineTransforms[index][0] + y * dihedralAffineTransforms[index][1];
//                            int newY = x * dihedralAffineTransforms[index][2] + y * dihedralAffineTransforms[index][3];
//                            if (newX < 0) {
//                                newX += domainBlock.width;
//                            }
//                            if (newY < 0) {
//                                newY += domainBlock.height;
//                            }
//                            short newPixelValue = (short) (contrast * domainBlock.put(x, y) + brightness);
//                            transformedDomainBlock.put(newX, newY, ((newPixelValue < MAX_PIXEL_VALUE) &&
//                                    (newPixelValue >= 0))
//                                    ? newPixelValue :
//                                    (MAX_PIXEL_VALUE / 2.0));
//                        }
//                    }
//
//                    int distance = getDistance(rangeBlock, transformedDomainBlock);
//                    if ((distance < minDistance) && (distance != 0)) {
//                        minDistance = distance;
//                        bestTransform.dihedralAffineTransformerIndex = index;
//                        bestTransform.originalDomainX = domainBlock.x;
//                        bestTransform.originalDomainY = domainBlock.y;
////                        bestTransform.brightnessOffset = brightness;
////                        bestTransform.contrastScale = contrast;
//                    }
//                }
//            }
//
//            getTransforms().add(bestTransform);
//        }
//
////        outputImage.transforms = transforms;
//
        return (M) outputImage;
    }

    private
    void createDomainPool () {

    }

    private
    void createCodeBook () {

    }

    /**
     * @return
     */
    private
    List <ImageTransform <M, C>> getTransforms () {
        return transforms;
    }

    /**
     * @param x
     * @param axis
     * @return
     */
    public
    M flipAxis ( M x, int axis ) {
//        x = np.asarray(x).swapaxes (axis, 0);
//        x = x[::-1, ...]
//        x = x.swapaxes(0, axis);
        return x;
    }

//    /**
//     * @param image
//     * @param range
//     * @param size
//     * @return
//     */
//    @Override
//    public
//    M applyTransform ( M image, Rect range, Size size ) {
//        return null;
//    }

//    /**
//     * @param matrix
//     * @param x
//     * @param y
//     * @return
//     */
//    @Override
//    public
//    Mat transformMatrixOffsetCenter ( Mat matrix, int x, int y ) {
//
//        return matrix;//fixme
//    }

//    /**

//     * Performs a brightness shift.
//     *
//     * @param x
//     * @param brightness
//     * @return Numpy image tensor.
//     * @throws ValueError if `brightness_range` isn't a tuple.
//     */
//    @Override
//    public
//    Image applyBrightnessShift ( Image x, Mat.Tuple2 <Float> brightness ) {
//        return null;
//    }

//    /**
//     * Performs a random brightness shift.
//     *
//     * @param x
//     * @param brightnessRange
//     * @return
//     */
//    @Override
//    public
//    M randomBrightness ( M x, Range brightnessRange ) {
//        return null;
//    }

    /**
     * @param range
     * @param domain
     * @return
     */
    public
    int getDistance ( ImageBlock <M> range, ImageBlock <M> domain ) {
        double error = 0;
        for (int i = 0; i < range.width; i++) {
            for (int j = 0; j < range.height; j++) {
                error += Math.pow(range.get(i, j, EMPTY_ARRAY) - domain.get(i, j, EMPTY_ARRAY), 2);///fixme
            }
        }
        error = error / (double) (range.width * range.height);

        return (int) error;
    }


    /*
    public class SimpleEncoder implements Constants{

        List<ImageBlock> rangeBlocks;
        List<ImageBlock> domainBlocks;

        List<ImageBlockTransform> transforms = null;
    //	GrayscaleImage inputImage;
    //	CompressedImage outputImage;
        GrayscaleImage inputImage;
    //	CompressedImage outputImage;

        public SimpleEncoder(GrayscaleImage inputImage, Point rangeSize, Point domainSize) {
            this.inputImage = inputImage;
            outputImage = new CompressedImage();
            outputImage.originalImageWidth = inputImage.getWidth();
            outputImage.originalImageHeight = inputImage.getHeight();

            SimpleBlockGenerator blockGenerator = new SimpleBlockGenerator(rangeSize, domainSize);
            rangeBlocks = blockGenerator.generateRangeBlocks(inputImage);
            domainBlocks = blockGenerator.generateDomainBlocks(inputImage);
            transforms = new ArrayList<ImageBlockTransform>();

            outputImage.rangeBlocks = rangeBlocks;
            outputImage.domainBlocks = domainBlocks;
        }

        public CompressedImage encode() {

            for (ImageBlock rangeBlock : rangeBlocks) {
                int percent = 100 * (rangeBlocks.indexOf(rangeBlock)+1) / rangeBlocks.size();
                System.err.println(percent + "%");
                ImageBlockTransform bestTransform = new ImageBlockTransform();
                int minDistance = Integer.MAX_VALUE;

                for (ImageBlock domainBlock : domainBlocks) {

                    double alpha = 0;
                    for(int i = 0; i<domainBlock.width ; i++){
                        for(int j = 0 ; j<domainBlock.height ; j++){
                            alpha += (domainBlock.pixelValues[i][j] - domainBlock.meanPixelValue)
                                        * (rangeBlock.pixelValues[i][j] - rangeBlock.meanPixelValue);
                        }
                    }

                    double contrast = alpha / domainBlock.beta;
                    int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);

                    for(int indx = 0; indx<8;indx++){
                        ImageBlock transformedDomainBlock = new ImageBlock(	domainBlock.x,
                                                                            domainBlock.y,
                                                                        domainBlock.width,
                                                                        domainBlock.height);

                        for(int x = 0; x<domainBlock.width ; x++){
                            for(int y = 0 ; y<domainBlock.height ; y++){
                                int newX = x * dihedralAffineTransforms[indx][0] + y * dihedralAffineTransforms[indx][1];
                                int newY = x * dihedralAffineTransforms[indx][2] + y * dihedralAffineTransforms[indx][3];
                                if(newX<0) newX +=domainBlock.width;
                                if(newY<0) newY +=domainBlock.height;
                                short newPixelValue = (short)(contrast * domainBlock.pixelValues[x][y] + brightness);
                                if(newPixelValue<MAX_PIXEL_VALUE
                                        && newPixelValue>=0)
                                    transformedDomainBlock.pixelValues[newX][newY] = newPixelValue;
                                else
                                    transformedDomainBlock.pixelValues[newX][newY] = MAX_PIXEL_VALUE / 2;

                            }
                        }

                        int distance = getDistance(rangeBlock, transformedDomainBlock);

                        if( (distance < minDistance)
                                && (distance!=0)){
                            minDistance = distance;
                            bestTransform.dihedralAffineTransformerIndex = indx;
                            bestTransform.originalDomainX = domainBlock.x;
                            bestTransform.originalDomainY = domainBlock.y;
                            bestTransform.brightnessOffset = brightness;
                            bestTransform.contrastScale = contrast;
                        }
                    }
                }
                transforms.add(bestTransform);
            }
            outputImage.transforms = transforms;
            return outputImage;
        }

    /**
     *
     */
    public
    Encoder ( ImageBlockGenerator blockGenerator ) {
        this.blockGenerator = blockGenerator;
    }
}
