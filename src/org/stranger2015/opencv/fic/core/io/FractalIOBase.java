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
     * @param fractalModel
     */
    protected
    FractalIOBase ( String fileName, FCImageModel fractalModel ) {
        this.fractalModel = fractalModel;
        this.fileName = fileName;
    }

    /**
     * @return
     */
    public
    String getFileName () {
        return fileName;
    }
}
