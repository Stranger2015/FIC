package org.stranger2015.opencv.fic.utils;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.RectangularTiler;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.EMetrics;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.converters.MetricsConverter;
import org.stranger2015.opencv.fic.utils.converters.PixelTilerConverter;
import org.stranger2015.opencv.fic.utils.validators.ColorspaceValidator;
import org.stranger2015.opencv.fic.utils.validators.MetricsValidator;
import org.stranger2015.opencv.fic.utils.validators.ScalerValidator;
import org.stranger2015.opencv.fic.utils.validators.TilerValidator;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * The available options to the system
 */
@SuppressWarnings("rawtypes")

public
class Options<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer> {

    protected int blockHeight;
    protected int blockWidth;

    public static final String tilerDelimit = "x";
    public static final String scaleDelimit = ":";

    /**
     *
     */
    public
    Options () {
    }

    @Parameter(names = {"-f", "--fuzz"},
            description = "colors within this distance are considered equal")
    protected double fuzz = 5;

    @Parameter(names = {"-h", "--help"},
            description = "display this help message")
    protected boolean help = false;

    @Parameter(names = {"-d", "--debug"},
            description = "display debug messages")
    protected boolean debug = false;

    @Parameter(names = {"-m", "--metrics"},
            description = "the metrics to use when comparing images",
            converter = MetricsConverter.class,
            validateWith = MetricsValidator.class)
    protected EMetrics metrics = EMetrics.AE;

    @Parameter(names = {"-s", "--scale"},
            description = "the width and height scale factors for the domain image",
            converter = ScaleTransformConverter.class,
            validateWith = ScalerValidator.class)

    @Parameter(names = {"-t", "--tile"},
            description = "the width and height in pixels to tile the image",
            converter = PixelTilerConverter.class,
            validateWith = TilerValidator.class)

    @Parameter(names = {"-c", "--colorspace"},
            description = "color spaces the image can be converted to",
//            converter = ColorspaceConverter.class,
            validateWith = ColorspaceValidator.class

    )

    protected ScaleTransform domainScale
            = new ScaleTransform <>(
            null,
            .5,
            .5,
            false,
            null);

    protected RectangularTiler <N, A, M, G> tiler = new RectangularTiler <>(blockWidth, blockHeight);
}
