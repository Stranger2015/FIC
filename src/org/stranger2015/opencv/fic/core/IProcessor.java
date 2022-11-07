package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
interface IProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    /**
     *
     */
//    void initialize ();

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
    IImage <A> process ( IImage <A> inputImage ) throws ValueError;

    /**
     * @param outputImage
     * @return
     */
    default
    CompressedImage <A> postprocess ( CompressedImage <A> outputImage ) {
        IIntSize size = outputImage.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        size = restoreSize(w, h, size.getOriginalImageWidth(), size.getOriginalImageHeight());
        Mat dest = new Mat();//CompressedImage<A>(outputImage);

        Imgproc.resize(outputImage.getMat(), dest, size.toSize());

        return new CompressedImage <A>(dest);
    }

    default
    IIntSize restoreSize ( int w, int h, int originalImageWidth, int originalImageHeight ) {
        return new IntSize(originalImageWidth, originalImageHeight, w, h);
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
        int radix = image.getAddress().radix();
        size = adjustSize(radix, w, h);
        List <RegionOfInterest <A>> regions = image.getRegions();
        List <RegionOfInterest <A>> newRegions = new ArrayList <>(regions.size());
        for (IImage <A> region : regions) {
            region = preprocess(region);
            newRegions.add((RegionOfInterest <A>) region);
        }

        image = new Image <>(actualImage, image.getMat(), size);
        image.setRegions(newRegions);

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
