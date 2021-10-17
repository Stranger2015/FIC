package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.IDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract public
class Node extends Rect implements IDrawable {

    protected static int indexCounter;
    protected short index;

    /**
     *
     */
    protected List<Node> children=new ArrayList <>();

    public
    short getIndex () {
        return index;
    }

    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;

        return index == node.index;
    }

    @Override
    public
    int hashCode () {
        return Objects.hash(index);
    }

    @Override
    public
    String toString () {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("index=").append(index);
        sb.append('}');

        return sb.toString();
    }

    public
    Node () {
        this.index = (short) indexCounter++;
    }

    public
    List <Node> getChildren () {
        return children;
    }

    /**
     * @param objects
     * @return
     */
    abstract public
    Node instance ( Object... objects );

    public
    boolean isLeaf ( ) {
        return getChildren().size() == 0;
    }

    abstract public
    Node merge ();
}

