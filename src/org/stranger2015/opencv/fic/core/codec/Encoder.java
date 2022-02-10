package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public //abstract
class Encoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
        implements IEncoder <N, A, M>, IConstants, IImageProcessorListener {

    public static final Size ZERO_SIZE = new Size(0, 0);

    protected final ImageBlockGenerator blockGenerator;
    protected final List <IEncoderListener> listeners = new ArrayList <>();

    protected M inputImage;
    protected M outputImage;

    public final List <ImageBlock> rangeBlocks = new ArrayList <>();
    public final List <ImageBlock> domainBlocks = new ArrayList <>();
    public final List <ImageBlock> codebookBlocks = new ArrayList <>();

    protected final List <ImageTransform <M>> transforms = new ArrayList <>();

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageBlock> getCodebookBlocks () {
        return codebookBlocks;
    }

    /**
     * @return
     */
    public
    M getInputImage () {
        return inputImage;
    }

    /**
     * @return
     */
    public
    M getOutputImage () {
        return outputImage;
    }

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    @SuppressWarnings("unchecked")
    protected
    Encoder ( M inputImage, Size rangeSize, Size domainSize ) {
        this.inputImage = inputImage;
        outputImage = (M) new CompressedImage(inputImage);

        outputImage.setOriginalImageWidth(inputImage.getWidth());
        outputImage.setOriginalImageHeight(inputImage.getHeight());

        blockGenerator = createBlockGenerator(this, inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    public
    void segmentImage ( M inputImage ) {
        blockGenerator.generateRangeBlocks(inputImage);
        blockGenerator.generateDomainBlocks(inputImage);
        blockGenerator.createCodebookBlocks(inputImage, domainBlocks);
    }

    /**
     * @param encoder
     * @param rangeSize
     * @param domainSize
     * @return
     */
    protected //abstract
    ImageBlockGenerator createBlockGenerator ( IEncoder <N, A, M> encoder,
                                               IImage image,
                                               Size rangeSize,
                                               Size domainSize ) {
        return null;
    }

    /**
     * @param listener
     */
    @Override
    public
    void addListener ( IEncoderListener listener ) {
        listeners.add(listener);
    }

    /**
     * @param listener
     */
    @Override
    public
    void removeListener ( IEncoderListener listener ) {
        listeners.remove(listener);
    }

    /**
     * @return
     */
    public
    M encode ( M image ) {
        segmentImage(image);
        return iterateRangeBlocks(image);
    }

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    protected
    M iterateRangeBlocks ( M image ) {
        for (ImageBlock rangeBlock : rangeBlocks) {
            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
            System.err.printf("%d%%", percent);
            ImageTransform <M> bestTransform = ImageTransform.create(image);
            int minDistance = Integer.MAX_VALUE;

            iterateDomainBlocks(rangeBlock, bestTransform, minDistance);

            getTransforms().add(bestTransform);
        }
        ((ICompressedImage) outputImage).getTransforms()
                .addAll((Collection <? extends ImageTransform <IImage>>) transforms);

        return outputImage;
    }

    /**
     * @param rangeBlock
     * @param bestTransform
     * @param minDistance
     */
    protected
    void iterateDomainBlocks ( ImageBlock rangeBlock, ImageTransform <M> bestTransform, int minDistance ) {
        for (ImageBlock domainBlock : domainBlocks) {
            double alpha = 0;
            for (int i = 0; i < domainBlock.width; i++) {
                for (int j = 0; j < domainBlock.height; j++) {
                    int[] data = new int[4];//fixme
                    alpha += (domainBlock.get(i, j, data) - domainBlock.meanPixelValue);
                }
            }

            double contrast = alpha / domainBlock.beta;
            int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);

            for (int index = 0; index < 8; index++) {
                ImageBlock transformedDomainBlock = new ImageBlock(
                        inputImage,
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
                    bestTransform.brightnessOffset = brightness;
                    bestTransform.contrastScale = contrast;
                }
            }
        }
    }

    /**
     * @param range
     * @param domain
     * @return
     */
    public
    int getDistance ( ImageBlock range, ImageBlock domain ) {
        double error = 0;
        for (int i = 0; i < range.getWidth(); i++) {
            for (int j = 0; j < range.getHeight(); j++) {
                error += Math.pow(range.meanPixelValue - domain.meanPixelValue, 2);///fixme CHECK mean??
            }
        }
        error = error / (double) (range.getWidth() * range.getHeight());

        return (int) error;
    }

    /**
     *
     */
    @Contract(pure = true)
    protected
    void createCodebookBlocks ( M image, List <ImageBlock> domainBlocks ) {
//        for (int i = 0; i < domainBlocks.size(); i++) {
//            domainBlocks.get(i);
//        }
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <M>> getTransforms () {
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

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
        return null;
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
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;
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
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;
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

        public SimpleEncoder(GrayscaleImage inputImage, AddressedPoint rangeSize, AddressedPoint domainSize) {
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
            outputImage.transforms =  ;
            return outputImage;
        }

    /**
     *
     */
    protected
    Encoder ( ImageBlockGenerator blockGenerator ) {
        this.blockGenerator = blockGenerator;
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }
}
