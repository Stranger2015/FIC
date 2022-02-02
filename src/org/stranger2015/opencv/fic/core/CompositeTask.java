package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public abstract
class CompositeTask extends Task {
    protected final List <Task> tasks = new ArrayList <>();

    /**
     * @param tasks
     */
    @SafeVarargs
    CompositeTask ( String fn, Task... tasks ) {
        this(fn, asList(tasks));
    }

    /**
     * @param tasks
     */
    public
    CompositeTask ( String fn, List <Task> tasks ) {
        this(fn);
        this.tasks.addAll(tasks);
    }

    /**
     * @param filename
     */
    protected
    CompositeTask ( String filename ) {
        super(filename);
    }

    /**
     * @return
     */
    public
    List <Task> getTasks () {
        return tasks;
    }

    /**
     * @param task
     */
    public
    void addTask ( Task task ) {
        tasks.add(task);
    }
}
