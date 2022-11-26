package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.PngjOutputException;
import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class FractalWriter<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
      /*  extends PngWriter */{

    /**
     * Opens a file for writing.
     * <p>
     * Sets shouldCloseStream=true. For more info see
     *
     * @param file
     * @param imgInfo
     * @param allowOverwrite If false and file exists, an {@link PngjOutputException} is thrown
     */
    public
    FractalWriter ( File file, ImageInfo imgInfo, boolean allowOverwrite ) {
//        super/*(file, imgInfo, allowOverwrite)*/;
    }

    /**
     * @param file
     * @param imgInfo
     */
    public
    FractalWriter ( File file, ImageInfo imgInfo ) {
//        super(file, imgInfo);
    }

    /**
     * @param model
     */
    public
    void writeModel ( FCImageModel <N, A, G> model ) {

    }
}
