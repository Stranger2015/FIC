package org.stranger2015.opencv.fic.core.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.EnumOptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

/**
 * @param <T>
 */
public
class MetricsHandler<T extends Enum<T>> extends EnumOptionHandler<T> {

    /**
     * @param parser
     * @param option
     * @param setter
     * @param enumType
     */
    public
    MetricsHandler ( CmdLineParser parser, OptionDef option, Setter <? super T> setter, Class <T> enumType ) {
        super(parser, option, setter, enumType);
    }

    /**
     * Called if the option that this owner recognizes is found.
     *
     * @param params The rest of the arguments. This method can use this
     *               object to access the arguments of the option if necessary.
     *               <p>
     *               The object is valid only during the method call.
     * @return The number of arguments consumed. (For example, returns {@code 0}
     * if this option doesn't take any parameters.)
     */
    @Override
    public
    int parseArguments ( Parameters params ) throws CmdLineException {
        return 1;
    }

    /**
     * Gets the default meta variable name used to print the usage screen.
     * <p>
     * The value returned by this method can be a reference in the
     * {@code ResourceBundle}, if one was passed to
     * {@link CmdLineParser}.
     *
     * @return {@code null} to hide a meta variable.
     */
    @Override
    public
    String getDefaultMetaVariable () {
        return null;
    }
}
