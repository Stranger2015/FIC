package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.VsaTreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.Collections;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public
class SipEncoder<N extends VsaTreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
        extends VsaEncoder <N, A, M> {

    private SipImage inputImage;
    private SipImage outputImage;

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 3;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
        return null;//todo
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {
        super.onPreprocess();
    }

    /**
     *
     */
    @Override
    public
    void onProcess () {
        super.onProcess();
    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {
        super.onPostprocess();
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return super.encode(image);
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return Collections.emptyList(); //todo
    }

    @Override
    public
    M getInputImage () {
        return (M) inputImage;
    }

    @Override
    public
    M getOutputImage () {
        return (M) outputImage;
    }

    public
    void setOutputImage ( SipImage outputImage ) {
        this.outputImage = outputImage;
    }

    public
    void setInputImage ( SipImage inputImage ) {
        this.inputImage = inputImage;
    }
}
