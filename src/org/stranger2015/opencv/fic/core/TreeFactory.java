package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 */
public
class TreeFactory<N extends TreeNode <N,A,M,G>, A extends Address, IImage extends IImage, G extends BitBuffer> {

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
