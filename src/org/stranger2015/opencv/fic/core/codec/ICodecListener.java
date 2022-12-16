package org.stranger2015.opencv.fic.core.codec;

/**
 *
 */
public
interface ICodecListener extends IListener <ICodec > {

    /**
     *
     */
    default
    void onCodecCreated ( ICodec codec ) {
        onCreated(codec);
    }
}
