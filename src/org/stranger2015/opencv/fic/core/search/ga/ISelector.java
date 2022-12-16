package org.stranger2015.opencv.fic.core.search.ga;

public
interface ISelector<C extends Chromosome>{
    /**
     * @return
     */
    C selectFirst ();

    /**
     * @return
     */
    C selectSecond ();

    /**
     * @return
     */
    ESelectionType getType ();
}
