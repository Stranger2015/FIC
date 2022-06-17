package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 
 */
public
interface IImageProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>

        extends IProcessor <N, A, G> {

    /**
     * @return
     */
    IImage<A> getImage ();

    /**
     * @return
     */
    EPartitionScheme getScheme ();

    /**
     * @return
     */
    ICodec <N, A, G> getCodec ();

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    @Override
    default
    IImage<A> preprocess ( String filename, IImage<A> inputImage ) throws ValueError {
        if (filename != null && inputImage == null) {
            inputImage = preprocess(filename);
        }

        return preprocess(inputImage);
    }

    /**
     * @param inputImage
     * @return
     */
    default
    IImage<A> preprocess ( IImage<A> inputImage ) throws ValueError {
        IntSize size = adjustSize(
                inputImage.getAddress().radix(),
                inputImage.getWidth(),
                inputImage.getHeight()
        );
        //image or block or roi
        IImage<A> destImage = new GrayScaleImage <A>(inputImage.getMat());
        Imgproc.resize(
                inputImage.getMat(),
                destImage.getMat(),
                new Size(size.getWidth(), size.getHeight()),
                1.0 * size.getWidth() / size.originalImageWidth,
                1.0 * size.getHeight() / size.originalImageHeight
        );

        return destImage;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    IImage<A> preprocess ( String filename );

}
