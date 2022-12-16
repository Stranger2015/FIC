package org.stranger2015.opencv.fic.core.io;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

/**
 *
 */
public
class FCICompressedInputStream extends ZipInputStream {
    /**
     * Creates a new ZIP input stream.
     *
     * <p>The UTF-8 {@link Charset charset} is used to
     * decode the entry names.
     *
     * @param in the actual input stream
     */
    public
    FCICompressedInputStream ( @NotNull InputStream in ) {
        super(in);
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an <code>int</code> in the range <code>0</code> to
     * <code>255</code>. If no byte is available because the end of the stream
     * has been reached, the value <code>-1</code> is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or <code>-1</code> if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public
    int read () throws IOException {
        return 0;
    }
}
