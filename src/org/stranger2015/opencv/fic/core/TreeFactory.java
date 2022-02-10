package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class TreeFactory<N extends TreeNode <N,A,M>, A extends Address<A>, M extends IImage> {

    private
    TreeFactory () {
    }

//   Tree<N,M,A> createTree(String clazzName) throws ReflectiveOperationException {
//       Class<?> clazz= Class.forName(clazzName);
//       Constructor <?> ctor = clazz.getDeclaredConstructor();
//       Tree<N,M,A> tree= (Tree <N, M, A>) ctor.newInstance();
//
//       return null;
//   }

}
