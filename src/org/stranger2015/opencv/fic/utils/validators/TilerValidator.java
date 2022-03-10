package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.stranger2015.opencv.fic.utils.Options;

public
class TilerValidator implements IParameterValidator {
    @Override
    public void validate(String arg, String val) throws ParameterException {
        String[] values = val.split(Options.tilerDelimit);
        for (String num : values) {
            try {
                if (values.length != 2 || num.isEmpty() || Double.parseDouble(num) <= 1) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                throw new ParameterException(String.format("Parameter %s has an invalid value: %s", arg, num));
            }
        }
    }
}
