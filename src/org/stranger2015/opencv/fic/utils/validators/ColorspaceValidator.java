package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValueValidator;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;

import static java.lang.String.format;

/**
 *
 */
public
class ColorspaceValidator<T extends EtvColorSpace> extends NoValueValidator <T> implements IValueValidator <T> {

    /**
     * Validate the parameter.
     *
     * @param name  The name of the parameter (e.g. "-host").
     * @param value The value of the parameter that we need to validate
     * @throws ParameterException Thrown if the value of the parameter is invalid.
     */
    @Override
    public
    void validate ( String name, T value ) throws ParameterException {
        if (!EtvColorSpace.YIG.equals(value) &&
                !EtvColorSpace.YUV.equals(value) &&
                !EtvColorSpace.Y_CB_CR.equals(value) &&
                !EtvColorSpace.Y_PB_PR.equals(value)) {
                    throw new ParameterException(format("Unexpected value: %s", value));
                }
    }
}
