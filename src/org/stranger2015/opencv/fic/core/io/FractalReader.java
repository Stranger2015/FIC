package org.stranger2015.opencv.fic.core.io;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.ImageInfo;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static java.nio.file.Files.newInputStream;
import static org.stranger2015.opencv.fic.core.FCImageModel.LABEL;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class FractalReader extends FractalIOBase {
    private final List <ImageTransform> transforms = new ArrayList <>();

    /**
     * Constructs a Reader object from a stream, with default options
     * <p>
     * Warning: In case of exception the stream is NOT closed.
     * <p>
     * Warning: By default the stream will be closed when this object is {@link #close()}d. See
     * Reader or {@link #setShouldCloseStream(boolean)}
     * <p>
     *
     * @param inputStream stream
     */
    /**
     * Creates a new {@code FileReader}, given the name of the file to read,
     * using the platform's
     * {@linkplain Charset#defaultCharset() default charset}.
     *
     * @param fileName the name of the file to read
     * @throws FileNotFoundException if the named file does not exist,
     *                               is a directory rather than a regular file,
     *                               or for some other reason cannot be opened for
     *                               reading.
     */
    public
    FractalReader ( String fileName, FCImageModel model ) throws IOException, ValueError {
        super(fileName, model);
    }

    /**
     * @return
     */
    public
    FCImageModel readModel ( String fileName ) throws Exception {
        InputStream fis;
        try (InputStream is = new GZIPInputStream(fis = newInputStream(Path.of(fileName)))) {
            readLabel(fis);
            byte[] ifsRecords = readData(is);
            fractalModel = new FCImageModel(ifsRecords);

            return fractalModel;
        }
    }

    /**
     * See the general contract of the <code>readInt</code>
     * method of <code>DataInput</code>.
     * <p>
     * Bytes
     * for this operation are read from the contained
     * input stream.
     *
     * @return the next four bytes of this input stream, interpreted as an
     * <code>int</code>.
     * @throws EOFException if this input stream reaches the end before
     *                      reading four bytes.
     * @throws IOException  the stream has been closed and the contained
     *                      input stream does not support reading after close, or
     *                      another I/O error occurs.
     * @see java.io.FilterInputStream#in
     */
    public final
    int readInt ( InputStream is ) throws IOException {
        int ch1 = read(is);
        int ch2 = read(is);
        int ch3 = read(is);
        int ch4 = read(is);

        if ((ch1 | ch2 | ch3 | ch4) < 0) {
            throw new EOFException();
        }

        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4);
    }

    /**
     * @throws IOException
     */
    void readLabel ( InputStream is ) throws IOException {
        int rc = is.read(LABEL.getBytes());
        if (rc != LABEL.getBytes().length) {
            throw new IllegalStateException();
        }
    }

    /**
     *
     */
    public
    byte[] readData ( InputStream is ) throws Exception {
        int dataLength = readInt(is);
        fractalModel.setImageInfo( readImageInfo(is));
        byte[] rawBytes = is.readNBytes(dataLength);

        bitBuffer.getBytes().put(rawBytes);
//        for (int i = 0; i < dataLength / ifsRecordLength; i++) {
//            ImageTransform transform = bitBuffer.readIfsCodeRecord(imageInfo.getWidth());
//            transforms.add(transform);
//        }

        return rawBytes;
    }

    /**
     * @param is
     * @returnnD
     * @throws IOException
     */
    private
    ImageInfo readImageInfo ( InputStream is ) throws IOException {
        int w = readInt(is);
        int h = readInt(is);
        int origW = readInt(is);
        int origH = readInt(is);

        EtvColorSpace origColorSpace = EtvColorSpace.values()[readInt(is)];

        return new ImageInfo(w, h, origW, origH, origColorSpace);
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
    public
    int read ( InputStream is ) throws IOException {
        return is.read(this.oneByte);
    }

    /**
     * @param bitBuffer
     */
    public
    void setBitBuffer ( BitBuffer bitBuffer ) {
        this.bitBuffer = bitBuffer;
    }
}