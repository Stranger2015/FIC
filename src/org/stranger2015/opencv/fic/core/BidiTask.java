package org.stranger2015.opencv.fic.core;

import java.util.Arrays;
import java.util.List;

/**
 * @param <M>
 */
public abstract
class BidiTask<M extends Image> extends CompositeTask <M> {
    private final Task <M> task;
    private final Task <M> inverseTask;

    /**
     * @param tasks
     */
    public
    BidiTask ( List <Task <M>> tasks ) {
        this(null, tasks);
           }

    /**
     * @param tasks
     */
    @SafeVarargs
    BidiTask (Task <M>... tasks ) {
        this(Arrays.asList(tasks));
    }

    /**
     * @param image
     * @param tasks
     */
    public
    BidiTask ( M image, List <Task <M>> tasks ) {
        super(image, tasks);
        this.task = tasks.get(0);
        this.inverseTask = tasks.get(1);
        addTask(task);
        addTask(inverseTask);

    }

    /**
     * @return
     */
    public
    Task <M> getTask () {
        return task;
    }

    /**
     * @return
     */
    public
    Task <M> getInverseTask () {
        return inverseTask;
    }

    /**
     * @param fn
     * @return
     */
    public abstract
    M loadImage ( String fn );

    /**
     * @param fn
     * @param image
     */
    public abstract
    void saveImage ( String fn, M image );
}
