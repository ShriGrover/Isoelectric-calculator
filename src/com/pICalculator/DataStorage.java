package com.pICalculator;

import java.util.ArrayList;

/**
 * Created by Shri on 2/20/2017.
 */
class DataStorage {

    int NTerminus = 0;
    int CTerminus = 0;

    AminoAcid NGroup;
    AminoAcid CGroup;

    ArrayList<AminoAcid> aminoAcids = new ArrayList<>();

    DataStorage() {
    }

    void setAA(AminoAcid aa, int howMany) {
        for (int i = 0; i < howMany; i++) {
            aminoAcids.add(aa);
        }
    }

    private ArrayList<AminoAcid> getAminoAcids() {
        return aminoAcids;
    }

    String getpIVal() {
        Calculations.getInstance().aminoAcids = getAminoAcids();
        Calculations.getInstance().NGroup = NGroup;
        Calculations.getInstance().CGroup = CGroup;
        String pIVal = String.format("%.02f", Calculations.getInstance().calculatepI());
        return pIVal;
    }

}
