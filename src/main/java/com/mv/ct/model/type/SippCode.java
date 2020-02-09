package com.mv.ct.model.type;

public enum SippCode {

    MCMR("M"),
    EDMN("E"),
    CDMR("C"),
    ICAV("I"),
    MDMR("M"),
    EDMR("E"),
    CLMR("C"),
    CMMV("C"),
    FVAR("C");

    private String category;

    SippCode(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}