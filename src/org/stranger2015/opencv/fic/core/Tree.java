package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

abstract public
class Tree<N extends Node, M extends Mat> {

    protected final M image;
    protected final N root;                            // The root of the tree
    protected final List <N> leafs=new ArrayList <>();

    /**
     *
     */
    protected int depth;                                // The depth of the tree
    protected final Rect area;


    /**
     * Constructs a new object.
     * @param image
     * @param root
     * @param area
     */
    public
    Tree ( M image, N root, Rect area ) {
        super();
        this.image = image;
        this.root = root;
        this.area = area;
    }

    public
    EnumSet <AffineTransform> getTransforms () {
        return null;
    }

    public
    void add ( N node ) {
//        if (getChildren().size() == 4) {
//            throw new IllegalStateException("quads size is not be greater than 4!");
//        }
//        getChildren().add(node);
    }

    public
    void set ( int index, N node ) {

//        getChildren().set(index, node);
    }

    public
    void set ( List <N> children ) {
//        this.children.clear();
//        this.children.addAll(children);
    }

    //    @Override
    public
    void draw ( M image, Rect rect, boolean drawQuads ) {
//        for (Node node : getChildren()) {
//            node.draw(image, rect, drawQuads);
//        }
    }

    public
    N getRoot () {
        return root;
    }

    public
    M getImage () {
        return image;
    }

    public
    enum AffineTransform {
        TRANSLATE,
        ROTATE,
        SCALE,
        SKEW,
        MIRROR,
        PERSPECTIVE
    }

    public
    Tree ( M image, N root, List <N> leafs, Rect area ) {
        this.image = image;
        this.root = root;
        this.area = area;
        this.leafs.addAll(leafs);
    }
}
