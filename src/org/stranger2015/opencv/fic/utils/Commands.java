package org.stranger2015.opencv.fic.utils;

import  com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import org.stranger2015.opencv.fic.utils.validators.FileAlreadyExistsValidator;
import org.stranger2015.opencv.fic.utils.validators.FileExistsValidator;

import java.io.File;

/**
 * The available commands to the system
 */
public enum Commands {

    @Parameter(
            names = "compress",
            description = "compress the given image",
            hidden = true,
            validateWith = NoValidator.class
    )
    COMPRESS,
    @Parameter(
            names = "decompress",
            description = "decompress the given file",
            hidden = true,
            validateWith = NoValidator.class)
    DECOMPRESS;

    @Parameter(
            names = {"-i", "--input"},
            description = "the input file",
            required = true,
            validateWith = FileExistsValidator.class
    )

    private File input;

    @Parameter(
            names = {"-o", "--output"},
            description = "the output file",
            required = true,
            validateWith = FileAlreadyExistsValidator.class,
            arity = 2
    )

    private File output;
  /**
     * the command identifier is a unique string for the command
     *
     * @return the command identifier
     */
    public
    String id () {
        return this.name().toLowerCase();
    }

    public
    File getInput () {
        return input;
    }

    public
    void setInput ( File input ) {
        this.input = input;
    }

    public
    File getOutput () {
        return output;
    }

    public
    void setOutput ( File output ) {
        this.output = output;
    }
}

//@Parameters(commandDescription = "compress and decompress commands")
//public enum Commands {
//
//    @Parameter(names        = "compress",
//            description  = "compress the given image",
//            hidden       = true)
//    COMPRESS,
//    @Parameter(names        = "decompress",
//            description  = "decompress the given file",
//            hidden       = true)
//    DECOMPRESS;
//
//    @Parameter(names        = {"-i", "--input"},
//            description  = "the input file",
//            required     = true,
//            validateWith = FileExistsValidator.class
//            )
//    protected File input;
//
//    @Parameter(names        = {"-o", "--output"},
//            description  = "the output file"
//required     = true,
//            validateWithValue = FileAlreadyExistsValidator.class
//
//            )
//    protected File output;
//
//    /**
//     * the command identifier is a unique string for the command
//     *
//     * @return the command identifier
//     */
//    public String id() {
//        return this.name().toLowerCase();
//    }
//}

