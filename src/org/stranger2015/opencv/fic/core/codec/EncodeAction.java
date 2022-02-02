package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Task;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 *
 */
public
class EncodeAction extends Task implements Consumer <String> {

    /**
     * @param fn
     */
    public
    EncodeAction ( String fn ) {
        super(fn);
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {
//        Path path = Paths.get(s);
//        path.toFile().exists();//fixme
    }
}
