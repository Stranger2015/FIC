package org.stranger2015.opencv.fic.core;

@Deprecated
public
interface IDigits9 extends IDigits7 {
    @Override
    default
    int base () {
        return 9;
    }

    int seven ();

    int eight ();
}
