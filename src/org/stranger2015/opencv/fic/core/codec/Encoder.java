package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public abstract
class Encoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        implements IEncoder <N, A, M>, IConstants, IImageProcessorListener {

    public static final Size ZERO_SIZE = new Size(0, 0);

    protected final ImageBlockGenerator blockGenerator;

    protected final List <IEncoderListener> listeners = new ArrayList <>();

    protected M inputImage;
    protected M outputImage;

    protected final List <ImageBlock> rangeBlocks = new ArrayList <>();
    protected final List <ImageBlock> domainBlocks = new ArrayList <>();
    protected final List <ImageBlock> codebookBlocks = new ArrayList <>();

    protected final List <ImageTransform <M>> transforms = new ArrayList <>();

    /**
     * @return
     */
    public
    List <ImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    public
    List <ImageBlock> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
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

        outputImage.originalImageWidth = inputImage.getWidth();
        outputImage.originalImageHeight = inputImage.getHeight();

        blockGenerator = createBlockGenerator(rangeSize, domainSize);
    }

    /**
     * @return
     */
    public
    void segmentImage ( M inputImage ) {
        blockGenerator.generateRangeBlocks(inputImage);
        blockGenerator.generateDomainBlocks(inputImage);
        blockGenerator.createCodebookBlocks(inputImage);
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

        return findBestTransform(image);
    }

    /**
     *
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    protected
    M findBestTransform ( M image ) {
        for (ImageBlock rangeBlock : rangeBlocks) {
            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
            System.err.printf("%d%%", percent);
            ImageTransform <M> bestTransform = ImageTransform.create(image);
            int minDistance = Integer.MAX_VALUE;
            iterateDomainBlocks(rangeBlock, bestTransform, minDistance);

            getTransforms().add(bestTransform);
        }
        ((ICompressedImage) outputImage).getTransforms()
                .addAll((Collection <? extends ImageTransform <Image>>) transforms);

        return outputImage;
    }

    protected
    void iterateDomainBlocks ( ImageBlock rangeBlock, ImageTransform <M> bestTransform, int minDistance ) {
        for (ImageBlock domainBlock : domainBlocks) {
            double alpha = 0;
            for (int i = 0; i < domainBlock.width; i++) {
                for (int j = 0; j < domainBlock.height; j++) {
                    int[] data = new int[0];//fixme
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
//                error += Math.pow(range.get(i, j), EMPTY_ARRAY) - domain.get(i, j, EMPTY_ARRAY), 2);///fixme
            }
        }
        error = error / (double) (range.getWidth() * range.getWidth());

        return (int) error;
    }

    /**
     *
     */
    @Contract(pure = true)
    protected
    void createCodebookBlocks ( M image ) {
//domainBlocks
    }

    /**
     * @return
     */
    private
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
    public
    Encoder ( ImageBlockGenerator blockGenerator ) {
        this.blockGenerator = blockGenerator;
    }
}
