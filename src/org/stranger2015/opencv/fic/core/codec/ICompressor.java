package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.FractalModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

public
interface ICompressor<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer> {

    /**
     * @param image
     * @return
     */
    FractalModel <N, A, M, G> compress ( M image, int sourceSize, int destinationSize, int step  );

    /**
     * @return
     */
    Set <ImageTransform <M, A, G>> getTransforms ();

    /**
     * @return
     */
    FractalModel <N, A, M, G> getModel ();
}
