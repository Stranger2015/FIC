package org.stranger2015.opencv.fic.core.codec;

/**
 *
 */
public
interface IConstants {
    int[][] dihedralAffineTransforms = {
            {1, 0, 0, 1},
            {1, 0, 0, -1},
            {-1, 0, 0, 1},
            {-1, 0, 0, -1},
            {0, 1, 1, 0},
            {0, 1, -1, 0},
            {0, -1, 1, 0},
            {0, -1, -1, 0}
    };

    String ENCODER_VERSION = "1";
    double MAX_PIXEL_CHANNEL_VALUE = 255;

    int NUMBER_OF_DECODER_ITERATIONS = 1;
}
