package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.Compressor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

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
class FractalModel<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        implements Serializable {

    private final Map <M, Map <ImageTransform <M, A, G>, Set <Point>>> fModel;

    /**
     * @param simpleModel
     */
    public
    FractalModel ( Map <Point, Entry <M, ImageTransform <M, A, G>>> simpleModel) {
        fModel = new HashMap <>();

        analyze(simpleModel);
    }

    /**
     * for each point, extract the appropriate domain and transform.
     * if the domain is new to the model, add it and create a map for
     * the transform and the range points.
     * finally, add the point.
     */
    private
    void analyze ( Map <Point, Entry <M, ImageTransform <M, A, G>>> simpleModel ) {
        for (Point point : simpleModel.keySet()) {
            M domain = (M) new Image <>(simpleModel.get(point).getKey());
            ImageTransform <M, A, G> transform = simpleModel.get(point).getValue();
            if (!fModel.containsKey(domain)) {
                fModel.put(domain, new HashMap <>());
                fModel.get(domain).put(transform, new HashSet <>());
            }
            else if (!fModel.get(domain).containsKey(transform)) {
                /*
                 * if the domain is not new, but the transform is new,
                 * add the transform and create a map for the range points
                 */
                fModel.get(domain).put(transform, new HashSet <>());
            }
            fModel.get(domain).get(transform).add(point);
        }
    }

    /**
     * @return
     */
    public
    Map <M, Map <ImageTransform <M, A, G>, Set <Point>>> getModel () {
        return fModel;
    }
}
