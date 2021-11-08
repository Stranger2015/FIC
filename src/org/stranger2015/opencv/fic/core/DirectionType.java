package org.stranger2015.opencv.fic.core;

import org.stranger2015.org.enumus.Hierarchy;

public
enum DirectionType {
    SIDE,
    QUADRANT,;

    private final DirectionType parent;

    private static final Hierarchy <DirectionType> hierarchy =
            new Hierarchy <>(
                    DirectionType.class,
                    e -> e.parent);

    public DirectionType[] children() {
        return hierarchy.getChildren(this);
    }

    public boolean isA(DirectionType other) {
        return hierarchy.relate(other, this);
    }

    DirectionType ( DirectionType parent ) {
        this.parent = parent;
    }

    DirectionType () {
        this.parent = null;
    }
}
