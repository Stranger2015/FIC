package org.stranger2015.opencv.fic.core.cli;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.EnumOptionHandler;
import org.kohsuke.args4j.spi.Setter;

/**
 * @param <T>
 */
public
class CommandHandler<T extends Enum<T>> extends EnumOptionHandler <T> {
    /**
     * @param parser
     * @param option
     * @param setter
     * @param enumType
     */
    public
    CommandHandler ( CmdLineParser parser, OptionDef option, Setter <? super T> setter, Class <T> enumType ) {
        super(parser, option, setter, enumType);
    }
}
