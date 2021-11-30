package org.stranger2015.opencv.fic;

import org.stranger2015.opencv.fic.core.FicApplication;

/**
 *
 */
public
class Main {

    public static
    void main ( String[] args ) {
        if (args.length == 0) {
            System.exit(-1);
        }
        new FicApplication(processor, args).run();
    }

}
