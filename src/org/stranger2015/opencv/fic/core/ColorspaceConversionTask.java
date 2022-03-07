package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * YUV, YCbCr, YPbPr color spaces
 * <p>
 * YUV color model imitates a human vision. Humans are able to recognize (discern) the contents of a color RGB
 * (Red / Green / Blue) image presented in the two main forms, as an original RGB color image or as a gray
 * (black / white) image.
 * <p>
 * Historically, YUV color space was developed to provide compatibility between color and black/white analog
 * television systems.
 * YUV color image information transmitted in the TV signal allows proper reproducing an image contents at
 * the both types of TV receivers, at the color TV sets, and at the black / white TV sets.
 * <p>
 * Term YUV itself is not defined precisely in the technical and scientific literature. In general,
 * it designates a whole family of so-called luminance / chrominance color spaces. The best way to avoid
 * ambiguity associated with the term YUV is to refer to the concrete variant of YUV colors space well-defined
 * in the internationally recognized standard documents.
 * <p>
 * YCbCr color space is defined in the ITU-R BT.601-5 [1] and ITU-R BT.709-5 [2] standards of ITU
 * (International Telecommunication Union). These documents define YCbCr as a color space for digital television
 * systems. These documents give concrete definitions for coefficients of conversion between RGB and YCbCr color
 * spaces, for normalization and quantization of digital signals. A majority of parameters defined for the digital
 * YCbCr color space remains the same for the YPbPr color space used in the analog television systems. YCbCr and YPbPr
 * color spaces are closely related.
 * <p>
 * Individual color components of YCbCr color space are luma Y, chroma Cb and chroma Cr. Chroma Cb corresponds to
 * the U color component, and chroma Cr corresponds to the V component of a general YUV color space.
 * <p>
 * Y = Kry · R + Kgy · G + Kby · B
 * Cb = B – Y
 * Cr = R – Y
 * Kry + Kgy + Kby = 1
 * <p>
 * Y = Kry · R + Kgy · G + Kby · B
 * Cb = Kru · R + Kgu · G + Kbu · B
 * Cr = Krv · R + Kgv · G + Kbv · B
 * <p>
 * Where
 * Kru = - Kry
 * Kgu = - Kgy
 * Kbu = 1 - Kby
 * Krv = 1 – Kry
 * Kgv = -Kgy
 * Kbv = -Kby
 * <p>
 * All coefficients used in the above formulae can be calculated from the just 2 main coefficients Kry and Kby.
 * These 2 coefficients define a relative contribution of red and blue color components in the total value of
 * luma Y. These coefficients reflect a relative sensitivity of human vision to the red and blue portions of a
 * visible light. At the same time, values of 2 main coefficients take in account colorimetric parameters of image
 * reproducing devices, such as a gamma function of displays.
 * <p>
 * Table 1 presents main coefficients Kry and Kby defined in the ITU 601 and ITU 709 documents.
 * Table also includes same meaning but different value coefficients from the standard 240M defined
 * by the Society of Motion Picture and Television Engineers.
 *
 * <table> </table>
 * Table 1: Coefficients Kry and Kby of color conversion from RGB to YCbCr. Reference standard 	Kry 	Kby
 * ITU601 / ITU-T 709 1250/50/2:1 	0.299 	0.114
 * ITU709 / ITU-T 709 1250/60/2:1 	0.2126 	0.0722
 * SMPTE 240M (1999) 	0.212 	0.087
 * <p>
 * =================================================================
 * <p>
 * YUV and YIQ (Color spaces V)
 * <p>
 * Not all color spaces are inherently digital, and in fact, most were first conceived as, and for, analog means.
 * Let’s have a look at two of those color spaces, YUV and YIQ. The YUV color space was used for the European analog
 * TV standard, PAL, while YIQ was used for the North American and Japanese standard, NTSC.
 * <p>
 * The YUV color space, like all the others we’ve seen so far, encodes the brightness in the first components and uses
 * two difference components, U and V, that somewhat correspond to the yellow-blue and the red-cyan differences:
 * <p>
 * \begin{bmatrix} 0.299 & 0.587 & 0.114\\ -0.147 & -0.289 & 0.436\\ 0.615 & -0.515 & -0.1\\ \end{bmatrix}
 * \begin{bmatrix} r\\ g\\ b\\ \end{bmatrix} =
 * \begin{bmatrix} Y\\ U\\ V\\ \end{bmatrix}
 * <p>
 * with inverse
 * <p>
 * \begin{bmatrix} 1 & 0 & 1.340 \\ 1 & -0.395 & -0.581\\ 1 & 2.032 & 0\\ \end{bmatrix}
 * \begin{bmatrix} Y\\ U\\ V\\ \end{bmatrix} =
 * \begin{bmatrix} r\\ g\\ b\\ \end{bmatrix}
 * <p>
 * In PAL, YUV is sent over the air using a special frequency modulation. The Y component eats the whole band, and
 * carries the black and white image. The U and V components are quadrature amplitude modulated over the Y signal.
 * If decoded for Y only, the signal would only exhibit high-frequency, low amplitude noise, probably unnoticeable
 * in normal viewing conditions.
 * <p>
 * *
 * * *
 * <p>
 * YIQ is the color space used in NTSC for color encoding. The transform is given by:
 * <p>
 * \begin{bmatrix} 0.299 & 0.587 & 0.114\\ 0.596 & -0.275 & -0.321\\ 0.212 & -0.523 & 0.311\\
 * \end{bmatrix}
 * \begin{bmatrix} r\\ g\\ b\\ \end{bmatrix} =
 * \begin{bmatrix} Y\\ I\\ Q\\ \end{bmatrix}
 * <p>
 * with inverse
 * <p>
 * \begin{bmatrix} 1 & 0.956 & 0.621\\ 1 & -0.272 & -0.647\\ 1 & -1.107 & 1.704\\ \end{bmatrix}
 * \begin{bmatrix} Y\\ I\\ Q\\ \end{bmatrix} =
 * \begin{bmatrix} r\\ g\\ b\\ \end{bmatrix}
 * <p>
 * The I and Q components are the red-cyan and yellow-blue differences but rotated -33° around the Y axis, and scaled
 * by what essentially look like magic constants. Why? I am not sure, because I and Q proceed to another encoding stage,
 * the quadrature amplitude modulation (QAM), just as in YUV/PAL. Maybe it was meant to be especially simple in (analog)
 * hardware decoding? That, I’m not very convinced, but I never build a decoder out of analog parts.
 * <p>
 * *
 * * *
 * <p>
 * The coefficients used in the various colorspace bases (or transforms) seem random, except for some regularities
 * such as the first row that encodes brightness, sometimes with the same coefficients (0.299, 0.587, 0.114),
 * but in fact, there’s a lot more order behind all this. I will close this series of posts with a bird’s eye view of
 * (linear) color spaces that will explain a lot. Yeah, I’m a tease.
 *
 * @param <N>
 * @param <A>
 * @param <M>
 * @param <G>
 */
public
class ColorspaceConversionTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        extends BidiTask <N, A, M, G> {

    protected final EtvColorSpace colorSpace;

    /**
     * @param filename
     * @param scheme
     * @param task
     * @param inverseTask
     */
    public
    ColorspaceConversionTask ( String filename,
                               EPartitionScheme scheme,
                               EtvColorSpace colorSpace,
                               Task <N, A, M, G> task,
                               Task <N, A, M, G> inverseTask ) {

        this(filename, scheme, colorSpace, List.of(task, inverseTask));
    }

    public
    ColorspaceConversionTask ( String fileName,
                               EPartitionScheme scheme,
                               EtvColorSpace colorSpace,
                               List<Task <N, A, M, G>> tasks ) {

        super(fileName, scheme, tasks);

        this.colorSpace = colorSpace;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    M execute ( String filename ) throws ValueError {
        M image = super.execute(filename);

        return image;
    }
}
