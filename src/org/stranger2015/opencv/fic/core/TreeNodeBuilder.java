package org.stranger2015.opencv.fic.core;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public abstract
class TreeNodeBuilder<N extends TreeNode <N>>
        implements ITreeNodeBuilder <N> {

    /**
     * maps block coordinate (point(x,y)) to image blk instance
     */
    protected final Map <Point, IImageBlock > mapBlockCoordToBlock = new DualHashBidiMap <>();

    protected final List <IImageBlock > blockList = new ArrayList <>();

    public final IImage image;
    private final IIntSize rangeSize;
    private final IIntSize domainSize;
    private final IEncoder encoder;

    public final Library  library;

    protected TreeNode <N> lastNode;

    public
    TreeNodeBase <N> getLastNodeBase () {
        return lastNodeBase;
    }

    protected TreeNodeBase <N> lastNodeBase;
    protected LeafNode <N> lastLeafNode;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param library
     */
    protected
    TreeNodeBuilder ( IImage image,
                      IIntSize rangeSize,
                      IIntSize domainSize,
                      IEncoder encoder,
                      Library  library ) {

        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
        this.encoder = encoder;
        this.library = library;
    }

    /**
     * @return
     */
    public
    Library  getLibrary () {
        return library;
    }

    /**
     * @return
     */
    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    /**
     * @return
     */
    public
    IIntSize getDomainSize () {
        return domainSize;
    }

    /**
     * @return
     */
    public
    IEncoder getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public abstract
    ITreeNodeBuilder <N> newInstance ();
}
