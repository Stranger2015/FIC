package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValidator;

import java.io.File;
import java.util.List;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

/**
 *
 */
public
class FileAlreadyExistsValidator extends NoValidator implements IParameterValidator {

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
        String[] values = value.split(value);
        File file = new File(values[0]);
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new ParameterException(format("File %s is a directory", values[0]));
            }
            if (!values[1].toUpperCase(ENGLISH).equals("Y")) {
                throw new ParameterException("Specify another (writable) file");
            }
        }
    }
}
