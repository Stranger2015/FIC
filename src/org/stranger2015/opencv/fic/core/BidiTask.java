package org.stranger2015.opencv.fic.core;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public abstract
class BidiTask<N extends TreeNode <N>, M extends Image> extends CompositeTask <N, M> {
    private final Task <N, M> task;
    private final Task <N, M> inverseTask;

    /**
     * @param tasks
     */
    public
    BidiTask ( List<Task <N, M>> tasks ) {
        super(tasks);
        this.task = tasks.get(0);
        this.inverseTask = tasks.get(1);
        addTask(task);
        addTask(inverseTask);
    }

    /**
     * @param image
     */
    @Override
    public abstract
    M execute ( M image );

    /**
     *
     * @return
     */
    public
    Task <N, M> getTask () {
        return task;
    }

    /**
     *
     * @return
     */
    public
    Task <N, M> getInverseTask () {
        return inverseTask;
    }
}
