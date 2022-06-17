package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValidator;
import org.stranger2015.opencv.fic.utils.Options;

import static java.lang.String.*;

/**
 *
 */
public
class TilerValidator extends NoValidator implements IParameterValidator {


    /**
     * @param arg
     * @param val
     * @throws ParameterException
     */
    @Override
    public void validate(String arg, String val) throws ParameterException {
        String[] values = val.split(Options.tilerDelimit);
        for (String num : values) {
            try {
                if (values.length != 2 || num.isEmpty() || Double.parseDouble(num) <= 1) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                throw new ParameterException(format("Parameter %s has an invalid value: %s", arg, num));
            }
        }
    }
}
