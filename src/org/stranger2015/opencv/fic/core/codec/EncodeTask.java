package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Task;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class EncodeTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>

        extends Task <N, A, M, G> {

    protected final IEncoder <N, A, M, G> encoder;
    protected M image;

    /**
     * @param filename
     * @param scheme
     */
    public
    EncodeTask ( String filename,
                 EPartitionScheme scheme,
                 List <Task <N, A, M, G>> tasks,
                 IEncoder <N, A, M, G> encoder ) {

        super(filename, scheme, tasks);

        this.encoder = encoder;
    }

    /**
     * @return
     */
    public
    IEncoder <N, A, M, G> getEncoder () {
        return encoder;
    }
}