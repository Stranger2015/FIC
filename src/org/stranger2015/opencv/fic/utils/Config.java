package org.stranger2015.opencv.fic.utils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.search.EMetrics;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * Holds the configuration of the system,
 * based on the arguments given
 */
@SuppressWarnings(value = {"rawtypes"})
public
class Config<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    private Commands command;

    private final Options <N, A, G> options;
    private final JCommander parser;

    private EtvColorSpace colorSpace;

//    private IEncoder <N, A, G> encoder;
//    private IDecoder <N, A, G> decoder;

    /**
     * given args, configuration parses them
     * and initializes the option values
     *
     * @param args the args to parse
     */
    public
    Config ( String[] args ) throws ClassNotFoundException, NoSuchMethodException {
        parser = new JCommander(this.options = new Options<>());

        parser.setProgramName(FicApplication.class.getCanonicalName());
        parser.addCommand(Commands.COMPRESS.id(), Commands.COMPRESS);
        parser.addCommand(Commands.DECOMPRESS.id(), Commands.DECOMPRESS);

        try {
            parser.parse(args);
        } catch (ParameterException pe) {
            selectUsage(parser.getParsedCommand());
            System.err.println(pe.getMessage());
            System.exit(options.help ? 0 : 1);
        }

        String cmd = parser.getParsedCommand();

        /*
         * even if there was not a parsing error
         * help could still have been requested
         */
        if (options.help) {
            selectUsage(cmd);
            System.exit(0);
        }

        if (cmd != null) {
            command = Commands.valueOf(cmd.toUpperCase());
        }
        else {
            parser.usage();
            System.err.println(EError.REQUIRED_ARG_NOT_FOUND.description("<command>"));
            System.exit(1);
        }
    }

    /**
     * if a command was parsed, show
     * the usage for that command
     */
    private
    void selectUsage ( String cmd ) {
        if (cmd != null) {
            parser.usage(cmd);
        }
        else {
            parser.usage();
        }
    }

    /**
     * @return
     */
    public
    boolean help () {
        return options.help;
    }

    /**
     * @return
     */
    public
    boolean debug () {
        return options.debug;
    }

    /**
     * @return
     */
    public
    EMetrics metrics () {
        return options.metrics;
    }

    /**
     * @return
     */
    public
    double fuzz () {
        return options.fuzz;
    }

    /**
     * @return
     */
    public
    ScaleTransform <A, G> domainScale () {
        return options.domainScale;
    }

    /**
     * @return
     */
    public
    ITiler <N, A, G> tiler () {
        return options.tiler;
    }

    /**
     * @return
     */
    public
    Commands command () {
        return command;
    }

//    /**
//     * @return
//     */
//    public
//    File input () {
//        return command.input;
//    }
//
//    /**
//     * @return
//     */
//    public
//    File output () {
//        return command.output;
//    }

    /**
     *
     */
    public
    void printUsage () {
        parser.usage();
    }

    /**
     * @param command
     */
    public
    void printUsage ( Commands command ) {
        parser.usage(command.id());
    }

    /**
     * @return
     */
    public
    EtvColorSpace colorSpace () {
        return options.colorSpace;
    }

    /**
     * @return
     */
    public
    EPartitionScheme partitionScheme () {
        return options.scheme;
    }

    /**
     * @return
     */
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     * @param colorSpace
     */
    public
    void setColorSpace ( EtvColorSpace colorSpace ) {
        this.colorSpace = colorSpace;
    }

//    /**
//     * @return
//     */
//    public
//    IEncoder <N, A, G> encoder () {
//        return encoder;
//    }


//    /**
//     * @return
//     */
//    public
//    IDecoder <N,A,M, G> decoder () {
//        return decoder;
//    }
//
//    public
//    M image () {
//        return null;//image;
//    }
}
