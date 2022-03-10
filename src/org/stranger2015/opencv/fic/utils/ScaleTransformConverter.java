package org.stranger2015.opencv.fic.utils;

import com.beust.jcommander.IStringConverter;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import static java.lang.Double.parseDouble;

/**
 * a converter that turns a series of string arguments into a scale transform
 *
 * @see ScaleTransform
 */
@SuppressWarnings("unchecked")
public
class ScaleTransformConverter
        implements IStringConverter <ScaleTransform> {

    /**
     * @param arg
     * @return
     */
    @Override
    public
    ScaleTransform  convert ( String arg ) {
        String[] sf = arg.split(Options.scaleDelimit);
        return new ScaleTransform (
                null,
                parseDouble(sf[0]),
                parseDouble(sf[1]),
                false,
                null);
    }
}
