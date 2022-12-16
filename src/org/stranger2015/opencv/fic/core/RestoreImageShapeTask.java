package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

public
class RestoreImageShapeTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Task <N> {

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    RestoreImageShapeTask ( String filename,
                            EPartitionScheme scheme,
                            ICodec <N> codec,
                            List <Task <N>> tasks ) {

        super(filename, scheme, codec,tasks);
    }
}
