package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.IEncoder;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public abstract
class AlterBinTreeTiler extends BinTreeTiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    AlterBinTreeTiler ( IImage image,
                        IIntSize rangeSize,
                        IIntSize domainSize,
                        IEncoder encoder,
                        ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public abstract
    ITiler instance ();

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    public
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight ) throws ValueError {
        return super.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        if ((imageBlock.getWidth() / imageBlock.getHeight()) == 2 ||
                (imageBlock.getHeight() / imageBlock.getWidth()) == 2
        ) {
            throw new ValueError("Image block must have the side size ratio of 2:1");
        }
        super.segmentRectangle(node, imageBlock);
    }

    //
//public
//class AlterBinTreeTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
//        extends AlterBinTreeTiler <N> {
//
//    /**
//     * @param image
//     * @param rangeSize
//     * @param domainSize
//     * @param encoder
//     * @param builder
//     */
//    protected
//    AlterBinTreeTiler ( IImage image,
//                        IIntSize rangeSize,
//                        IIntSize domainSize,
//                        IEncoder <N> encoder,
//                        ITreeNodeBuilder <N> builder ) {
//
//        super(image, rangeSize, domainSize, encoder, builder);
//    }
//
//    @Override
//    protected
//    void doTile ( IIntSize minRangeSize, Deque <IImageBlock > queue ) throws ValueError {
//        super.doTile(minRangeSize, queue);
//    }
//
//    @Override
//    public
//    void segmentSquare ( IImageBlock  imageBlock ) throws ValueError {
//        int x = imageBlock.getX();
//        int y = imageBlock.getY();
//        int w = imageBlock.getWidth();
//        int h = imageBlock.getHeight();
//
//        ESplitKind dir = chooseDirection(imageBlock);
//        switch (dir) {
//            case HORIZONTAL:
//                r[0] = new Rectangle(x, y, w, h / 2);
//                r[1] = new Rectangle(x, y + h / 2, w, h / 2);
//                break;
//            case VERTICAL:
//                r[0] = new Rectangle(x, y, w / 2, h);
//                r[1] = new Rectangle(x + w / 2, y, w / 2, h);
//                break;
//        }
//    }
//
//    /**
//     * @param imageBlock
//     * @throws ValueError
//     */
//    @Override
//    @Contract(value = "_, _ -> new")
//    public
//    void segmentRectangle ( IImageBlock  imageBlock )
//            throws ValueError {
//
//        int x = imageBlock.getX();
//        int y = imageBlock.getY();
//        int w = imageBlock.getWidth();
//        int h = imageBlock.getHeight();
//
//        ESplitKind dir = chooseDirection(imageBlock);
//
//        if (sideSize == imageBlock.getWidth() / 2) {
//
//        }
//        else if (sideSize == imageBlock.getWidth() / 2) {
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(sideSize, 0, sideSize);
//        }
//        else {
//            sideSize = imageBlock.getHeight() / 2;
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(0, sideSize, sideSize);
//        }
//    }
}
