package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.DecodeTask;
import org.stranger2015.opencv.fic.core.codec.EncodeTask;
import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.Arrays;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.FIXED_SIZE;

/**
 *
 */
public abstract
class BidiTask extends Task {

    protected Task task;
    protected Task inverseTask;

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    @SafeVarargs
    BidiTask ( String filename,
               EPartitionScheme scheme,
               ICodec codec,
               Task... tasks ) {

        this(filename, scheme, codec, Arrays.asList(tasks));
    }

    /**
     * @param filename
     * @param tasks
     */
    public
    BidiTask ( String filename,
               EPartitionScheme scheme,
               ICodec codec,
               List <Task> tasks ) {

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
               ICodec codec,
               Task task,
               Task inverseTask ) {
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
               ICodec codec,
               EncodeTask encodeTask,
               DecodeTask decodeTask ) {

        this(filename, FIXED_SIZE, codec, encodeTask, decodeTask);
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
     * @param filename
     * @return
     */
    @Override
    protected
    IImage execute ( String filename ) throws Exception {
        return super.execute(filename);
    }
}