package org.stranger2015.opencv.fic.core.search;

//import org.jetbrains.annotations.Contract;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.IntSize;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import static java.lang.Math.ceil;
import static org.stranger2015.opencv.fic.core.codec.IConstants.MAX_PIXEL_CHANNEL_VALUE;
import static org.stranger2015.opencv.fic.core.codec.IConstants.dihedralAffineTransforms;

/**
 * @param
 * @param <G>
 */
public
class ExhaustiveSearchProcessor extends SearchProcessor {
    public static final int ifsRecordLength = 31;
    private final IEncoder encoder;
    BitBuffer bitBuffer;

    /**
     * @param encoder
     */
    public
    ExhaustiveSearchProcessor ( IEncoder encoder ) {
        this.encoder = encoder;
    }

    /**
     * @param domainBlock
     */
    private
    void accept ( IImageBlock domainBlock ) {

    }

    /**
     *
     */
    @Override
    public
    ITransform searchForBestTransform () {
        return getBestTransform();
    }

    /**
     * @return
     */
    @Override
    public
    double evaluate () {
        return 0;
    }

    /**
     * @param imageBlock
     * @param rangeBlocks
     * @return
     */
    @Override
    public
    byte[] search ( IImageBlock imageBlock, Pool <IImageBlock> rangeBlocks ) throws ValueError {
        bitBuffer = BitBuffer.allocate((int) ceil(rangeBlocks.size() * ifsRecordLength / 8.0));
        int channels = imageBlock.getChannelsAmount();

//        IImageBlock bestImageBlock = encoder.selectBest();
        for (IImageBlock rangeBlock : rangeBlocks) {
            ImageTransform bestTransform = ImageTransform.create(imageBlock, rangeBlock.getAddress(0, 0));
            for (IImageBlock domainBlock : encoder.getDomainBlocks()) {
                double[] alpha = new double[channels];
                int ch = 0;
                for (; ch < channels; ch++) {
                    alpha[ch] = 0;
                    for (int i = 0; i < domainBlock.getWidth(); i++) {
                        for (int j = 0; j < domainBlock.getHeight(); j++) {
                            alpha[ch] += (domainBlock.pixelValues(i, j)[ch] - domainBlock.getMeanPixelValue()[ch])
                                    * (rangeBlock.pixelValues(i, j)[ch] - rangeBlock.getMeanPixelValue()[ch]);
                        }
                    }
                    double contrast = alpha[ch] / domainBlock.getBeta();
                    int brightness = (int) (rangeBlock.getMeanPixelValue()[ch] -
                            contrast * domainBlock.getMeanPixelValue()[ch]
                    );
                    bestTransform = selectBestTransform(rangeBlock, domainBlock, brightness, contrast, bestTransform, ch);
                }
            }

            writeIfsCode(bitBuffer, bestTransform);
        }

        return bitBuffer.getBytes().array();
    }

    /**
     * @param rangeBlock
     * @param domainBlock
     * @param brightness
     * @param contrast
     * @param bestTransform
     * @param ch1
     * @throws ValueError
     */
    protected
    ImageTransform selectBestTransform (
            IImageBlock rangeBlock,
            IImageBlock domainBlock,
            int brightness,
            double contrast,
            ImageTransform bestTransform,
            int ch1
    ) throws ValueError {

        int channels = domainBlock.getChannelsAmount();
        double[] newPixelValue = new double[channels];
        double[] minDistance = new double[channels];
        for (int ch = 0; ch < channels; ch++) {
            minDistance[ch] = 0;
        }
        for (int index = 0; index < 8; index++) {
            IImageBlock transformedDomainBlock = domainBlock.getSubImage(
                    domainBlock.getX(),
                    domainBlock.getY(),
                    domainBlock.getWidth(),
                    domainBlock.getHeight()
            );
            for (int x = 0; x < domainBlock.getWidth(); x++) {
                for (int y = 0; y < domainBlock.getHeight(); y++) {
                    int newX = x * dihedralAffineTransforms[index][0] +
                            y * dihedralAffineTransforms[index][1];

                    int newY = x * dihedralAffineTransforms[index][2] +
                            y * dihedralAffineTransforms[index][3];

                    if (newX < 0) {
                        newX += domainBlock.getWidth();
                    }
                    if (newY < 0) {
                        newY += domainBlock.getHeight();
                    }
                    newPixelValue[ch1] = (contrast * domainBlock.pixelValues(x, y)[ch1] + brightness);
                    if (newPixelValue[ch1] < MAX_PIXEL_CHANNEL_VALUE && newPixelValue[ch1] >= 0) {
                        transformedDomainBlock.pixelValues(newX, newY)[ch1] = newPixelValue[ch1];
                    }
                    else {
                        transformedDomainBlock.pixelValues(newX, newY)[ch1] = MAX_PIXEL_CHANNEL_VALUE / 2;
                    }
                }
            }
            double[] distance = getDistance(rangeBlock, transformedDomainBlock);

            if ((distance[ch1] < minDistance[ch1]) && (distance[ch1] != 0)) {
                minDistance[ch1] = distance[ch1];

                bestTransform.dihedralAffineTransformIndex = index;
                bestTransform.setOriginalDomainX(domainBlock.getX());
                bestTransform.setOriginalDomainY(domainBlock.getY());
                bestTransform.brightnessOffset = brightness;
                bestTransform.contrastScale = contrast;

                domainBlock.incUsageCount();
            }
        }

        return bestTransform;
    }

    /**
     * @param bitBuffer
     * @param transform
     */
    protected
    void writeIfsCode ( BitBuffer bitBuffer, ImageTransform transform ) {
        bitBuffer.writeIfsCodeRecord(transform);
    }

    /**
     * @param range
     * @param domain
     * @return
     */
    double[] getDistance ( IImageBlock rangeBlock, IImageBlock domainBlock ) {
        double[] distance = this.getMetrics().distance(
                rangeBlock.pixelValues(rangeBlock.getX(), rangeBlock.getY()),
                domainBlock.pixelValues(domainBlock.getX(), domainBlock.getY())//,
//fuzz
        );
/*
  distance += metric.distance(img1pixels[pixelrow * width + pixelcol],
                                            img2pixels[pixelrow * width + pixelcol],
                                            fuzz);
 */
        int channels = rangeBlock.getChannelsAmount();
        double[] error = new double[channels];
//        double[] dist = new double[channels];
        for (int ch = 0; ch < channels; ch++) {
            error[ch] = 0;
            for (int i = 0; i < rangeBlock.getWidth(); i++) {
                for (int j = 0; j < rangeBlock.getHeight(); j++) {
                    error[ch] += Math.pow(rangeBlock.pixelValues(i, j)[ch] - domainBlock.pixelValues(i, j)[ch], 2);
                }
            }
            error[ch] /= rangeBlock.getWidth() * rangeBlock.getHeight();
//            dist[ch] = (int) error[ch];
        }

        return error;// dist;
    }

    /**
     * @param filename
     * @return
     */
//    @Override
    public
    IImage preprocess ( String filename ) {
        return null;
    }

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    public
    IImage preprocess ( String filename, IImage inputImage ) throws ValueError {
        return null;
    }

    /**
     * @param inputImage
     * @return
     */
//    @Override
    public
    IImage process ( IImage inputImage ) throws Exception {
        return inputImage;
    }

    /**
     * @param base
     * @param w
     * @param h
     * @return
     */
//    @Override
    public
    IntSize adjustSize ( int base, int w, int h ) {
        return null;
    }

    //    @Override
    public
    IEncoder getEncoder () {
        return encoder;
    }
}
