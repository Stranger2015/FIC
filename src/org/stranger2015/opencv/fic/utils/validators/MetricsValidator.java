package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.stranger2015.opencv.fic.core.search.EMetrics;


/**
 * validate the given value is a Metric
 *
 * @see EMetrics
 */
public class MetricsValidator implements IParameterValidator {

    @Override
    public void validate(String arg, String val) throws ParameterException {
        try {
            EMetrics.valueOf(val.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new ParameterException(String.format("Parameter %s has an invalid value: %s", arg, val));
        }
    }
}
