package com.pICalculator;

/**
 * Created by Shri on 2/20/2017.
 */
public enum AminoAcid {
    ALA (2.4, 9.9, 0, "neutral"),
    ARG (1.8, 9.0, 12.5, "positive"),
    ASN (2.1, 8.7, 0, "neutral"),
    ASP (2.0, 9.9, 3.9, "negative"),
    CYS (1.9, 10.7, 8.3, "negative"),
    GLU (2.1, 9.5, 4.1, "negative"),
    GLN (2.2, 9.1, 0, "neutral"),
    GLY (2.4, 9.8, 0, "neutral"),
    HIS (1.8, 9.3, 6.0, "positive"),
    ILE (2.3, 9.8, 0.0, "neutral"),
    LEU (2.3, 9.8, 0.0, "neutral"),
    LYS (2.2, 9.0, 10.5, "positive"),
    MET (2.1, 9.3, 0.0, "neutral"),
    PHE (2.2, 9.3, 0.0, "neutral"),
    PRO (2.0, 10.6, 0.0, "neutral"),
    SER (2.2, 9.2, 13.5, "negative"),
    THR (2.1, 9.1, 13.5, "negative"),
    TRP (2.5, 9.4, 0.0, "neutral"),
    TYR (2.2, 9.2, 10.5, "negative"),
    VAL (2.3, 9.7, 0.0, "neutral");


    private final double COOH;      //pKa of COOH
    private final double NH3;       //pKa of NH3
    private final double RGroup;    //pKa of RGroup
    private final String charge;    //charge if applicable, negative neutral or positive
                                    // negative = OH or SH group, positive = NH2 group

    AminoAcid(double COOH, double NH3, double RGroup, String charge) {
        this.COOH = COOH;
        this.NH3 = NH3;
        this.RGroup = RGroup;
        this.charge = charge;
    }

    public double getCOOH() {
        return COOH;
    }

    public double getNH3() {
        return NH3;
    }

    public double getRGroup() {
        return RGroup;
    }

    public String getCharge() {
        return charge;
    }
}


