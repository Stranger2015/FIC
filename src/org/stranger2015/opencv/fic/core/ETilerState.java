package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
enum ETilerState implements IOperation {
    TILE_FINISH,
//    TILE_INACTIVE,
    TILE_LEAF,
//    TILE_START,
    TILE_SUCCESSOR,
    TILE_SUCCESSORS,
    ;

    ETilerState () {
    }

    @Override
    public
    IOperation getOperation () {
        return null;
    }

    @Override
    public
    void setOperation ( IOperation operation ) {

    }
}
