package org.stranger2015.opencv.fic.core;

import org.kohsuke.args4j.Config;
import org.stranger2015.opencv.fic.core.FicApplication.ECommands;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
/**
 * @param <N> \
 * @param <A>
 * @param <G>
 */
public
class FicConfig<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Config {
    public IDistanceator <A> distanceator;
    private File input;
    private EtvColorSpace colorSpace;
    private EPartitionScheme partitionScheme;
    private File output;
    private FCImageModel <N, A, G> fractalModel;
    private  ECommands command;

    /**
     * @param args
     */
    public
    FicConfig ( String[] args){

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
}
