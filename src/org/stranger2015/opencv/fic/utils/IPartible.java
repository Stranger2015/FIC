package org.stranger2015.opencv.fic.utils;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.ValueError;

public
interface IPartible<A extends Address <A>> {
    A divide () throws ValueError;
}
