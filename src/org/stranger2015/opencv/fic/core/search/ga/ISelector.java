package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

public
interface ISelector</* M extends IImage <A> */, A extends IAddress <A>, G extends BitBuffer/* C extends Chromosome < M,A, G>*/> {
    /**
     * @return
     */
    M selectFirst ();

    /**
     * @return
     */
    M selectSecond ();

    /**
     * @return
     */
    ESelectionType getType ();
}
