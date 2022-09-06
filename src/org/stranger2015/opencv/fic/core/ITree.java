package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

public
interface ITree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    @SuppressWarnings("unchecked")
    static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    @NotNull Tree <N,A,G> create ( Class <?> clazz, TreeNode <N, A, G> node, IImageBlock <A> imageBlock ) {
        @NotNull Tree <N, A, G> result;
        int rc = 0;
        try {
            Tree <N, A, G> tree = (Tree <N, A, G>) clazz.getDeclaredConstructor()
                    .newInstance(node, imageBlock);

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
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect )
            throws ValueError;

    /**
     * @return
     */
    List <TreeNode <N, A, G>> getNodes ();
}
