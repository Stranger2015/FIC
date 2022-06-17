package org.stranger2015.opencv.fic.utils;

import com.beust.jcommander.validators.NoValidator;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.search.EMetrics;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.converters.MetricsConverter;
import org.stranger2015.opencv.fic.utils.converters.PixelTilerConverter;
import org.stranger2015.opencv.fic.utils.validators.*;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.core.codec.EtvColorSpace.*;
import static org.stranger2015.opencv.fic.core.search.EMetrics.*;

/**
 * The available options to the system
 */
public
class Options<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer> {

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

    //==========================================================================
    @Parameter(names = {"-f", "--fuzz"},
            description = "colors within this distance are considered equal",
            validateWith = FuzzValidator.class
    )

    @Parameter(names = {"-h", "--help"},
            description = "display this help message",
            validateWith = NoValidator.class
    )

    @Parameter(names = {"", "--debug"},
            description = "display debug messages",
            validateWith = NoValidator.class
    )

    @Parameter(names = {"-m", "--metrics"},
            description = "the metrics to use when comparing images",
            converter = MetricsConverter.class,
            validateWith = MetricsValidator.class
    )

    @Parameter(names = {"-s", "--scale"},
            description = "the width and height scale factors for the domain image",
            converter = ScaleTransformConverter.class,
            validateWith = ScalerValidator.class
    )

    @Parameter(names = {"-t", "--tile"},
            description = "the width and height in pixels to tile the image",
            converter = PixelTilerConverter.class,
            validateWith = TilerValidator.class
    )

    @Parameter(names = {"-c", "--colorspace"},
            description = "color spaces the image can be converted to",
            validateWith = NoValidator.class,
            validateValueWith = ColorspaceValidator.class
    )
//
//    @Parameter(names = {"-e", "--encode"},
//            description = "encode the image being compressed",
//            validateWith = FileExistsValidator.class,
//            arity = 2
//    )
//
//    @Parameter(names = {"-d", "--decode"},
//            description = "decode the image being decompressed",
//            validateWith = FileAlreadyExistsValidator.class,
//            arity = 2
//    )

    @Parameter(names = {"-p", "--partition"},
            description = "partition scheme for the processing image",
            validateValueWith = PartitionSchemeValidator.class,
            arity = 1,
            validateWith = NoValidator.class
    )

    @Parameter(names = {"-sp", "--search"},
            description = "processor searching for the best transformation set",
            validateWith = SearchProcessorValidator.class,
            arity = 2
    )

    //==========================================================================

    protected boolean help = false;

    protected boolean debug = false;

    protected double fuzz = 5;
    protected EMetrics metrics = AE;
    protected EtvColorSpace colorSpace = YUV;
    protected String filename;
    protected EPartitionScheme scheme;

    protected ScaleTransform <A, G> domainScale
            = new ScaleTransform <>(
            null,
            .5,
            .5,
            false,
            null);

    protected ITiler <M, A> tiler = ITiler.create(blockWidth, blockHeight);
}
