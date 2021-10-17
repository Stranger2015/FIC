package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

public
class QuadNode extends BinNode {
    protected Node child2;
    protected Node child3;

    public
    QuadNode ( Node child0,
               Node child1,
               Node child2,
               Node child3
    ) {
        super(child0, child1);

        this.child2 = child2;
        this.child3 = child3;
    }

    public
    QuadNode ( Object... objects ) {
        instance(objects);
    }

    public
    List <Node> build ( Mat image, int width, int height, int x, int y, int w, int h )  {
        List <Node> children = new ArrayList <>(4);

        if(width == w){
            new Leaf(image.submat(x,y,x+width, y=height));
        }

        children.addAll(build(image, width / 2, height / 2, x, y, w, h));
        children.addAll(build(image, width / 2, height / 2, x + width / 2, y, w, h));
        children.addAll(build(image, width / 2, height / 2, x, y + height / 2, w, h));
        children.addAll(build(image, width / 2, height / 2, x + width / 2, y + height / 2, w, h));

        Node qTree = instance();
        return children;
    }

    @Override
    public
    Node instance ( Object @NotNull ... objects ) {
        return new QuadNode(
                objects[0],
                objects[1],
                objects[2],
                objects[3]
        );
    }

    @Override
    public
    void draw ( Mat image, Rect rect, boolean drawQuads ) {
        super.draw(image, rect, drawQuads);
    }
}
