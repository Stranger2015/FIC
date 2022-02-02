package org.stranger2015.opencv.fic;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.NodeList;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 *
 */
@Deprecated
public
class DomainPool<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends NodeList <N, A, M> {
    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public
    DomainPool () {
    }
}
