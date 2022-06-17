package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.DecodeTask;
import org.stranger2015.opencv.fic.core.codec.EncodeTask;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Arrays;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.FIXED_SIZE;

/**
 *
 */
public abstract
class BidiTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Task <N, A, G> {

    protected Task <N, A, G> task;
    protected Task <N, A, G> inverseTask;

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    @SafeVarargs
    BidiTask ( String filename,
               EPartitionScheme scheme,
               ICodec <N, A, G> codec,
               Task <N, A, G>... tasks ) {

        this(filename, scheme, codec, Arrays.asList(tasks));
    }

    /**
     * @param filename
     * @param tasks
     */
    public
    BidiTask ( String filename,
               EPartitionScheme scheme,
               ICodec <N, A, G> codec,
               List <Task <N, A, G>> tasks ) {

        super(filename, scheme, codec, tasks);

        if (tasks.isEmpty()) {
            return;
        }

        this.task = tasks.get(0);
        this.inverseTask = tasks.get(1);

        addTask(task);
        addTask(inverseTask);
    }

    /**
     * @param filename
     */
    public
    BidiTask ( String filename,
               EPartitionScheme scheme,
               ICodec <N, A, G> codec,
               Task <N, A, G> task,
               Task <N, A, G> inverseTask ) {

        this(filename, scheme, codec, List.of(task, inverseTask));

        this.task = task;
        this.inverseTask = inverseTask;
    }

    /**
     * @param filename
     * @param codec
     * @param encodeTask
     * @param decodeTask
     */
    public
    BidiTask ( String filename,
               ICodec<N, A, G> codec,
               EncodeTask <N, A, G> encodeTask,
               DecodeTask <N, A, G> decodeTask ) {

        this(filename, FIXED_SIZE, codec, encodeTask, decodeTask);
    }


    /**
     * @return
     */
    public
    Task <N, A, G> getTask () {
        return task;
    }

    /**
     * @return
     */
    public
    Task <N, A, G> getInverseTask () {
        return inverseTask;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    IImage<A> execute ( String filename ) throws ValueError {

        return super.execute(filename);
    }
}