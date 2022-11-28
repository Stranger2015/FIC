package org.stranger2015.opencv.fic.core;

import org.kohsuke.args4j.Config;
import org.stranger2015.opencv.fic.core.FicApplication.ECommands;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.List;

/**
 * @param <N> \
 * @param <A>
 * @param <G>
 */
public
class FicConfig<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Config {
    public IDistanceator <A> distanceator;

    public ICodec <N, A, G> codec;
    private File input;
    private EtvColorSpace colorSpace;
    private EPartitionScheme partitionScheme;
    private File output;
    private FCImageModel <N, A, G> fractalModel;
    private ECommands command;
    private List <Task <N, A, G>> tasks;

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
    FCImageModel <N, A, G> getFractalModel () {
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
    void setFractalModel ( FCImageModel <N, A, G> fractalModel ) {
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
    List <Task <N, A, G>> tasks () {
        return tasks;
    }

    /**
     * @param tasks
     */
    public
    void setTasks ( List <Task <N, A, G>> tasks ) {
        this.tasks = tasks;
    }
}
