package org.stranger2015.opencv.fic.core.cli;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.FileOptionHandler;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.codec.IDecoder;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.search.EMetrics;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The available options to the system
 */
public
class Options<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer> {
   @Argument(metaVar = "COMMAND", handler = CommandHandler.class)
    @Option(name = "-e")
    public
    IEncoder <N> encoder;

    @Option(name = "-d")
    public
    IDecoder <N> decoder;

    public IPartitionProcessor <N> partitionProcessor;

    protected int blockHeight;

    protected int blockWidth;

    public static final String tilerDelimit = "x";
    public static final String scaleDelimit = ":";

    /**
     *
     */
    public
    Options () throws ClassNotFoundException, NoSuchMethodException {

    }

    /**
     * @return
     */
    //==========================================================================
    @Option(name = "-f",
            aliases = {"--fuzz"},
            usage = "colors within this distance are considered equal",
            handler = FuzzHandler.class
    )
    public
    double getFuzz () {
        return fuzz;
    }

    @Option(name = "-h",
            aliases = {"--help", "-?"},
            usage = "display this help message",
            handler = NoneHandler.class
    )

    public
    void help () {
    }

    @Option(name = "debug",
            usage = "display debug messages",
            handler = NoneHandler.class
    )
    public
    void debug () {
    }

    @Option(name = "-m",
            aliases = {"--metrics"},
            usage = "the metrics to use when comparing images",
            handler = MetricsHandler.class
    )
    public EMetrics metrics;

    @Option(name = "-s",
            aliases = {"--scale"},
            usage = "the width and height scale factors for the domain image",
            handler = ScaleTransformHandler.class
    )
    public ScaleTransform <?, ?> scaleTransform;

    @Option(name = "-t", aliases = {"--tile"},
            usage = "the width and height in pixels to tile the image",
            handler = TilerHandler.class
    )
    public String wXh;

    @Option(name = "-c", aliases = {"--colorspace"},
            usage = "color spaces the image can be converted to",
            handler = ColorspaceHandler.class
    )
    public EtvColorSpace colorSpace;

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

    @Option(name = "-p", aliases = {"--partition"},
            usage = "partition scheme for the processing image",
            handler = PartitionSchemeHandler.class
    )
    public EPartitionScheme scheme;

 /**
  * @return
  */
 public
 ISearchProcessor <?, ?, ?> getSearchProcessor () {
  return searchProcessor;
 }

 @Option(name = "-sp",
            aliases = {"--search"},
            usage = "processor searching for the best transformation set",
            handler = SearchProcessorHandler.class
    )
    public ISearchProcessor <?, ?, ?> searchProcessor;

    /**
     * @param fn
     * @return
     * @throws FileNotFoundException
     */
    @Option(name = "-i",
            aliases = {"--input"},
            usage = "input image",
            handler = FileReadOptionHandler.class
    )
    public
    File input ( String fn ) throws FileNotFoundException {
        File f = new File(fn);
        if (f.exists()) {
            return f;
        }
        throw new FileNotFoundException(fn);
    }

    @Option(name = "-o",
            aliases = {"--output"},
            usage = "output image",
            handler = FileOptionHandler.class
    )
    public
    File output ( String fn, boolean overWrite ) throws FileNotFoundException {
        File f = new File(fn);
        if (!f.exists() || overWrite) {
            return f;
        }

        throw new FileNotFoundException(fn);
    }

    //==========================================================================

    protected boolean help = false;

    protected boolean debug = false;

    protected double fuzz = 5;
//    protected EMetrics metrics = AE;
//    protected EtvColorSpace colorSpace = YUV;
//    protected String filename;
//    protected EPartitionScheme scheme;

    protected ScaleTransform  domainScale
            = new ScaleTransform <>(
            null,
            .5,
            .5,
            false,
            null);
}
