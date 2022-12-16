package org.stranger2015.opencv.fic.core.io;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipOutputStream;

/**
 *
 */
public
class FCICompressedOutputStream extends ZipOutputStream {
    /**
     * Creates a new ZIP output stream.
     *
     * <p>The UTF-8 {@link Charset charset} is used
     * to encode the entry names and comments.
     *
     * @param out the actual output stream
     */
    public
    FCICompressedOutputStream ( @NotNull OutputStream out ) {
        super(out);
    }

    /**
     * Writes the specified byte to this output stream. The general
     * contract for <code>write</code> is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument <code>b</code>. The 24
     * high-order bits of <code>b</code> are ignored.
     * <p>
     * Subclasses of <code>OutputStream</code> must provide an
     * implementation for this method.
     *
     * @param b the <code>byte</code>.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an <code>IOException</code> may be thrown if the
     *                     output stream has been closed.
     */
    @Override
    public
    void write ( int b ) throws IOException {

    }
}
