package org.stranger2015.opencv.fic.core.enumus.enumus;

//.samples.article.enumwitannotations;

import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Initializable;
import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.IntValue;
import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value;

public enum Country implements Initializable {
    @Value(name = "name", value = "French Republic")
    @Value(name = "president", value = "Emmanuel Macron")
    @Value(name = "primeMinister", value = "Édouard Philippe")
    @IntValue(name = "countryCode", value = 33)
    @org.stranger2015.opencv.fic.core.enumus.enumus.IsoCountryCode(name = "iso", iso = IsoAlpha2.FR)
    FRANCE(),


    @Value(name = "name", value = "United States of America")
    @Value(name = "president", value = "Donald Trump")
    @IntValue(name = "countryCode", value = 1)
    @org.stranger2015.opencv.fic.core.enumus.enumus.IsoCountryCode(name = "iso", iso = IsoAlpha2.US)
    USA(),

    @Value(name = "name", value = "England")
    @Value(name = "monarch", value = "Elizabeth II")
    @Value(name = "primeMinister", value = "Theresa May")
    @IntValue(name = "countryCode", value = 44)
    ENGLAND(),

    @Value(name = "name", value = "England")
    @Value(name = "monarch", value = "Elizabeth II")
    @Value(name = "primeMinister", value = "Theresa May")
    @IntValue(name = "countryCode", value = 44)
    WELSH(),

    @Value(name = "name", value = "England")
    @Value(name = "monarch", value = "Elizabeth II")
    @Value(name = "primeMinister", value = "Nicola Sturgeon")
    @IntValue(name = "countryCode", value = 44)
    SCOTLAND(),


    @Value(name = "name", value = "The United Kingdom of Great Britain and Northern Ireland")
    @Value(name = "monarch", value = "Elizabeth II")
    @Value(name = "primeMinister", value = "Theresa May")
    @IntValue(name = "countryCode", value = 44)
    @org.stranger2015.opencv.fic.core.enumus.enumus.IsoCountryCode(name = "iso", iso = IsoAlpha2.GB)
    UNITED_KINDOM(ENGLAND, WELSH, SCOTLAND),
    ;

    private /*final*/ String name;
    private /*final*/ String monarch;
    private /*final*/ String president;
    private /*final*/ String primeMinister;
    private /*final*/ Country1[] federatedState;
    private /*final*/ int countryCode;
    private /*final*/ IsoAlpha2 iso;


    // fdfdf
    Country( Country1... countries) {
        this.name = $();
        this.monarch = $();
        this.president = $();
        this.primeMinister = $();
        this.federatedState = $(countries);
        this.countryCode = $();
        this.iso = $();
    }

    Country ( Country england, Country welsh, Country scotland ) {
    }

    public String getName() {
        return name;
    }

    public String getMonarch() {
        return monarch;
    }

    public String getPresident() {
        return president;
    }

    public String getPrimeMinister() {
        return primeMinister;
    }

    public Country1[] getFederatedState() {
        return federatedState;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public IsoAlpha2 getIso() {
        return iso;
    }
}
