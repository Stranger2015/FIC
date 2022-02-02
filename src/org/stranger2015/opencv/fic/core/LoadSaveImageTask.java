package org.stranger2015.opencv.fic.core;

import org.opencv.imgcodecs.Imgcodecs;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.SipLibrary;

import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.*;

/**
 *
 */
public
class LoadSaveImageTask extends BidiTask {

    private EPartitionScheme scheme;
    private Image image;

    /**
     * @param fileName
     * @param tasks
     */
//    @SafeVarargs
    public
    LoadSaveImageTask ( String fileName, EPartitionScheme scheme, Task... tasks ) {
        super(fileName, List.of(tasks));

        this.scheme = scheme;
    }

    /**
     * @param fileName
     * @param tasks
     */
    public
    LoadSaveImageTask ( String fileName, EPartitionScheme scheme, List <Task> tasks ) {
//        this(fileName, scheme, tasks.get(0), tasks.get(1)); fix,ne
    }

    /**
     * @param fn
     * @return
     */
    @Override
    public
    Image loadImage ( String fn ) {
        return new Image( Imgcodecs.imread(fn));
    }

    /**
     * @param fn
     * @return
     * @throws ValueError
     */
    @Override
    public
    SipImage loadSipImage ( String fn ) throws ValueError {
        Image image = loadImage(fn);
        SipLibrary <?> sipLib = new SipLibrary <>();
        SipTreeNodeBuilder <?, ?, ?> builder = new SipTreeNodeBuilder <>(image);

        return sipLib.convertImageToSipImage(builder.buildTree(), image);
    }

    /**
     * @param fn
     * @param image
     */
    @Override
    public
    void saveImage ( String fn, Image image ) {
        Imgcodecs.imwrite(fn, image);
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {
        try {
            image = (scheme == SIP ? loadSipImage(s) : loadImage(s));

        } catch (ValueError valueError) {
            valueError.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    public
    Image getImage () {
        return image;
    }
}