package org.stranger2015.opencv.fic.core;

@Deprecated
public
interface ITriangularImageBlockPair<A extends IAddress > extends IImageBlock  {

 /**
  * @return
  */
 @Override
 double[] getMeanPixelValue ();

 /**
  * @return
  */
IImageBlock getBlock1();

 /**
  * @return
  */
 IImageBlock getBlock2();

 /**
  * @return
  */
boolean isBlockHomogenous(IImageBlock block);

}
