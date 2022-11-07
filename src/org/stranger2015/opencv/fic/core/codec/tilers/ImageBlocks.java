package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.Tree;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;

public
class ImageBlocks<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ArrayList<IImageBlock <A>> {

    protected Tree<N, A, G> tree;




}
