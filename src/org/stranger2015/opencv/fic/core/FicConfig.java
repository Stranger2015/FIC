package org.stranger2015.opencv.fic.core;

import org.kohsuke.args4j.Config;
import org.stranger2015.opencv.fic.core.FicApplication.ECommands;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.io.File;
import java.util.List;

/**
 * @param <N> \
 * @param
 * @param <G>
 */
public
class FicConfig extends Config {
    public IDistanceator distanceator;

    public ICodec codec;
    private File input;
    private EtvColorSpace colorSpace;
    private EPartitionScheme partitionScheme;
    private File output;
    private FCImageModel fractalModel;
    private ECommands command;
    private List <Task> tasks;
//todo fuzz
    /**
     * @param args
     */
    public
    FicConfig ( String[] args ) {

    }

    /**
     * @return
     */
    public
    EtvColorSpace colorSpace () {
        return colorSpace;
    }

    /**
     * @return
     */
    public
    EPartitionScheme partitionScheme () {
        return partitionScheme;
    }

    /**
     * @return
     */
    public
    File getInput () {
        return input;
    }

    /**
     * @return
     */
    public
    File getOutput () {
        return output;
    }

    /**
     * @return
     */
    public
    FCImageModel getFractalModel () {
        return fractalModel;
    }

    /**
     * @return
     */
    public
    ECommands getCommand () {
        return command;
    }

    /**
     * @param colorSpace
     */
    public
    void setColorSpace ( EtvColorSpace colorSpace ) {
        this.colorSpace = colorSpace;
    }

    /**
     * @param partitionScheme
     */
    public
    void setPartitionScheme ( EPartitionScheme partitionScheme ) {
        this.partitionScheme = partitionScheme;
    }

    /**
     * @param output
     */
    public
    void setOutput ( File output ) {
        this.output = output;
    }

    /**
     * @param fractalModel
     */
    public
    void setFractalModel ( FCImageModel fractalModel ) {
        this.fractalModel = fractalModel;
    }

    /**
     * @param command
     */
    public
    void setCommand ( ECommands command ) {
        this.command = command;
    }

    /**
     * @param input
     */
    public
    void setInput ( File input ) {
        this.input = input;
    }

    /**
     * @return
     */
    public
    List <Task> tasks () {
        return tasks;
    }

    /**
     * @param tasks
     */
    public
    void setTasks ( List <Task> tasks ) {
        this.tasks = tasks;
    }
}
