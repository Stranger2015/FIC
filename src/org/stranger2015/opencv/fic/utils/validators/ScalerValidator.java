package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValidator;
import org.stranger2015.opencv.fic.utils.Options;

import static java.lang.String.*;

/**
 * validate the given value is a number greater than zero
 */
public
class ScalerValidator extends NoValidator implements IParameterValidator {
    @Override
    public
    void validate ( String arg, String val ) throws ParameterException {
        String[] values = val.split(Options.scaleDelimit);
        for (String num : values) {
            try {
                if (values.length != 2 || num.isEmpty() || Double.parseDouble(num) <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                throw new ParameterException(format("Parameter %s has an invalid value: %s", arg, num));
            }
        }
    }
}
