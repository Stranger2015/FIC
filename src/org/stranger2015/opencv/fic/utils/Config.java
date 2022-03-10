package org.stranger2015.opencv.fic.utils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.stranger2015.opencv.fic.core.EError;
import org.stranger2015.opencv.fic.core.FicApplication;
import org.stranger2015.opencv.fic.core.RectangularTiler;
import org.stranger2015.opencv.fic.core.search.EMetrics;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.io.File;

/**
 * Holds the configuration of the system,
 * based on the arguments given
 */
@SuppressWarnings(value = {"rawtypes"})
public
class Config {

        private Commands   command;
        private final Options    options;
        private final JCommander parser;

        /**
         * given arguments, configuration parses them
         * and initializes the option values
         *
         * @param arguments the arguments to parse
         */
        public Config(String[] arguments) {
            parser = new JCommander(this.options = new Options());

            parser.setProgramName(FicApplication.class.getCanonicalName());
            parser.addCommand(Commands.COMPRESS.id(), Commands.COMPRESS);
            parser.addCommand(Commands.DECOMPRESS.id(), Commands.DECOMPRESS);

            try {
                parser.parse(arguments);
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
            } else {
                parser.usage();
                System.err.println(EError.REQUIRED_ARG_NOT_FOUND.description("<command>"));
                System.exit(1);
            }
        }

        /**
         * if a command was parsed, show
         * the usage for that command
         */
        private void selectUsage(String cmd) {
            if (cmd != null) {
                parser.usage(cmd);
            } else {
                parser.usage();
            }
        }

    /**
     * @return
     */
        public boolean help() {
            return options.help;
        }

    /**
     * @return
     */
        public boolean debug() {
            return options.debug;
        }

    /**
     * @return
     */
        public EMetrics metrics() {
            return options.metrics;
        }

    /**
     * @return
     */
        public double fuzz() {
            return options.fuzz;
        }

    /**
     * @return
     */
        public
        ScaleTransform domainScale() {
            return options.domainScale;
        }

    /**
     * @return
     */
        public
        RectangularTiler tiler() {
            return options.tiler;
        }

    /**
     * @return
     */
        public Commands command() {
            return command;
        }

    /**
     * @return
     */
        public
        File input() {
            return command.input;
        }

    /**
     * @return
     */
        public File output() {
            return command.output;
        }

    /**
     *
     */
        public void printUsage() {
            parser.usage();
        }

    /**
     * @param command
     */
        public void printUsage(Commands command) {
            parser.usage(command.id());
        }
    }
