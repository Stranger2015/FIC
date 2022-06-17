package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

public
interface IMutationOperator</*T extends Individual <T, ?, ?, ?>*/ /* M extends IImage <A> */, A extends Address<A>,
        G extends BitBuffer> {
    /**
     * @param geneIndex
     */
    void setGeneIndex ( int geneIndex );
}
