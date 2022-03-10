package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.IImageLine;
import ar.com.hjg.pngj.IImageLineSet;
import ar.com.hjg.pngj.PngReader;
import org.stranger2015.opencv.fic.core.FractalModel;

import java.io.File;
import java.io.InputStream;

public
class FractalReader extends PngReader {
    /**
     * Constructs a PngReader object from a stream, with default options. This reads the signature and the first IHDR
     * chunk only.
     * <p>
     * Warning: In case of exception the stream is NOT closed.
     * <p>
     * Warning: By default the stream will be closed when this object is {@link #close()}d. See
     * PngReader or {@link #setShouldCloseStream(boolean)}
     * <p>
     *
     * @param inputStream PNG stream
     */
    public
    FractalReader ( InputStream inputStream ) {
        super(inputStream);
    }

    /**
     * Same as  but allows to specify early if the stream must be closed
     *
     * @param inputStream
     * @param shouldCloseStream The stream will be closed in case of exception (constructor included) or normal
     */
    public
    FractalReader ( InputStream inputStream, boolean shouldCloseStream ) {
        super(inputStream, shouldCloseStream);
    }

    /**
     * Constructs a PngReader opening a file. Sets <tt>shouldCloseStream=true</tt>, so that the stream will be closed with
     * this object.
     *
     * @param file PNG image file
     */
    public
    FractalReader ( File file ) {
        super(file);
    }

    FractalModel<?,?,?,?> readModel(){
        FractalModel <?, ?, ?, ?> model;

        readFirstChunks();
        IImageLineSet <? extends IImageLine> rows = readRows();

        return model;
    }
}
