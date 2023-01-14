package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;

/**
 *
 */
public
interface IConstants {
    int[][] dihedralAffineTransforms = {
            {1, 0, 0, 1},//identity
            {1, 0, 0, -1},//
            {-1, 0, 0, 1},//
            {-1, 0, 0, -1},//
            {0, 1, 1, 0},//
            {0, 1, -1, 0},//
            {0, -1, 1, 0},//
            {0, -1, -1, 0}//
    };
    Mat[] dihedralAffineTransformMatrices = {
            new MatOfInt(1, 0, 0, 1),
            new MatOfInt(1, 0, 0, -1),
            new MatOfInt(-1, 0, 0, 1),
            new MatOfInt(-1, 0, 0, -1),
            new MatOfInt(0, 1, 1, 0),
            new MatOfInt(0, 1, -1, 0),
            new MatOfInt(0, -1, 1, 0),
            new MatOfInt(0, -1, -1, 0)
    };

    //    String ENCODER_VERSION = "1";
    double MAX_PIXEL_CHANNEL_VALUE = 255;
    int NUMBER_OF_DECODER_ITERATIONS = 1;
}
