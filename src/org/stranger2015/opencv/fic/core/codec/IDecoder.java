package org.stranger2015.opencv.fic.core.codec;
//
//import org.stranger2015.opencv.fic.core.FCImageModel;
//import org.stranger2015.opencv.fic.core.IImage;
//import org.stranger2015.opencv.fic.core.ValueError;
//import org.stranger2015.opencv.fic.transform.ImageTransform;
//
//import java.io.IOException;
//

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;

import java.io.IOException;

/**
 *
 */
public
interface IDecoder extends ICodecListener, IConstants {
    /**
     *
     */
    IImage decode ( String fileName ) throws ValueError, IOException;

    /**
     * @param filename
     * @param fractalModel
     */
    void saveModel ( String filename, FCImageModel fractalModel ) throws Exception;

}
