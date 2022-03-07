package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public abstract
class BidiTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends Task <N, A, M, G> {

    protected Task <N, A, M, G> task;
    protected Task <N, A, M, G> inverseTask;

    /**
     * @param fn
     * @param scheme
     * @param tasks
     */
    @SafeVarargs
    BidiTask ( String fn, EPartitionScheme scheme, Task <N, A, M, G>... tasks ) {
        this(fn, scheme, Arrays.asList(tasks));
    }

    /**
     * @param fn
     * @param tasks
     */
    public
    BidiTask ( String fn, EPartitionScheme scheme, List <Task <N, A, M, G>> tasks ) {
        super(fn, scheme, tasks);

        if (tasks.isEmpty()) {
            return;
        }

        this.task = tasks.get(0);
        this.inverseTask = tasks.get(1);

        addTask(task);
        addTask(inverseTask);
    }

    /**
     * @param fn
     */
    public
    BidiTask ( String fn, EPartitionScheme scheme, Task <N, A, M, G> task, Task <N, A, M, G> inverseTask ) {
        this(fn, scheme, List.of(task, inverseTask));

        this.task = task;
        this.inverseTask = inverseTask;
    }

    /**
     * @return
     */
    public
    Task <N, A, M, G> getTask () {
        return task;
    }

    /**
     * @return
     */
    public
    Task <N, A, M, G> getInverseTask () {
        return inverseTask;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    M execute ( String filename ) throws ValueError {

        return super.execute(filename);
    }
}