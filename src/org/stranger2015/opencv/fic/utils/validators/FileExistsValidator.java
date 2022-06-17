package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValidator;

import java.io.File;

import static java.lang.String.*;

/**
 *
 */
public
class FileExistsValidator extends NoValidator implements IParameterValidator {

    /**
     * Validate the parameter.
     *
     * @param name  The name of the parameter (e.g. "-host").
     * @param value The value of the parameter that we need to validate
     * @throws ParameterException Thrown if the value of the parameter is invalid.
     */
    @Override
    public
    void validate ( String name, String value ) throws ParameterException {
        File file = new File(value);
        if (!file.exists()) {
            throw new ParameterException(format("File for %s doesn't exist: %s", name, value));
        }
    }
}
