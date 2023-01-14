package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.classifiers.ImageBlockClassifier;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.transform.EInterpolationType;
import org.stranger2015.opencv.fic.transform.ImageTransform;

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

    /**
     * @param scheme
     * @param paramTypes
     * @param params
     * @return
     * @throws ReflectiveOperationException
     */
    public static
    IDecoder create ( EPartitionScheme scheme, ImageBlockClassifier[] paramTypes, Object... params )
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
    IImage decode ( String fileName ) throws Exception {
        FractalReader reader = new FractalReader(fileName);
        FCImageModel model = reader.readModel();
        for (int i = 0; i < NUMBER_OF_DECODER_ITERATIONS; i++) {
            for (ImageTransform transform : compressedImage.getTransforms()) {
//                int[] transformation = dihedralAffineTransforms[transform.dihedralAffineTransformIndex];

//                dihedralAffineTransformMatrices =
                originalImage = transform.transform(
                        originalImage,
                        dihedralAffineTransformMatrices[0],
                        EInterpolationType.BILINEAR
                        );
            };
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
        FractalWriter writer = new FractalWriter(filename, fractalModel);
        writer.writeModel();
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