package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public
class OcNode extends QuadNode {
    protected Node child2;
    protected Node child3;
    protected Node child4;
    protected Node child5;
    protected Node child6;
    protected Node child7;

    public
    OcNode ( Node child0,
             Node child1,
             Node child2,
             Node child3,
             Node child4,
             Node child5,
             Node child6,
             Node child7
    ) {
        super(child0, child1);

        this.child2 = child2;
        this.child3 = child3;
        this.child4 = child4;
        this.child5 = child5;
        this.child6 = child6;
        this.child7 = child7;
    }

    public
    OcNode ( Object... objects ) {
        instance(objects);
    }

    @Override
    public
    Node instance ( Object... objects ) {
        return new OcNode(
                (Node) objects[0],
                (Node) objects[1],
                (Node) objects[2],
                (Node) objects[3],
                (Node) objects[4],
                (Node) objects[5],
                (Node) objects[6],
                (Node) objects[7]
        );
    }

    @Override
    public
    void draw ( Mat image, Rect rect, boolean drawQuads ) {
        super.draw(image, rect, drawQuads);
    }
    @Override
    protected
    int maxChildren () {
        return 8;
    }

}
