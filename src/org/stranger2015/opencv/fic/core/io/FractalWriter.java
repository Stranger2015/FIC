package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.PngWriter;
import ar.com.hjg.pngj.PngjOutputException;
import org.stranger2015.opencv.fic.core.FractalModel;

import java.io.*;

public
class FractalWriter extends PngWriter {

    /**
     * Opens a file for writing.
     * <p>
     * Sets shouldCloseStream=true. For more info see {@link #PngWriter(OutputStream, ImageInfo)}
     *
     * @param file
     * @param imgInfo
     * @param allowoverwrite If false and file exists, an {@link PngjOutputException} is thrown
     */
    public
    FractalWriter ( File file, ImageInfo imgInfo, boolean allowoverwrite ) {
        super(file, imgInfo, allowoverwrite);
    }

    /**
     * @param file
     * @param imgInfo
     * @see #PngWriter(File, ImageInfo, boolean) (overwrite=true)
     */
    public
    FractalWriter ( File file, ImageInfo imgInfo ) {
        super(file, imgInfo);
    }

    /**
     * Constructs a new PngWriter from a output stream. After construction nothing is writen yet. You still can set some
     * parameters (compression, filters) and queue chunks before start writing the pixels.
     * <p>
     *
     * @param outputStream Open stream for binary writing
     * @param imgInfo      Basic image parameters
     */
    public
    FractalWriter ( OutputStream outputStream, ImageInfo imgInfo ) {
        super(outputStream, imgInfo);
    }


    public
    void write ( FractalModel <?, ?, ?, ?> fractalModel ) {

    }

}
