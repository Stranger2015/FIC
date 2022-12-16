package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 
 */
public
interface IImageProcessor
        extends IProcessor  {

    IImage  postprocess ( IImage outputImage );

    /**
     * @return
     */
    IImage getImage ();

    /**
     * @return
     */
    EPartitionScheme getScheme ();

    /**
     * @return
     */
    ICodec  getCodec ();

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    @Override
    default
    IImage preprocess ( String filename, IImage inputImage ) throws ValueError {
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
    IImage preprocess ( IImage inputImage ) throws ValueError {
        IntSize size = adjustSize(
                inputImage.getAddress(0,0).radix(),
                inputImage.getWidth(),
                inputImage.getHeight()
        );
        //image or block or roi
        IImage destImage = new Image <>(inputImage.getMat());
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
    IImage preprocess ( String filename );
}
