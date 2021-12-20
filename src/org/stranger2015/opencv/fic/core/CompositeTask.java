package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param <M>
 */
public abstract
class CompositeTask<M extends Image> extends Task <M> {
    protected final List <Task <M>> tasks = new ArrayList <>();

    /**
     * @param tasks
     */
    public
    CompositeTask ( M image, List <Task <M>> tasks ) {
        super(image);

        this.tasks.addAll(tasks);
    }

    /**
     * @param image
     * @param tasks
     */
    @SafeVarargs
    CompositeTask ( M image, Task <M>... tasks ) {
        this(image, Arrays.asList(tasks));
    }

    /**
     * @param image
     */
    public
    CompositeTask ( M image ) {
        super(image);
    }

    /**
     * @param tasks
     */
    public
    CompositeTask ( List <Task <M>> tasks ) {
        this(null, tasks);
    }

    /**
     * @return
     */
    public
    List <Task <M>> getTasks () {
        return tasks;
    }

    /**
     * @param task
     */
    void addTask ( Task <M> task ) {
        tasks.add(task);
    }
}
