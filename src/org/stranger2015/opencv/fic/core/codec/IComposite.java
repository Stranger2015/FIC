package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

public
interface IComposite<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    /**
     * @return
     */
    List <ITiler <N, A, G>> getPipeline();
}
