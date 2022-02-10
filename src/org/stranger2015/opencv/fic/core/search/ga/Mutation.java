package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <T>
 */
public
class Mutation<T extends Individual> extends Operator<T> implements IUnaryOperator<T>{

    /**
     * @param rate
     */
    protected
    Mutation ( double rate ) {
        super(rate);
    }

    /**
     * Applies this function to the given argument.
     *
     * @param population the function argument
     * @return the function result
     */
    @Override
    public
    Number apply ( Population <T> population ) {
        return null;
    }
}
