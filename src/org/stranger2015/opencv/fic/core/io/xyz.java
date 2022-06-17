package org.stranger2015.opencv.fic.core.io;

// Example 1. Creating an image and setting its pixels.
//import com.aspose.imaging.FileFormat;
//import com.aspose.imaging.Image;
//import com.aspose.imaging.RasterImage;
//import com.aspose.imaging.Size;
//import com.aspose.imaging.fileformats.apng.ApngImage;
//import com.aspose.imaging.fileformats.png.PngColorType;
//import com.aspose.imaging.imageoptions.ApngOptions;
//import com.aspose.imaging.sources.FileCreateSource;

// Load pixels from source raster image
//Size imageSize;
//        int[] imagePixels;
//        try (RasterImage sourceImage = (RasterImage) Image.load("not_animated.png"))
//        {
//        imageSize = sourceImage.getSize();
//        imagePixels = sourceImage.loadArgb32Pixels(sourceImage.getBounds());
//        }
//
//// Create APNG image and set its pixels
//        try (ApngOptions options = new ApngOptions())
//        {
//        options.setSource(new FileCreateSource("created_apng.png", false));
//        options.setColorType(PngColorType.TruecolorWithAlpha);
//
//        try (ApngImage image = (ApngImage)Image.create(options, imageSize.getWidth(), imageSize.getHeight()))
//        {
//        image.saveArgb32Pixels(image.getBounds(), imagePixels);
//        image.save();
//        }
//        }
//
//// Check output file format
//        try (Image image = Image.load("created_apng.png"))
//        {
//        assert (image.getFileFormat() == FileFormat.Apng);
//        assert (image instanceof ApngImage);
//        }
