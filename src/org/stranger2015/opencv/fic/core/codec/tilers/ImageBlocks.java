package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.Tree;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.ArrayList;

/**
 * @param <N>
 */
public
class ImageBlocks<N extends TreeNode <N>>
        extends ArrayList<IImageBlock > {

    protected Tree<N> tree;
}
