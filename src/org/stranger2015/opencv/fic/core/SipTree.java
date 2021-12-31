package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.VsaTree;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SipTree<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
        extends VsaTree <N, A, M> {

//    /**
//     * @return
//     */
//    @Override
//    public
//    TreeNode <N, A, M> getRoot () {
//        return super.getRoot();
//    }

    public
    SipTree (TreeNode <N, A, M> root, M image, TreeNodeAction <N> action) {
        super(root, image, action);
    }

//    /**
//     * @param parent
//     * @param quadrant
//     * @param rect
//     * @return
//     */
////    @Override
//    public
//    TreeNode <N, A, M> nodeInstance ( VsaTreeNode <N, A, M> parent, EDirection quadrant, Rect rect ) {
//        return null;//todo
//    }



    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) SipTreeNode.class;
    }
}
