package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValidator;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param
 * @param <G>
 */
public
class SearchProcessorValidator</* IImage extends IImage */, A extends IAddress , G extends BitBuffer>
        extends NoValidator implements IParameterValidator{


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


    }
}
