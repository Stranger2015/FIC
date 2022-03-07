package org.stranger2015.opencv.fic.core.codec;

import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.FractalModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * restore the original image from the given fractal model
 */
public
class Decompressor<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends Observable {

    /**
     * @param observer the observer receiving progress results from the decompression - allowed to be null
     */
    public
    Decompressor ( Observer observer ) {
        this.addObserver(observer);
    }

    /**
     * reconstruct the original range image from the given fractal model
     *
     * @param fModel the fractal model describing the image
     * @return the original image
     * @see FractalModel
     */
    public
    M decompress ( FractalModel <N, A, M, G> fModel ) {
        Map <Point, M> simpleModel = new HashMap <>();

        int blockWidth = fModel.getModel().keySet().iterator().next().getWidth();
        int blockHeight = fModel.getModel().keySet().iterator().next().getHeight();

        int maxWidth = 0;
        int maxHeight = 0;

        for (M domain : fModel.getModel().keySet()) {
            for (ImageTransform <M, A, G> transform : fModel.getModel().get(domain).keySet()) {
                for (Point point : fModel.getModel().get(domain).get(transform)) {
                    simpleModel.put(point, domain);///fixme
                    if (maxWidth < point.getX()) {
                        maxWidth = point.getX();
                    }
                    if (maxHeight < point.getY()) {
                        maxHeight = point.getY();
                    }
                }
            }
        }

        int imgWidth = ++maxWidth * blockWidth;
        int imgHeight = ++maxHeight * blockHeight;

//        M image = Imgproc.warpAffine(null,
//                null,
//                null,
//                null,
//                -1);

        return null;// image;
    }
}
