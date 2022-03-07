package org.stranger2015.opencv.fic.core.codec;

/**
 *
 */
public
interface ICodecListener {
   /**
    *
    */
   void onCreateCodec ();

   /**
    *
    */
   void onEncode();

   /**
    *
    */
   void onDecode();
}
