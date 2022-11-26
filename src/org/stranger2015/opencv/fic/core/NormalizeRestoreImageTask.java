package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class NormalizeRestoreImageTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BidiTask <N, A, G> {

    /**
     * @param filename
     * @param scheme
     */
    public
    NormalizeRestoreImageTask ( String filename,
                                EPartitionScheme scheme,
                                ICodec <N, A, G> codec
    ) {
        super(
                filename,
                scheme,
                codec,
                new NormalizeImageShapeTask <>(
                        filename,
                        scheme,
                        codec,
                        List.of(
                                new SplitImageTask <>(
                                        filename,
                                        scheme,
                                        codec,
                                        List.of()
                                )
                        )
                ),
                new RestoreImageTask <>(
                        filename,
                        scheme,
                        codec,
                        List.of(
                                new MergeImageTask <>(
                                        filename,
                                        scheme,
                                        codec,
                                        List.of()
                                )
                        )
                )
        );
    }
}
