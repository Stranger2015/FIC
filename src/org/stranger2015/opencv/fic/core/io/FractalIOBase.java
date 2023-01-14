package org.stranger2015.opencv.fic.core.io;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public abstract
class FractalIOBase {
    protected final byte[] oneByte = new byte[1];
    static final int BYTE_MASK = 0xFF;

    protected BitBuffer bitBuffer;
    protected final String fileName;
    /**
     *
     */
    protected FCImageModel fractalModel;

    /**
     *
     */
    protected
    FractalIOBase ( String fileName ) {
        this.fileName = fileName;
    }

    /**
     * @param fileName
     * @param fractalModel
     */
    public
    FractalIOBase ( String fileName, FCImageModel fractalModel ) {
        this.fileName = fileName;
        this.fractalModel = fractalModel;
    }

    /**
     * @return
     */
    public
    String getFileName () {
        return fileName;
    }
}
