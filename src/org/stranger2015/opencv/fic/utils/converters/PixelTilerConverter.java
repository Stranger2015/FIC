package org.stranger2015.opencv.fic.utils.converters;

import com.beust.jcommander.IStringConverter;
import org.stranger2015.opencv.fic.core.RectangularTiler;
import org.stranger2015.opencv.fic.utils.Options;

import static java.lang.Integer.parseInt;

/**
 *
 */
@SuppressWarnings("rawtypes")
public
class PixelTilerConverter implements IStringConverter <RectangularTiler> {
    /**
     * @param arg
     * @return
     */
    @Override
    public
    RectangularTiler convert ( String arg ) {
        String[] wh = arg.split(Options.tilerDelimit);

        return new RectangularTiler(parseInt(wh[0]), parseInt(wh[1]));
    }
}