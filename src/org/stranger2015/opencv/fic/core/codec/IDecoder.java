package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;

/**
 *
 */
public
interface IDecoder extends ICodecListener, IConstants {
    /**
     *
     */
    IImage decode ( String fileName ) throws Exception;

    /**
     * @param filename
     * @param fractalModel
     */
    void saveModel ( String filename, FCImageModel fractalModel ) throws Exception;

}
