package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.List;

public
interface ITree<N extends TreeNode <N>> {
    @SuppressWarnings("unchecked")
    static
    <N extends TreeNode <N>>
    @NotNull Tree < N> create ( Class <?> clazz, TreeNode <N> node, IImageBlock  imageBlock ) {
        @NotNull Tree <N> result;
        int rc = 0;
        try {
            Tree <N> tree = (Tree <N>) clazz.getDeclaredConstructor().newInstance(node, imageBlock);

            tree.initialize(node, imageBlock);
            result = tree;

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     * @throws ValueError
     */
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent, EDirection quadrant, IIntSize rect )
            throws ValueError;

    /**
     * @return
     */
    List <TreeNode <N>> getNodes ();
}
