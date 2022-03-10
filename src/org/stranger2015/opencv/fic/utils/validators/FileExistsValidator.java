package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 *
 */
public
class FileExistsValidator implements IParameterValidator {
    @Override
    public
    void validate ( String arg, String val ) throws ParameterException {
        File file = new File(val);
        if (!file.exists()) {
            throw new ParameterException(String.format("File for %s doesn't exist: %s", arg, val));
        }
    }
}
