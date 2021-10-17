package org.stranger2015.opencv.fic.core;

import org.opencv.core.Scalar;

public
enum Color {
    BLACK(new Scalar(0.0 / 255, 0.0 / 255, 0.0 / 255, 1.0)),
    WHITE(new Scalar(1.0 / 255, 1.0 / 255, 1.0 / 255, 1.0)),
    ;

    private final Scalar data;

    Color ( Scalar data ) {
        this.data = data;
    }

    public
    Scalar getData () {
        return data;
    }

}