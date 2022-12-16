package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class NormalizeRestoreImageTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BidiTask <N> {

    /**
     * @param filename
     * @param scheme
     */
    public
    NormalizeRestoreImageTask ( String filename,
                                EPartitionScheme scheme,
                                ICodec <N> codec
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
