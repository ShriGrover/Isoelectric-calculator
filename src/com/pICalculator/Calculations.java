package com.pICalculator;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

/**
 * Created by Shri on 2/20/2017.
 */
public final class Calculations extends DataStorage {
    private static final Calculations INSTANCE = new Calculations();


    private Calculations() {
        super();
    }


    static Calculations getInstance() {
        return INSTANCE;
    }

    private TreeMap<Double, ArrayList<Double>> calcTable = new TreeMap<>();




    private void setCalcTable() {
        calcTable.put(NGroup.getNH3(), makeAvgMC(NGroup.getNH3()));
        calcTable.put(CGroup.getCOOH(), makeAvgMC(CGroup.getCOOH()));

        for (AminoAcid aa : aminoAcids) {
            if (!aa.getCharge().equals("neutral")) {
                double pH = aa.getRGroup();
                calcTable.put(pH, makeAvgMC(pH));
            }
        }
        super.aminoAcids.clear();
    }

    private ArrayList<Double> makeAvgMC(double pH) { //given a pH, it calculates the average molecule for each AA entered
        ArrayList<Double> ans = new ArrayList<>();

        ans.add(calcAvgMC(pH, NGroup.getNH3(), "positive"));
        ans.add(calcAvgMC(pH, CGroup.getCOOH(), "negative"));

        for (AminoAcid aa: aminoAcids) {
            if (!aa.getCharge().equals("neutral")) ans.add(calcAvgMC(pH, aa.getRGroup(), aa.getCharge())); //only if there is an RGroup
        }
        return ans;
    }

    private double calcAvgMC(double pH, double pKa, String charge) {

        if (pH == pKa) {
            if (charge.equals("positive")) return 0.5; //+0.5 for positive groups
            else return -0.5;                                  //-0.5 for negative groups
        }

        if (Math.abs(pH - pKa) >= 2) { //if pH is 2+ away from pKa
            if ((pH - pKa) >= 2) { //if pH is 2 MORE than the pKa
                if (charge.equals("positive")) {
                    return 0;    // charge = positive
                } else return -1; // charge = negative
            } else if (charge.equals("positive")) { //if pH is 2 LESS than the pKa
                return 1;       // charge = positive
            } else return 0;   // charge = negative
        } else {
            return manualCalc(pH, pKa, charge);
        }

    }

    private double manualCalc(double pH, double pKa, String charge) {
        double x = Math.pow(10,(pH - pKa)); // x = 10^(pH-pKa)

        if (charge.equals("negative")) {
            return -(x/(x+1));
        } else return 1-(x/(x+1));
    }

    double calculatepI() {
        setCalcTable();
        double prevKey = CGroup.getCOOH(); // previous key value, lowest = COOH group in any case
        for (Map.Entry<Double, ArrayList<Double>> entry : calcTable.entrySet()) {
            Double key = entry.getKey();
            ArrayList<Double> val = entry.getValue();
            double totalCharge = sum(val); //add up all the charges for the given pH
            if (totalCharge == 0) return key;
            if (totalCharge < 0) return ((prevKey + key) / 2);
            else prevKey = key;
        }

        return error;
    }

    private double sum(ArrayList<Double> val) {
        double i = 0;
        for (Double d: val) {
            i += d;
        }
        return i;
    }

    void clearCalcTable() {
        calcTable.clear();
    }
}
