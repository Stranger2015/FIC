package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public abstract
class BidiTask extends CompositeTask {

    protected Task task;
    protected Task inverseTask;

    /**
     * @param tasks
     */
    public
    BidiTask ( List <Task> tasks ) {
        this(null, tasks);
    }

    /**
     * @param tasks
     */
    @SafeVarargs
    BidiTask ( Task... tasks ) {
        this(Arrays.asList(tasks));
    }

    /**
     * @param tasks
     */
    public
    BidiTask ( String fn, List <Task> tasks ) {
        super(fn, tasks);
        if (tasks.isEmpty()) {
           return;// throw new IllegalStateException("BidiTask: tasks must contain exactly two tasks");
        }
        this.task = tasks.get(0);
        this.inverseTask = tasks.get(1);

        addTask(task);
        addTask(inverseTask);
    }

    /**
     * @return
     */
    public
    Task getTask () {
        return task;
    }

    /**
     * @return
     */
    public
    Task getInverseTask () {
        return inverseTask;
    }

    /**
     * @param fn
     * @return
     */
    public abstract
    Image loadImage ( String fn );

    /**
     * @param fn
     * @return
     */
    public abstract
    SipImage loadSipImage ( String fn ) throws ValueError;

    /**
     * @param fn
     * @param image
     */
    public abstract
    void saveImage ( String fn, Image image );
}