package org.stranger2015.opencv.fic.core.codec.classifiers;

import java.util.Set;

/**
 *
 */
public
interface IFuzzyClassifier extends IClassifier {
    /**
     * @return
     */
    @Override
    Set <IClassifiable> classify ();
}
