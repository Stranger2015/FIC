package org.stranger2015.opencv.fic.core.search;

//import org.jetbrains.annotations.Contract;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

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
    byte[] search ( IImageBlock imageBlock, List <IImageBlock> rangeBlocks ) {
        bitBuffer = BitBuffer.allocate((int) ceil(rangeBlocks.size() * ifsRecordLength / 8.0));
        int channels = imageBlock.getChannelsAmount();
        int[] minDistance = new int[channels];

        rangeBlocks.forEach(rangeBlock -> {
            for (int c = 0; c < channels; c++) {
                minDistance[c] = 0;
            }
            ImageTransform bestTransform = null;
            try {
                bestTransform = ImageTransform.create(imageBlock, rangeBlock.getAddress(0, 0));
            } catch (ValueError e) {
                throw new RuntimeException(e);
            }
            ImageTransform finalBestTransform = bestTransform;
            encoder.getDomainBlocks().forEach(domainBlock -> {
                double[] alpha = new double[channels];
                double[] newPixelValue = new double[channels];
                int c = 0;
                for (; c < channels; c++) {
                    alpha[c] = 0;
                    for (int i = 0; i < domainBlock.getWidth(); i++) {
                        for (int j = 0; j < domainBlock.getHeight(); j++) {
                            alpha[c] += (domainBlock.pixelValues(i, j)[c] - domainBlock.getMeanPixelValue()[c])
                                    * (rangeBlock.pixelValues(i, j)[c] - rangeBlock.getMeanPixelValue()[c]);
                        }
                    }
                    double contrast = alpha[c] / domainBlock.getBeta();
                    int brightness = (int) (rangeBlock.getMeanPixelValue()[c] -
                            contrast * domainBlock.getMeanPixelValue()[c]
                    );

                    for (int indx = 0; indx < 8; indx++) {
                        IImageBlock transformedDomainBlock = null;
                        try {
                            transformedDomainBlock = new ImageBlock(
                                    domainBlock,
                                    domainBlock.getX(),
                                    domainBlock.getY(),
                                    domainBlock.getWidth(),
                                    domainBlock.getHeight()
                            );
                        } catch (ValueError e) {
                            throw new RuntimeException(e);
                        }

                        for (int x = 0; x < domainBlock.getWidth(); x++) {
                            for (int y = 0; y < domainBlock.getHeight(); y++) {
                                int newX = x * dihedralAffineTransforms[indx][0] +
                                        y * dihedralAffineTransforms[indx][1];

                                int newY = x * dihedralAffineTransforms[indx][2] +
                                        y * dihedralAffineTransforms[indx][3];

                                if (newX < 0) {
                                    newX += domainBlock.getWidth();
                                }
                                if (newY < 0) {
                                    newY += domainBlock.getHeight();
                                }
                                newPixelValue[c] = (contrast * domainBlock.pixelValues(x, y)[c] + brightness);
                                if (newPixelValue[c] < MAX_PIXEL_CHANNEL_VALUE && newPixelValue[c] >= 0) {
                                    transformedDomainBlock.pixelValues(newX, newY)[c] = newPixelValue[c];
                                }
                                else {
                                    transformedDomainBlock.pixelValues(newX, newY)[c] = MAX_PIXEL_CHANNEL_VALUE / 2;
                                }
                            }
                        }
                        int[] distance = getDistance(rangeBlock, transformedDomainBlock);

                        if ((distance[c] < minDistance[c]) && (distance[c] != 0)) {
                            minDistance[c] = distance[c];

                            finalBestTransform.dihedralAffineTransformIndex = indx;
                            finalBestTransform.setOriginalDomainX(domainBlock.getX());
                            finalBestTransform.setOriginalDomainY(domainBlock.getY());
                            finalBestTransform.brightnessOffset = brightness;
                            finalBestTransform.contrastScale = contrast;
                        }
                    }
                }
            });

            writeIfsCode(bitBuffer, finalBestTransform);
        });

        return bitBuffer.getBytes().array();
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
    int[] getDistance ( IImageBlock rangeBlock, IImageBlock domainBlock ) {
        int channels = rangeBlock.getChannelsAmount();
        double[] error = new double[channels];
        int[] dist = new int[channels];
        for (int c = 0; c < channels; c++) {
            error[c] = 0;
            for (int i = 0; i < rangeBlock.getWidth(); i++) {
                for (int j = 0; j < rangeBlock.getHeight(); j++) {
                    error[c] += Math.pow(rangeBlock.pixelValues(i, j)[c] - domainBlock.pixelValues(i, j)[c], 2);
                }
            }
            error[c] /= rangeBlock.getWidth() * rangeBlock.getHeight();
            dist[c] = (int) error[c];
        }

        return dist;
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
