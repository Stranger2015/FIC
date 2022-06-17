package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ISingleLinked;

public
interface IPipeline<I, O> extends ISingleLinked<IPipeline<I,O>> {
    @Override
    ISingleLinked <IPipeline <I, O>> getNext ();
    I getInput();
    O getOutput();
}
