package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IImageBlock;

import java.nio.ByteBuffer;

/**
 *
 */
public
class ClassificationScheme {
private ByteBuffer buffer;
    /**
     *
     */
    public
    enum RelativeOrdering {
        MAJOR_CLASS_1,//A1 ≥ A2 ≥ A3 ≥ A4
        MAJOR_CLASS_2,//A1 ≥ A2 ≥ A4 ≥ A3
        MAJOR_CLASS_3,//A1 ≥ A4 ≥ A2 ≥ A3
    }

private final ImageBlockInfo blockInfo;

    /**
     * @param image
     * @param x
     * @param y
     * @param sideSize
     * @param geometry
     * @param block
     */
    public
    ClassificationScheme ( ImageBlockInfo blockInfo, IImageBlock block, ByteBuffer buffer) {
        this.blockInfo = blockInfo;
        this.buffer = buffer;
    }

    /**
     * @return
     */
    public
    ImageBlockInfo getBlockInfo () {
        return blockInfo;
    }
}

