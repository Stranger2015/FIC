package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifiable;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifier;
import org.stranger2015.opencv.fic.core.codec.classifiers.ImageBlockClassifier;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlockInfo;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;

import java.io.IOException;
import java.util.Set;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public abstract
class PartitionProcessor implements IPartitionProcessor {

    protected ITiler tiler;
    protected ImageBlockGenerator <?> imageBlockGenerator;
    protected ITreeNodeBuilder <?> nodeBuilder;
    private IClassifier classifier;

    /**
     * @param tiler
     * @param imageBlockGenerator
     */
    protected
    PartitionProcessor ( ITiler tiler,
                         ImageBlockGenerator <?> imageBlockGenerator,
                         ITreeNodeBuilder <?> nodeBuilder ) {

        this.tiler = tiler;
        this.imageBlockGenerator = imageBlockGenerator;
        this.nodeBuilder = nodeBuilder;
    }

    public
    PartitionProcessor ( ITiler tiler ) {
        this.tiler = tiler;
    }

    /**
     * @param classifier
     */
    @Override
    public
    void setClassifier ( IClassifier classifier ) {
        this.classifier = classifier;
    }

    /**
     * @return
     */
    @Override
    public
    IClassifier getClassifier () {
        return classifier;
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     * @return
     */
    @Override
    public abstract
    IPartitionProcessor instance ( ITiler tiler,
                                   ImageBlockGenerator <?> imageBlockGenerator,
                                   ITreeNodeBuilder <?> nodeBuilder );

    /**
     * @return
     */
    @Override
    public
    ITiler getTiler () {
        return tiler;
    }

    /**
     * @param rangeBlocks
     * @param domainBlocks
     * @return
     */
    @Override
    public
    Set <IClassifiable> classify ( Pool <IImageBlock> rangeBlocks, Pool <IImageBlock> domainBlocks, ImageBlockInfo[] level2Classes ) throws IOException {

        IClassifier classifier =new ImageBlockClassifier(level2Classes );

        return classifier.classify();

    }

    /**
     * @return
     */
    public
    ITreeNodeBuilder <?> getNodeBuilder () {
        return nodeBuilder;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <?> getImageBlockGenerator () {
        return imageBlockGenerator;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    IImage preprocess ( String filename ) {
        return null;
    }

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    @Override
    public
    IImage preprocess ( String filename, IImage inputImage ) throws ValueError {
        return inputImage;
    }

    /**
     * @param inputImage
     * @return
     */
    @Override
    public
    IImage process ( IImage inputImage ) throws Exception {
        return inputImage;
    }

    /**
     * @param base
     * @param w
     * @param h
     * @return
     */
    @Override
    public
    IntSize adjustSize ( int base, int w, int h ) {
        return null;
    }
}
