package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;

import java.util.function.Function;

/**
 * @param <T>
 * @param <A>
 */
public
class SearchProcessorEvaluator<T extends IImage <A>, A extends Address <A>>
        implements Function <T, Number> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    public
    Number apply ( T t ) {
        return null;
    }
}
