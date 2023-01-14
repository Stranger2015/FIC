package org.stranger2015.opencv.fic.core.codec.tilers;

import java.lang.ref.SoftReference;

public
class SrImageBlockInfo extends SoftReference<ImageBlockInfo[]> {
    /**
     * Creates a new soft reference that refers to the given object.  The new
     * reference is not registered with any queue.
     *
     * @param referent object the new soft reference will refer to
     */
    public
    SrImageBlockInfo ( ImageBlockInfo[] referent ) {
        super(referent);
    }
}
