package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.io.IOException;

/**
 *
 */
public abstract
class Decoder implements IDecoder, IConstants {
    private ICompressedImage compressedImage;
    private IImage originalImage;

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec codec ) {
        IDecoder.super.onCodecCreated(codec);
    }

    /**
     *
     */
    protected
    Decoder ( IImage image ) {
        this.originalImage = image;
    }

    public static
    IDecoder create ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params )
            throws ReflectiveOperationException {

        return (IDecoder) Utils.create(
                scheme.getDecoderClassName(),
                null,
                paramTypes,
                params);
    }

    /**
     *
     */
    public
    IImage decode ( String fileName ) throws ValueError, IOException {
        FractalReader reader=new FractalReader(fileName);
        FCImageModel model = reader.readModel();
        for (int i = 0; i < NUMBER_OF_DECODER_ITERATIONS; i++) {
            for (ImageTransform transform : compressedImage.getTransforms()) {
int[] transformation= dihedralAffineTransforms[ transform.dihedralAffineTransformIndex];

Mat transformationMatrix = dihedralAffineTransforms[ transform.dihedralAffineTransformIndex];
                originalImage = transform.transform(
                        originalImage,
//                        ;//applyTransformOn(originalImage, range, domainSize)
            }
        }

        return originalImage;
    }

    /**
     * @param filename
     * @param fractalModel
     */
    @Override
    public
    void saveModel ( String filename, FCImageModel fractalModel ) throws Exception {
        try (FractalWriter writer = new FractalWriter(filename, fractalModel)) {
            writer.writeModel(O,fractalModel);
        }
    }

    /**
     *
     */
//    @Override
    public
    void onCreateCodec () {

    }

    /**
     * @return
     */
    public
    IImage getImage () {
        return originalImage;
    }

    /**
     * @param compressedImage
     */
    public
    void setCompressedImage ( ICompressedImage compressedImage ) {
        this.compressedImage = compressedImage;
    }
}