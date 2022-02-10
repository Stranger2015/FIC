package org.stranger2015.opencv.fic.core.search.ga;

public
interface ISelector<T extends Individual> {
    Individual selectFirst();
    Individual selectSecond();
    ESelectionType getType();

}
