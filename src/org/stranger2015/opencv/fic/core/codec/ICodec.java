package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.Task;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 */
public
interface ICodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Runnable {

    /**
     * @return
     */
    @Contract(pure = true)
    static @Unmodifiable
    List <IListener <?>> getListeners () {
        return List.of();
    }

    /**
     * @return
     */
    IEncoder <N, A, G> getEncoder ();

    /**
     * @return
     */
    IDecoder <N, A, G> getDecoder ();

    /**
     * @return
     */
    int getImageSizeBase ();

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    IAddress <A> createAddress ( int address ) throws ValueError;

    /**
     * @param task
     */
    void addTask ( Task <N, A, G> task );
}
