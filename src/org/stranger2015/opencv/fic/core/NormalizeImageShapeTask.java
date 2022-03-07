package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class NormalizeImageShapeTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer> extends Task <N, A, M, G> {

    /**
     * @param fileName
     * @param tasks
     */
    @SafeVarargs
    public
    NormalizeImageShapeTask ( String fileName, EPartitionScheme scheme, Task <N, A, M, G>... tasks ) {
        this(fileName, scheme, List.of(tasks));
    }

    /**
     * @param fileName
     * @param scheme
     * @param tasks
     */
    NormalizeImageShapeTask ( String fileName, EPartitionScheme scheme, List<Task <N, A, M, G>> tasks ) {
        super(fileName, scheme, tasks);
    }
}
