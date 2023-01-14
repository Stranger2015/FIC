package org.stranger2015.opencv.fic.core.enumus.enumus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum Country2 {
    FRANCE("French Republic",
            null,
            "Emmanuel Macron",
            "Édouard Philippe",
            List.of(),
            33,
            IsoAlpha2.FR),
    USA("The United states of America",
            null,
            "Donald Trump",
            null,
            List.of(),
            1,
            IsoAlpha2.US),

    ENGLAND("England",
            "Elizabeth II",
            null,
            "Theresa May",
            List.of(),
            44,
            IsoAlpha2.GB),
    WELSH("England",
            "Elizabeth II",
            null,
            "Theresa May",
            List.of(),
            44,
            IsoAlpha2.GB),
    SCOTLAND("England",
            "Elizabeth II",
            null,
            "Nicola Sturgeon",
            List.of(),
            44,
            IsoAlpha2.GB),
    UNITED_KINDOM("The United Kingdom of Great Britain and Northern Ireland",
            "Elizabeth II",
            null,
            "Theresa May",
            asList(ENGLAND, WELSH, SCOTLAND),
            44,
            IsoAlpha2.GB),
    ;

    private static
    List<Country2> asList ( Country2 england, Country2 welsh, Country2 scotland ) {
        return new ArrayList ();
    }

    /*too long...*/
    private final String name;
    private final String monarch;
    private final String president;
    private final String primeMinister;
    private final Collection<Country2> federatedState;
    private final int countryCode;
    private final IsoAlpha2 iso;



    Country2 ( String name,
               String monarch,
               String president,
               String primeMinister,
               Collection<Country2> federatedState,
               int countryCode,
               IsoAlpha2 iso) {
        this.name = name;
        this.monarch = monarch;
        this.president = president;
        this.primeMinister = primeMinister;
        this.federatedState = federatedState;
        this.countryCode = countryCode;
        this.iso = iso;
    }
}
