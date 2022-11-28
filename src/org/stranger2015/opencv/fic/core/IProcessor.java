package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface IProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @param filename
     * @return
     */
    IImage <A> preprocess ( String filename );

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    IImage <A> preprocess ( String filename, IImage <A> inputImage ) throws ValueError;

    /**
     * @return
     */
    IImage <A> process ( IImage <A> inputImage ) throws Exception;

    /**
     * @param outputImage
     * @return
     */
    default
    FCImageModel <N,A,G> postprocess ( CompressedImage <A> outputImage ) {
        IIntSize size = outputImage.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        size = outputImage.restoreSize(h, size.getOriginalImageWidth(), size.getOriginalImageHeight());
        Mat dest = new Mat();//CompressedImage<A>(outputImage);

        Imgproc.resize(outputImage.getMat(), dest, size.toSize());

        return new CompressedImage <A>((IImage <A>) dest);
    }

    /**
     * @param image
     * @return
     */
//    @SuppressWarnings("unchecked")
    default
    IImage <A> preprocess ( IImage <A> image ) throws ValueError {
        IIntSize size = image.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        int radix = image.getAddress(0,0).radix();
        size = adjustSize(radix, w, h);
//        List <IImageBlock <A>> regions = image.getRegions();
//        List <IImageBlock <A>> newRegions = new ArrayList <>(regions.size());
//        for (IImage <A> region : regions) {
//            region = preprocess(region);
//            newRegions.add((IImageBlock <A>) region);
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
