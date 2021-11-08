package org.stranger2015.opencv.fic.core;

import org.stranger2015.org.enumus.Hierarchy;

import static org.stranger2015.opencv.fic.core.CornerDirection.*;

public
enum SideDirection implements ISideDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    private final SideDirection parent;

    private final static Hierarchy <SideDirection> hierarchy =
            new Hierarchy <>(SideDirection.class, SideDirection::getParent);

    public
    SideDirection[] children () {
        return hierarchy.getChildren(this);
    }

    public
    boolean isA ( SideDirection other ) {
        return hierarchy.relate(other, this);
    }

    SideDirection ( SideDirection parent ) {
        this.parent = parent;
    }

    SideDirection () {
        this.parent = null;
    }

    public
    SideDirection getParent () {
        return parent;
    }

    @Override
    public
    CornerDirection reflect ( CornerDirection quadrant ) {
        CornerDirection result = null;
        switch (this) {
            case NORTH:
            case SOUTH:
                switch (quadrant) {
                    case NORTH_EAST:
                    case NORTH_WEST:
                    case SOUTH_WEST:
                        break;
                    case SOUTH_EAST:
                        result = NORTH_EAST;
                        break;
                }
                break;
            case EAST:
            case WEST:
                switch (quadrant) {
                    case NORTH_EAST:
                    case SOUTH_WEST:
                    case NORTH_WEST:
                        break;
                    case SOUTH_EAST:
                        result = SOUTH_WEST;
                        break;
                }
                break;
        }
        if (result == null) {
            throw new IllegalStateException("null result in reflect()");
        }
        return result;
    }

    /**
     *
     *
     * @param side
     * @return
     */
    @Override
    public
    CornerDirection quadrant ( SideDirection side ) {
        switch (this) {
            case NORTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException("illegal input - " + this + ", " + side);
                    case EAST:
                        return NORTH_EAST;
                    case WEST:
                        return NORTH_WEST;
                }
                break;
            case EAST:
                switch (side) {
                    case NORTH:
                        return NORTH_EAST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException("illegal input - " + this + ", " + side);
                    case SOUTH:
                        return SOUTH_EAST;
                }
                throw new IllegalArgumentException("illegal input - " + this + ", " + side);
            case SOUTH:
                switch (side) {
                    case NORTH:
                    case SOUTH:
                        throw new IllegalArgumentException("illegal input - " + this + ", " + side);
                    case EAST:
                        return SOUTH_EAST;
                    case WEST:
                        return SOUTH_WEST;
                }
                break;
            case WEST:
                switch (side) {
                    case NORTH:
                        return NORTH_WEST;
                    case EAST:
                    case WEST:
                        throw new IllegalArgumentException("illegal input - " + this + ", " + side);
                    case SOUTH:
                        return SOUTH_WEST;
                }
                break;
        }
        throw new IllegalArgumentException("illegal input - " + this + ", " + side);
    }

    /**
     * return true if and only if quadrant Z is adjacent to boundary B of the node’s block,
     * e.g., ADJ(E, SE) is true.
     *
     * @param cornerDirection
     * @return
     */
    @Override
    public
    boolean adjacent ( CornerDirection cornerDirection ) {
        return cornerDirection.toSideDirection().contains(this);
    }

    @Override
    public
    int north () {
        return NORTH.ordinal();
    }

    @Override
    public
    int east () {
        return EAST.ordinal();
    }

    @Override
    public
    int south () {
        return SOUTH.ordinal();
    }

    @Override
    public
    int west () {
        return WEST.ordinal();
    }

    @Override
    public
    SideDirection cSide () {
        SideDirection result = null;
        switch (this) {
            case NORTH:
                result = EAST;
                break;
            case EAST:
                result = SOUTH;
                break;
            case SOUTH:
                result = WEST;
                break;
            case WEST:
                result = NORTH;
                break;
        }
        if (result == null) {
            result = this;
        }

        return result;
    }

    @Override
    public
    SideDirection ccSide () {
        SideDirection result = null;
        switch (this) {
            case NORTH:
                result = WEST;
                break;
            case WEST:
                result = SOUTH;
                break;
            case SOUTH:
                result = EAST;
                break;
            case EAST:
                result = NORTH;
                break;
        }
        if (result == null) {
            result = this;
        }

        return result;
    }

    @Override
    public
    SideDirection opSide () {
        SideDirection result = null;
        switch (this) {
            case NORTH:
                result = EAST;
                break;
            case EAST:
                result = SOUTH;
                break;
            case SOUTH:
                result = WEST;
                break;
            case WEST:
                result = NORTH;
                break;
        }

        return result;
    }
}