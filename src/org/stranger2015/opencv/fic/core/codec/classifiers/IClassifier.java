package org.stranger2015.opencv.fic.core.codec.classifiers;

import java.util.Set;

/**
 *
 */
public
interface IClassifier {

    /**
     * @return
     */
    Set <IClassifiable> classify ();
}
