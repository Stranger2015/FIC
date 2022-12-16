package org.stranger2015.opencv.fic.core;

import org.checkerframework.checker.units.qual.A;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 *
 */
public
interface IProcessor<N extends TreeNode <N>> {

    /**
     * @param filename
     * @return
     */
    IImage preprocess ( String filename );

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    IImage preprocess ( String filename, IImage inputImage ) throws ValueError;

    /**
     * @return
     */
    IImage process ( IImage inputImage ) throws Exception;

    /**
     * @param outputImage
     * @return
     */
    default
    FCImageModel <N,A,G> postprocess ( CompressedImage  outputImage ) {
        IIntSize size = outputImage.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        size = outputImage.restoreSize(h, size.getOriginalImageWidth(), size.getOriginalImageHeight());
        Mat dest = new Mat();//CompressedImage(outputImage);

        Imgproc.resize(outputImage.getMat(), dest, size.toSize());

        return new CompressedImage ((IImage ) dest);
    }

    /**
     * @param image
     * @return
     */
//    @SuppressWarnings("unchecked")
    default
    IImage preprocess ( IImage image ) throws ValueError {
        IIntSize size = image.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        int radix = image.getAddress(0,0).radix();
        size = adjustSize(radix, w, h);
//        List <IImageBlock > regions = image.getRegions();
//        List <IImageBlock > newRegions = new ArrayList <>(regions.size());
//        for (IImage  region : regions) {
//            region = preprocess(region);
//            newRegions.add((IImageBlock ) region);
//        }
        image = new Image <>((MatOfInt) image.getMat(), size);

        return image;
    }

    /**
     * @param base
     * @param w
     * @param h
     * @return
     */
    IntSize adjustSize ( int base, int w, int h );
}
