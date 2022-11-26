package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.IImageLine;
import ar.com.hjg.pngj.IImageLineSet;
import ar.com.hjg.pngj.PngReader;
import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import static org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class FractalReader<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
       /* extends PngReader*/ {

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
        /*super(inputStream);*/
    }

    /**
     * Same as  but allows to specify early if the stream must be closed
     *
     * @param inputStream
     * @param shouldCloseStream The stream will be closed in case of exception (constructor included) or normal
     */
    public
    FractalReader ( InputStream inputStream, boolean shouldCloseStream ) {
        /*super(inputStream, shouldCloseStream);*/
    }

    /**
     * Constructs a PngReader opening a file. Sets <tt>shouldCloseStream=true</tt>, so that the stream will be closed with
     * this object.
     *
     * @param file PNG image file
     */
    public
    FractalReader ( File file ) {
       // super(file);
    }

    /**
     *
     *
     * @return
     */
    public
    FCImageModel <N, A, G> readModel () throws ValueError {
        FCImageModel <N, A, G> model = new FCImageModel <>(/*new HashMap <>()*/);

//        IImageLineSet <? extends IImageLine> rows = readRows();///*/*/*/**/*/*/*/
        for (int i = 0; i < rows.size(); i++) {
            IImageLine line = rows.getImageLine(i);
            byte[] raw = new byte[0];
            int len = 0;
            int offset = 0;
            int step = 0;
            line.readFromPngRaw(raw, len, offset, step);
        }

        return model;
    }
}
