package org.stranger2015.opencv.fic.core.codec.classifiers;

import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlockInfo;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public
class Fisher24Classifier extends ImageBlockClassifier {
    /**
     *
     */
    public
    Fisher24Classifier () throws IOException {
        super(new SoftReference <>(new ImageBlockInfo[24]));
        categories = new TreeSet <>();
    }

    /**
     * @param level2Classes
     * @throws IOException
     */
    public
    Fisher24Classifier ( ImageBlockInfo[] level2Classes ) throws IOException {
        super(level2Classes);
    }

    /**
     * @return
     */
    @Override
    public
    Set <IClassifiable> classify () {
        return categories;//fixme
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return "Fisher24Classifier{}";
    }
}
