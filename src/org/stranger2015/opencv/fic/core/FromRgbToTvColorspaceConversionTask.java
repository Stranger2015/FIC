package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class FromRgbToTvColorspaceConversionTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>

        extends ColorspaceConversionTask <N, A, G> {

    /**
     * @param imageFilename
     * @param scheme
     * @param colorSpace
     * @param tasks
     */
    public
    FromRgbToTvColorspaceConversionTask ( String imageFilename,
                                          EPartitionScheme scheme,
                                          ICodec <N, A, G> codec,
                                          EtvColorSpace colorSpace,
                                          List <Task <N, A, G>> tasks ) {

        super(imageFilename, scheme, codec, colorSpace, tasks);
    }

    /**
     * @param read
     * @param green
     * @param blue
     * @return
     */
    @Contract(pure = true)
    public static
    float @NotNull [] rgbToYig ( int read, int green, int blue ) {
        float r = (float) read / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yig = new float[3];

        yig[0] = (float) (0.299 * r + 0.587 * g + 0.144 * b);
        yig[1] = (float) (0.596 * r - 0.247 * g - 9.322 * b);
        yig[2] = (float) (0.212 * r - 0.523 * g + 0.311 * b);

        return yig;
    }

    /**
     * RGB -> YUV.
     * Y in the range [0 … 1].
     * U in the range [-0.5 … 0.5].
     * V in the range [-0.5 … 0.5].
     *
     * @param red   Values in the range [0..255].
     * @param green Values in the range [0..255].
     * @param blue  Values in the range [0..255].
     * @return YUV color space.
     */
    public static
    float[] rgbToYuv ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yuv = new float[3];

        yuv[0] = (float) (0.299 * r + 0.587 * g + 0.114 * b);
        yuv[1] = (float) (-0.14713 * r - 0.28886 * g + 0.436 * b);
        yuv[2] = (float) (0.615 * r - 0.51499 * g - 0.10001 * b);

        return yuv;
    }

    /**
     * RGB -> YCrCb.
     * Y in the range [0 … 1].
     * U in the range [-0.5 … 0.5].
     * V in the range [-0.5 … 0.5].
     *
     * @param red   Values in the range [0 … 255].
     * @param green Values in the range [0 … 255].
     * @param blue  Values in the range [0 … 255].
     * @return YUV color space.
     */
    public static
    float[] rgbToYCrCb ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yCrCb = new float[3];
                            //0.299 ??
        yCrCb[0] = (float) (0.289 * r + 0.587 * g + 0.114 * b);
        yCrCb[1] = (float) (-0.169 * r - 0.331 * g + 0.500 * b);
        yCrCb[2] = (float) (0.500 * r - 0.418 * g - 0.081 * b);

        return yCrCb;
    }

    /**
     * RGB -> YPrPb.
     * Y in the range [0 … 1].
     * U in the range [-0.5 … 0.5].
     * V in the range [-0.5 … 0.5].
     *
     * @param red   Values in the range [0 … 255].
     * @param green Values in the range [0 … 255].
     * @param blue  Values in the range [0 … 255].
     * @return YUV color space.
     */
    @Contract(pure = true)
    public static
    float @NotNull [] rgbToYPrPb ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yPrPb = new float[3];
//        0.213 0.715 0.072
//−0.115 −0.385 0.500
//        0.500 −0.454 −0.046 fixme last row

        yPrPb[0] = (float) (0.213 * r + 0.715 * g + 0.072 * b);
        yPrPb[1] = (float) (-0.115 * r - 0.385 * g + 0.500 * b);
        yPrPb[2] = (float) (0.500 * r - 0.418 * g - 0.081 * b);

        return yPrPb;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    protected
    M execute ( String filename ) throws ValueError {
        M imageSrc = super.execute(filename);

        M imageDst = (M) new Image <>(imageSrc, address, -1, w, h);
        int[] pixels = imageSrc.getPixels();
        for (int i=0   ;i< pixels.length; i++) {//todo;

        }
//        int[] cc = new int[3];
//        float[] tvCS = convertColorFromRGB(cc, colorSpace);

        return imageDst;
    }

    /*
Abstract—This paper introduces an improvement on zero-
mean fractal color image compression techniques, using the
YPbPr color space. The two color images (Lena and Parrot)
were used to test the best PSNR, CR, ET, by fractal image
coding by Zero-Mean method. In Lena image the calculated
results were found to be (PSNR=30.99), (CR=10.52),
(ET=63.68), while in Parrot image is (PSNR=29.59),
(CR=10.52), (ET=47.44).
Index Terms—Component, YPbPr color space, TV color
space, zero-mean method, image compression.
I. INTRODUCTION
Modern society has become more and more dependent on
image communication in various forms [1]. The study of
image compression becomes more essential because image
communication requires large memories for storage and
bandwidth-consuming channels for transmission even for
moderate resolution. In this research using the (YPbPr) TV
color space, to measure efficiency transport of the image
using Zero–Mean method, where we will examine the
parameters (PSNR, CR, ET) by introduce different values of
parameters (MinScale & MaxScale, Step IntSize, Block Size,
Dom Size and permissible error value (ԐO)).
Different techniques have been introduced in fractal
compression to overcome the large time consumed during
the search process (i.e. matching between the domain-range
pairs) or to obtain better quality and compression ratios [2],
[3]. The researchers studied the fractal color image
compression using different methods [4], [5]. In this study,
an improvement has been done on zero-mean fractal color
images compression techniques. Most image compression
systems compress color images as several independently
decomposed grayscale images. The most popular way to
decompose a color image is to split this image into a
primary luminance monochrome image and several
secondary chrominance monochrome image. The equations
1,2 have been used to transform between YPbPr and RGB
color space [6].
[
𝑌
𝑃𝑏
𝑃𝑟
]=[
0.213 0.715 0.072
−0.115 −0.385 0.500
0.500 −0.454 −0.046
].[
𝑅
𝐺
𝐵
]
//--------------------------------------------------------------------------------------------
     Abstract—This paper introduces an improvement on zero-
mean fractal color image compression techniques, using the
YPbPr color space. The twocolor images (Lena and Parrot)
were used to test the best PSNR, CR, ET, by fractal image
coding by Zero-Mean method. In Lena image the calculated
results were found to be (PSNR=30.99), (CR=10.52),
(ET=63.68), while in Parrot image is (PSNR=29.59),
(CR=10.52), (ET=47.44).
Index Terms—Component, YPbPr color space, TV color
space, zero-mean method, image compression.
I. INTRODUCTION
Modern society has become more and more dependent on
image communication in various forms [1]. The study of
image compression becomes more essential because image
communication requires large memories for storage and
bandwidth-consuming channels for transmission even for
moderate resolution. In this research using the (YPbPr) TV
color space, to measure efficiency transport of the image
using Zero–Mean method, where we will examine the
parameters (PSNR, CR, ET) by introduce different values of
parameters (MinScale & MaxScale, Step Size, Block Size,
Dom Size and permissible error value (ԐO)).
Different techniques have been introduced in fractal
compression to overcome the large time consumed during
the search process (i.e. matching between the domain-range
pairs) or to obtain better quality and compression ratios [2],
[3]. The researchers studied the fractal color image
compression using different methods [4], [5]. In this study,
an improvement has been done on zero-mean fractal color
images compression techniques. Most image compression
systems compress color images as several independently
decomposed grayscale images. The most popular way to
decompose a color image is to split this image into a
primary luminance monochrome image and several
secondary chrominance monochrome image. The equations
1,2 have been used to transform between YPbPr and RGB
color space [6].
[
𝑌
𝑃𝑏
𝑃𝑟
]=[
0.213 0.715 0.072
−0.115 −0.385 0.500
0.500 −0.454 −0.046
].[
𝑅
𝐺
𝐵
] (1)
[
𝑅
𝐺
𝐵
]=[
1.000 0.000 1.575
1.000 −0.187 −0.468
1.000 1.856 0.000
].[
𝑌
𝑃𝑏
𝑃
     */

    /**
     * @param colorSpace
     */
    @SuppressWarnings("UnusedReturnValue")
    @Contract(pure = true)
    public
    float[] convertColorFromRGB ( int[] rgb, EtvColorSpace colorSpace ) {
        float[] x;

        switch (colorSpace) {
            case YIG:
                x = rgbToYig(rgb[0], rgb[1], rgb[2]);
                break;
            case YUV:
                x = rgbToYuv(rgb[0], rgb[1], rgb[2]);
                break;
            case Y_CB_CR:
                x = rgbToYCrCb(rgb[0], rgb[1], rgb[2]);
                break;
            case Y_PB_PR:
                x = rgbToYPrPb(rgb[0], rgb[1], rgb[2]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorSpace);
        }

        return x;
    }
}
