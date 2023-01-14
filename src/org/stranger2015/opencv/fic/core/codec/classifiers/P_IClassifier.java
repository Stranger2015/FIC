package org.stranger2015.opencv.fic.core.codec.classifiers;

import java.util.Set;
import java.util.TreeSet;

import static java.util.Collections.unmodifiableSortedSet;

/**
 *
 */
public
class P_IClassifier implements ICrispClassifier {

    /**
     * @return
     */
    @Override
    public
    Set <IClassifiable> classify () {
        return unmodifiableSortedSet(new TreeSet <>());
    }

}
