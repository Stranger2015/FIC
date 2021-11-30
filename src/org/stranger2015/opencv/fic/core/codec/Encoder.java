package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.stranger2015.opencv.fic.core.ImageBlock.EMPTY_ARRAY;
//=====================================================================================

public abstract
class Encoder<M extends Image, C extends CompressedImage> implements IEncoder <M, C>, IConstants {

    protected List <ImageBlock> rangeBlocks;
    protected List <ImageBlock> domainBlocks;

    protected M inputImage;
    protected C outputImage;

    private final List <ImageTransform <M, C>> transforms = new ArrayList <>();

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

        ImageBlockGenerator blockGenerator = new ImageBlockGenerator(rangeSize, domainSize);
        rangeBlocks = blockGenerator.generateRangeBlocks(inputImage);
        domainBlocks = blockGenerator.generateDomainBlocks(inputImage);
//        transforms = new ArrayList <>();

        outputImage.rangeBlocks = rangeBlocks;
        outputImage.domainBlocks = domainBlocks;
    }

    @SuppressWarnings("unchecked")
    public static
    <M extends Image, C extends CompressedImage>
    IEncoder <M, C> create ( EPartitionScheme scheme, EncodeAction action ) {
        try {
            Class <IEncoder <M, C>> encoderClass = (Class <IEncoder <M, C>>) Class.forName(scheme.getEncoderClassName());
            Constructor <IEncoder <M, C>> ctor = encoderClass.getDeclaredConstructor(
                    Image.class,
                    Size.class,
                    Size.class);

            return ctor.newInstance(loadImage(action.getFn()), 0, 0);//fixme
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            System.exit(0);
        }

        throw new IllegalStateException();
    }

    private static
    <M extends Image, C extends CompressedImage>
    M loadImage ( String imageFn ) {
        return null;
    }

    /**
     * @return
     */
    public
    M encode ( M image ) {
        Random rand = new Random();
        double d1 = 0;
        double d2 = 0;
//       DoubleStream ds = rand.doubles(d1, d2);
//        DoubleSupplier supplier= () -> 0;
//        ds.generate();
        for (ImageBlock rangeBlock : rangeBlocks) {
            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
            System.err.printf("%d%%", percent);
            ImageTransform <M, C> bestTransform = ImageTransform.create(image);
            int minDistance = Integer.MAX_VALUE;

            for (ImageBlock domainBlock : domainBlocks) {
                double alpha = 0;
                for (int i = 0; i < domainBlock.width; i++) {
                    for (int j = 0; j < domainBlock.height; j++) {
                        int[] data = new int[0];//fixme
                        alpha += (domainBlock.get(i, j, data) - domainBlock.meanPixelValue);
//                        (rangeBlock.get(i,j,data) - rangeBlock.meanPixelValue);
                    }
                }

                double contrast = alpha / domainBlock.beta;
                int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);

                for (int index = 0; index < 8; index++) {
                    ImageBlock transformedDomainBlock = new ImageBlock(
                            domainBlock.x,
                            domainBlock.y,
                            domainBlock.width,
                            domainBlock.height);

                    for (int x = 0; x < domainBlock.width; x++) {
                        for (int y = 0; y < domainBlock.height; y++) {
                            int newX = x * dihedralAffineTransforms[index][0] + y * dihedralAffineTransforms[index][1];
                            int newY = x * dihedralAffineTransforms[index][2] + y * dihedralAffineTransforms[index][3];
                            if (newX < 0) {
                                newX += domainBlock.width;
                            }
                            if (newY < 0) {
                                newY += domainBlock.height;
                            }
                            short newPixelValue = (short) (contrast * domainBlock.put(x, y) + brightness);
                            transformedDomainBlock.put(newX, newY, ((newPixelValue < MAX_PIXEL_VALUE) &&
                                    (newPixelValue >= 0))
                                    ? newPixelValue :
                                    (MAX_PIXEL_VALUE / 2.0));
                        }
                    }

                    int distance = getDistance(rangeBlock, transformedDomainBlock);
                    if ((distance < minDistance) && (distance != 0)) {
                        minDistance = distance;
                        bestTransform.dihedralAffineTransformerIndex = index;
                        bestTransform.originalDomainX = domainBlock.x;
                        bestTransform.originalDomainY = domainBlock.y;
//                        bestTransform.brightnessOffset = brightness;
//                        bestTransform.contrastScale = contrast;
                    }
                }
            }

            getTransforms().add(bestTransform);
        }

//        outputImage.transforms = transforms;

        return (M) outputImage;
    }

    private
    List <ImageTransform <M, C>> getTransforms () {
        return transforms;
    }

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

    public
    int getDistance ( ImageBlock range, ImageBlock domain ) {
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

        public int getDistance(ImageBlock range, ImageBlock domain){
            double error = 0;
            for(int i=0;i<range.width;i++){
                for (int j=0;j<range.height;j++){
                    error += Math.pow(range.pixelValues[i][j]-domain.pixelValues[i][j],2);
                }
            }
            error = error / (double)(range.width * range.height);
            return (int)error;
        }
    }
        /**
         *
         */
    public
    Encoder () {
    }
}
