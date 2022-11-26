package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface IDecoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ICodecListener<N, A, G>{

    /**
     *
     */
    IImage<A > decode ( FCImageModel <N, A, G> fractalModel );

    /**
     * @param filename
     * @param fractalModel
     */
    void saveModel(String filename, FCImageModel <N, A, G> fractalModel);
}
