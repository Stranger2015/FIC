package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.function.Function;

/**
 
 * @param <A>
 * @param <G>
 */
public
class SearchProcessorEvaluator</* M extends IImage <A> */, A extends IAddress <A>, G extends BitBuffer>
        implements Function <M, Number> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    public
    Number apply ( M t ) {
        return null;
    }
}
