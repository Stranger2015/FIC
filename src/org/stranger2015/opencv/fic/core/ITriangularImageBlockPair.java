package org.stranger2015.opencv.fic.core;

@Deprecated
public
interface ITriangularImageBlockPair<A extends IAddress <A>> extends IImageBlock <A> {

 /**
  * @return
  */
 @Override
 int getMeanPixelValue ();

 /**
  * @return
  */
IImageBlock<A> getBlock1();

 /**
  * @return
  */
 IImageBlock<A> getBlock2();

 /**
  * @return
  */
boolean isBlockHomogenous(IImageBlock<A> block);

}
