package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ValueError;

/**
 * @param
 */
public abstract
class AffineTransform extends ImageTransform {

    private final EInterpolationType type;

    /**
     * @param image
     */
    protected
    AffineTransform ( IImage image, EInterpolationType type , IAddress address) {
        super(image, type, address);

        this.type = type;
    }

    /**
     * @param image
     * @param type
     * @param address
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformerIndex
     * @throws ValueError
     */
    public
    AffineTransform ( IImageBlock image,
                      EInterpolationType type,
                      IAddress address,
                      int brightnessOffset,
                      double contrastScale,
                      int dihedralAffineTransformerIndex ) throws ValueError {
        super(
                image,
                type,
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformerIndex);
        this.type = EInterpolationType.BILINEAR;
    }

    /**
     * @return
     */
    public
    EInterpolationType getType () {
        return type;
    }
}
