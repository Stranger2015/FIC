package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;


public abstract
class Selector</*T extends Individual <T, A, G, C>*/ M  extends IImage<A>, A extends IAddress <A>, G extends BitBuffer
        /*C extends Chromosome <A, G>*/>

        implements ISelector <A, G> {

    protected final ESelectionType type;

    /**
     * @param type
     */
    @Contract(pure = true)
    protected
    Selector ( ESelectionType type ) {
        this.type = type;
    }

    /**
     * @return
     */
    @Override
    public
    ESelectionType getType () {
        return type;
    }
}
