package com.pICalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Shri on 2/20/2017.
 */
public class pI_Calculator extends JFrame{
    private JPanel AppGUIRootPanel;
    protected JTextField AlaTF;
    protected JTextField ArgTF;
    protected JTextField AsnTF;
    protected JTextField AspTF;
    protected JTextField CysTF;
    protected JButton calculateButton;
    private JEditorPane ValueOfpI;
    public JCheckBox AlanineN;
    protected JCheckBox AlanineC;
    protected JCheckBox ArginineN;
    protected JCheckBox AsparagineC;
    protected JCheckBox AsparagineN;
    protected JCheckBox ArginineC;
    protected JCheckBox AspartateN;
    protected JCheckBox AspartateC;
    protected JCheckBox CysteineN;
    protected JCheckBox CysteineC;
    protected JCheckBox HistidineC;
    protected JCheckBox GlutamineC;
    protected JCheckBox GlycineC;
    protected JCheckBox IsoleucineC;
    protected JCheckBox GlutamateC;
    protected JCheckBox GlutamateN;
    protected JCheckBox GlutamineN;
    protected JCheckBox GlycineN;
    protected JCheckBox HistidineN;
    protected JCheckBox IsoleucineN;
    protected JTextField GluTF;
    protected JTextField GlnTF;
    protected JTextField GlyTF;
    protected JTextField HisTF;
    protected JTextField IleTF;
    protected JCheckBox LeucineN;
    protected JCheckBox LeucineC;
    protected JCheckBox LysineC;
    protected JCheckBox MethionineC;
    protected JCheckBox PhenylalanineC;
    protected JCheckBox ProlineC;
    protected JCheckBox SerineC;
    protected JCheckBox ThreonineC;
    protected JCheckBox TryptophanC;
    protected JCheckBox TyrosineC;
    protected JCheckBox ValineC;
    protected JCheckBox LysineN;
    protected JCheckBox MethionineN;
    protected JCheckBox PhenylalanineN;
    protected JCheckBox ProlineN;
    protected JCheckBox SerineN;
    protected JCheckBox ThreonineN;
    protected JCheckBox TryptophanN;
    protected JCheckBox TyrosineN;
    protected JCheckBox ValineN;
    protected JTextField LeuTF;
    protected JTextField LysTF;
    protected JTextField MetTF;
    protected JTextField PheTF;
    protected JTextField ProTF;
    protected JTextField SerTF;
    protected JTextField ThrTF;
    protected JTextField TrpTF;
    protected JTextField TyrTF;
    protected JTextField ValTF;

    private HashMap<JCheckBox, AminoAcid> nCheckBox = new HashMap<>();
    private HashMap<JCheckBox, AminoAcid> cCheckBox = new HashMap<>();
    private HashMap<JTextField, AminoAcid> textField = new HashMap<>();



    protected JButton resetButton;

    private DataStorage dataStorage = new DataStorage();

    private pI_Calculator() {
        super("App Panel");
        initializeCheckBoxes();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    for (Map.Entry<JTextField, AminoAcid> tf : textField.entrySet()) {
                        JTextField key = tf.getKey();
                        AminoAcid val = tf.getValue();

                        if(!Objects.equals(key.getText(), "")) {
                            int howMany = Integer.parseInt(key.getText()); //number of that type of amino acid
                            dataStorage.setAA(val, howMany);
                        }
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                    dataStorage.aminoAcids.clear();
                    Calculations.getInstance().clearCalcTable();
                }
            }
        });
        calculateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dataStorage.NTerminus != 1 || dataStorage.CTerminus != 1) {
                    JOptionPane.showMessageDialog(null, "Please select only 1 N and C terminus!");
                    dataStorage.aminoAcids.clear();
                    Calculations.getInstance().clearCalcTable();
                } else {
                    ValueOfpI.setText(dataStorage.getpIVal());
                    Calculations.getInstance().clearCalcTable();
                }
            }
        });

        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Map.Entry<JCheckBox, AminoAcid> nt : nCheckBox.entrySet()) { //clear N boxes
                    nt.getKey().setSelected(false);
                }

                for (Map.Entry<JCheckBox, AminoAcid> ct : cCheckBox.entrySet()) { //clear C boxes
                    ct.getKey().setSelected(false);
                }

                for (Map.Entry<JTextField, AminoAcid> tf : textField.entrySet()) {
                    tf.getKey().setText("");
                }

                ValueOfpI.setText("");
                dataStorage.NTerminus = 0;
                dataStorage.NGroup = null;
                dataStorage.CTerminus = 0;
                dataStorage.CGroup = null;
            }
        });


        MouseAdapter nTerminus = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataStorage.NTerminus = 0;
                for (Map.Entry<JCheckBox, AminoAcid> nt : nCheckBox.entrySet()) {
                    JCheckBox key = nt.getKey();
                    AminoAcid val = nt.getValue();

                    if (key.isSelected()) {
                        dataStorage.NGroup = val;
                        dataStorage.NTerminus++;
                    }
                }


            }
        };

        for (Map.Entry<JCheckBox, AminoAcid> nt : nCheckBox.entrySet()) { //add n checkbox mouse listners
            nt.getKey().addMouseListener(nTerminus);
        }


        MouseAdapter cTerminus = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dataStorage.CTerminus = 0;
                for (Map.Entry<JCheckBox, AminoAcid> ct : cCheckBox.entrySet()) {
                    JCheckBox key = ct.getKey();
                    AminoAcid val = ct.getValue();

                    if (key.isSelected()) {
                        dataStorage.CGroup = val;
                        dataStorage.CTerminus++;
                    }
                }
            }
        };
        for (Map.Entry<JCheckBox, AminoAcid> ct : cCheckBox.entrySet()) { //add c checkbox mouse listeners
            ct.getKey().addMouseListener(cTerminus);
        }
    }

    private void initializeCheckBoxes() {
        nCheckBox.put(AlanineN, AminoAcid.ALA);
        nCheckBox.put(ArginineN, AminoAcid.ARG);
        nCheckBox.put(AsparagineN, AminoAcid.ASN);
        nCheckBox.put(AspartateN, AminoAcid.ASP);
        nCheckBox.put(CysteineN, AminoAcid.CYS);
        nCheckBox.put(GlutamateN, AminoAcid.GLU);
        nCheckBox.put(GlutamineN, AminoAcid.GLN);
        nCheckBox.put(GlycineN, AminoAcid.GLY);
        nCheckBox.put(HistidineN, AminoAcid.HIS);
        nCheckBox.put(IsoleucineN, AminoAcid.ILE);
        nCheckBox.put(LeucineN, AminoAcid.LEU);
        nCheckBox.put(LysineN, AminoAcid.LYS);
        nCheckBox.put(MethionineN, AminoAcid.MET);
        nCheckBox.put(PhenylalanineN, AminoAcid.PHE);
        nCheckBox.put(ProlineN, AminoAcid.PRO);
        nCheckBox.put(SerineN, AminoAcid.SER);
        nCheckBox.put(ThreonineN, AminoAcid.THR);
        nCheckBox.put(TryptophanN, AminoAcid.TRP);
        nCheckBox.put(TyrosineN, AminoAcid.TYR);
        nCheckBox.put(ValineN, AminoAcid.VAL);

        cCheckBox.put(AlanineC, AminoAcid.ALA);
        cCheckBox.put(ArginineC, AminoAcid.ARG);
        cCheckBox.put(AsparagineC, AminoAcid.ASN);
        cCheckBox.put(AspartateC, AminoAcid.ASP);
        cCheckBox.put(CysteineC, AminoAcid.CYS);
        cCheckBox.put(GlutamateC, AminoAcid.GLU);
        cCheckBox.put(GlutamineC, AminoAcid.GLN);
        cCheckBox.put(GlycineC, AminoAcid.GLY);
        cCheckBox.put(HistidineC, AminoAcid.HIS);
        cCheckBox.put(IsoleucineC, AminoAcid.ILE);
        cCheckBox.put(LeucineC, AminoAcid.LEU);
        cCheckBox.put(LysineC, AminoAcid.LYS);
        cCheckBox.put(MethionineC, AminoAcid.MET);
        cCheckBox.put(PhenylalanineC, AminoAcid.PHE);
        cCheckBox.put(ProlineC, AminoAcid.PRO);
        cCheckBox.put(SerineC, AminoAcid.SER);
        cCheckBox.put(ThreonineC, AminoAcid.THR);
        cCheckBox.put(TryptophanC, AminoAcid.TRP);
        cCheckBox.put(TyrosineC, AminoAcid.TYR);
        cCheckBox.put(ValineC, AminoAcid.VAL);

        textField.put(AlaTF, AminoAcid.ALA);
        textField.put(ArgTF, AminoAcid.ARG);
        textField.put(AsnTF, AminoAcid.ASN);
        textField.put(AspTF, AminoAcid.ASP);
        textField.put(CysTF, AminoAcid.CYS);
        textField.put(GluTF, AminoAcid.GLU);
        textField.put(GlnTF, AminoAcid.GLN);
        textField.put(GlyTF, AminoAcid.GLY);
        textField.put(HisTF, AminoAcid.HIS);
        textField.put(IleTF, AminoAcid.ILE);
        textField.put(LeuTF, AminoAcid.LEU);
        textField.put(LysTF, AminoAcid.LYS);
        textField.put(MetTF, AminoAcid.MET);
        textField.put(PheTF, AminoAcid.PHE);
        textField.put(ProTF, AminoAcid.PRO);
        textField.put(SerTF, AminoAcid.SER);
        textField.put(ThrTF, AminoAcid.THR);
        textField.put(TrpTF, AminoAcid.TRP);
        textField.put(TyrTF, AminoAcid.TYR);
        textField.put(ValTF, AminoAcid.VAL);
    }

    /** for testing purposes
    private void visPrint(TreeMap<Double, ArrayList<Double>> temp) { //to print a visually friendly tree map
        for (Object o : temp.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println(pair.getKey() + "=" + pair.getValue());
        }
    }
     */

    public static void main(String[] args) {
        JFrame frame = new JFrame("pI_Calculator");
        frame.setContentPane(new pI_Calculator().AppGUIRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
