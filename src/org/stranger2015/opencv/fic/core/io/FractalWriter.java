package org.stranger2015.opencv.fic.core.io;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.ImageInfo;
import org.stranger2015.opencv.fic.core.ValueError;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;

import static java.lang.Math.ceil;
import static java.nio.file.Files.newOutputStream;
import static org.stranger2015.opencv.fic.core.FCImageModel.LABEL;
import static org.stranger2015.opencv.fic.core.search.ExhaustiveSearchProcessor.ifsRecordLength;
import static org.stranger2015.opencv.utils.BitBuffer.allocate;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class FractalWriter extends FractalIOBase {

    /**
     * Constructs a {@code FileWriter} given a file name, using the platform's
     * {@linkplain Charset#defaultCharset() default charset}
     *
     * @param fractalModel
     * @throws IOException if the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason
     */
    public
    FractalWriter ( String fileName, FCImageModel fractalModel ) throws IOException, ValueError {
        super(fileName, fractalModel);
    }

    /**
     * @param os
     * @throws IOException
     */
    private
    void writeLabel ( OutputStream os ) throws IOException {
        os.write(LABEL.getBytes().length);
    }

    /**
     * @param fileName
     * @param model
     */
    public
    void writeModel ( String fileName, FCImageModel model ) throws Exception {
        OutputStream fos;

        try (OutputStream os = new GZIPOutputStream(fos = newOutputStream(Path.of(fileName)))) {
            writeLabel(fos);
            int dataLength = (int) ceil(model.getRangeBlocks().size() * ifsRecordLength / 8.0);
            bitBuffer = allocate(dataLength);
            model.setIfsRecords(bitBuffer.getBytes().array());
            byte[] ifsRecords = model.getIfsRecords();
            writeData(os, ifsRecords);
        }
    }

    private
    void writeData ( OutputStream os, byte[] ifsRecords ) throws IOException {
        writeInt(os, ifsRecords.length);
        writeImageInfo(os);
        os.write(ifsRecords);
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
    public
    void write ( OutputStream os, int b ) throws IOException {
        oneByte[0] = (byte) (b & BYTE_MASK);
        os.write(oneByte);
    }

    /**
     * Writes an <code>int</code> to the underlying output stream as four
     * bytes, high byte first. If no exception is thrown, the counter
     * <code>written</code> is incremented by <code>4</code>.
     *
     * @param v an <code>int</code> to be written.
     * @throws IOException if an I/O error occurs.
     * @see java.io.FilterOutputStream#
     */
    public final
    void writeInt ( OutputStream os, int v ) throws IOException {
        os.write((v >>> 24) & 0xFF);
        os.write((v >>> 16) & 0xFF);
        os.write((v >>> 8) & 0xFF);
        os.write((v) & 0xFF);
//        incCount(4);
    }

    /**
     * @param os
     * @throws IOException
     */
    public
    void writeImageInfo ( OutputStream os ) throws IOException {
        ImageInfo imageInfo = fractalModel.getImageInfo();

        writeInt(os, imageInfo.width);
        writeInt(os, imageInfo.height);

        writeInt(os, imageInfo.getOriginalImageWidth());
        writeInt(os, imageInfo.getOriginalImageHeight());

        writeInt(os, imageInfo.getOriginalColorSpace().ordinal());
    }
}
