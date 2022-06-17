package org.stranger2015.opencv.fic.utils.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.NoValueValidator;
import org.stranger2015.opencv.fic.core.EPartitionScheme;

/**
 * @param <T>
 */
public
class PartitionSchemeValidator<T extends EPartitionScheme> extends NoValueValidator<T> implements IValueValidator<T> {
    /**
     * Validate the parameter.
     *
     * @param name  The name of the parameter (e.g. "-host").
     * @param value The value of the parameter that we need to validate
     * @throws ParameterException Thrown if the value of the parameter is invalid.
     */
    @Override
    public
    void validate ( String name, EPartitionScheme value ) throws ParameterException {

    }
}
