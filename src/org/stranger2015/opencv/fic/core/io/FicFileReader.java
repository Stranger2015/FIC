package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.PngReader;
import ar.com.hjg.pngj.chunks.ChunkRaw;
import org.stranger2015.opencv.fic.core.codec.EAddressKind;

import java.io.File;
import java.io.InputStream;

/**
 *
 */
public
class FicFileReader extends PngReader {

    /**
     * Constructs a PngReader object from a stream, with default options. This reads the signature and the first IHDR
     * chunk only.
     * <p>
     * Warning: In case of exception the stream is NOT closed.
     * <p>
     * Warning: By default the stream will be closed when this object is {@link #close()}d. See
     * {@link #PngReader(InputStream, boolean)} or {@link #setShouldCloseStream(boolean)}
     * <p>
     *
     * @param inputStream PNG stream
     */
    public
    FicFileReader ( InputStream inputStream ) {
        super(inputStream);
    }

    /**
     * Same as {@link #PngReader(InputStream)} but allows to specify early if the stream must be closed
     *
     * @param inputStream
     * @param shouldCloseStream The stream will be closed in case of exception (constructor included) or normal
     */
    public
    FicFileReader ( InputStream inputStream, boolean shouldCloseStream ) {
        super(inputStream, shouldCloseStream);
    }

    /**
     * Constructs a PngReader opening a file. Sets <tt>shouldCloseStream=true</tt>, so that the stream will be closed with
     * this object.
     *
     * @param file PNG image file
     */
    public
    FicFileReader ( File file ) {
        super(file);
    }

    public
    EAddressKind readAddressType(){

        return EAddressKind.ORDINARY;
    }

    private ChunkRaw readFicData(){

        return null;
    }
}
