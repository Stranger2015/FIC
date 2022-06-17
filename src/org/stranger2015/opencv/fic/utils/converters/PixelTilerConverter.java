package org.stranger2015.opencv.fic.utils.converters;

import com.beust.jcommander.IStringConverter;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.RectangularTiler;
import org.stranger2015.opencv.fic.utils.Options;

import static java.lang.Integer.parseInt;

/**
 *
 */
@SuppressWarnings("rawtypes")
public
class PixelTilerConverter implements IStringConverter <ITiler<?,?>> {
    /**
     * @param arg
     * @return
     */
    @Override
    public
    ITiler<?,?> convert ( String arg ) {
        String[] wh = arg.split(Options.tilerDelimit);

        return ITiler.create(parseInt(wh[0]), parseInt(wh[1]));
    }
}