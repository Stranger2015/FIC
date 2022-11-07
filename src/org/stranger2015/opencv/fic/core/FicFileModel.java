package org.stranger2015.opencv.fic.core;

import ar.com.hjg.pngj.ImageInfo;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * a fractal model represents the compressed form of the image.<br />
 * <br />
 * {@code from <Range, <Domain, Transform>>      }<br />
 * {@code ..to <Domain, <Transform, {Range}>>  }<br />
 * <br />
 * or from:
 * <br />
 * {@code Point1 - Domain1 - Transform1}<br />
 * {@code Point2 - Domain2 - Transform1}<br />
 * {@code Point3 - Domain1 - Transform2}<br />
 * {@code Point4 - Domain1 - Transform2}<br />
 * {@code Point5 - Domain2 - Transform1}<br />
 * <br />
 * to:
 * <br />
 * {@code Domain1 -[ Transform1 -[ Point1 ]] }<br />
 * {@code ....... .[ Transform2 -[ Point3 ]  }<br />
 * {@code ....... ............. .[ Point4 ]] }<br />
 * {@code Domain2 -[ Transform1 -[ Point2 ]  }<br />
 * {@code ....... ............. .[ Point5 ]] }<br />
 * <br />
 * in other words, instead of storing for each range the transform <br />
 * and the domain, we store the domain once, along with a set of   <br />
 * transforms, with each transform, we store a set of points that  <br />
 * represent the position of the ranges in the original image.
 *
 * @see Compressor
 */
public
class FicFileModel<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>{
    private final Map <IImageBlock<A>, Map <ImageTransform <A, G>, Set <Point>>> model;
    private ImageInfo imageInfo;

    private final MatOfInt mat = new MatOfInt();

    private int originalImageWidth;
    private int originalImageHeight;

    /**
     * @param simpleModel
     */
    public
    FicFileModel ( Map <Point, Entry <IImageBlock<A>, ImageTransform <A, G>>> simpleModel ) {
        model = new HashMap <>();
        analyze(simpleModel);
    }

    /**
     * for each point, extract the appropriate domain and transform.
     * if the domain is new to the model, add it and create a map for
     * the transform and the range points.
     * finally, add the point.
     */
    @SuppressWarnings({"unchecked"})
    private
    void analyze ( Map <Point, Entry <IImageBlock<A>, ImageTransform <A, G>>> simpleModel ) {
        for (Point point : simpleModel.keySet()) {
            IImageBlock<A> domain = new ImageBlock <>((Mat) simpleModel.get(point).getKey(), geometry);
            ImageTransform <A, G> transform = simpleModel.get(point).getValue();
            if (!model.containsKey(domain)) {
                model.put(domain, new HashMap <>());
                model.get(domain).put(transform, new HashSet <>());
            }
            else if (!model.get(domain).containsKey(transform)) {
                /*
                 * if the domain is not new, but the transform is new,
                 * add the transform and create a map for the range points
                 */
                model.get(domain).put(transform, new HashSet <>());
            }
            model.get(domain).get(transform).add(point);
        }
    }

    /**
     * @return
     */
    public
    Map <IImageBlock<A>, Map <ImageTransform<A, G>, Set <Point>>> getModel () {
        return model;
    }

    /**
     * @return
     */
    public
    ImageInfo getImageInfo () {
        return imageInfo;
    }

    /**
     * @param imageInfo
     */
    public
    void setImageInfo ( ImageInfo imageInfo ) {
        this.imageInfo = imageInfo;
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImage<A> toImage () {
        return  new GrayScaleImage <A>(mat);
    }

    /**
     * @return
     */
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    /**
     * @param originalImageWidth
     */
    public
    void setOriginalImageWidth ( int originalImageWidth ) {
        this.originalImageWidth = originalImageWidth;
    }

    /**
     * @return
     */
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @param originalImageHeight
     */
    public
    void setOriginalImageHeight ( int originalImageHeight ) {
        this.originalImageHeight = originalImageHeight;
    }
}