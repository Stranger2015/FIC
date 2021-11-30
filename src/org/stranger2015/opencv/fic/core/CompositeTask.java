package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public abstract
class CompositeTask<N extends TreeNode <N>, M extends Image> extends Task <N, M> {

    protected final List <Task <N, M>> tasks = new ArrayList <>();

    /**
     * @param tasks
     */
    public
    CompositeTask ( List <Task <N, M>> tasks ) {
        this.tasks.addAll(tasks);
    }

    /**
     * @param image
     */
    @Override
    public abstract
    M execute ( M image );

    /**
     * @return
     */
    public
    List <Task <N, M>> getTasks () {
        return tasks;
    }

    /**
     * @param task
     */
    void addTask ( Task <N, M> task ) {
        tasks.add(task);
    }
}
