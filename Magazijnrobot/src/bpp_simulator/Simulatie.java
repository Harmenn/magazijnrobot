package bpp_simulator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import bpp_simulator.algoritmes.Algoritme;
import bpp_simulator.algoritmes.Bestfit;
import bpp_simulator.algoritmes.Bruteforce;
import bpp_simulator.algoritmes.EigenAlgoritme;
import bpp_simulator.algoritmes.Firstfit;
import bpp_simulator.algoritmes.Nextfit;

public class Simulatie extends javax.swing.JFrame implements MouseListener, ActionListener, Runnable {

    private ArrayList<Product> ArrayProducts = new ArrayList<>();
    private final ArrayList<Algoritme> ArrayAlgoritmes = new ArrayList<>();
    private final ArrayList<Resultaat> ArrayResults = new ArrayList<>();
    private final int BoxSize, vol;
    private StringBuilder endResult = new StringBuilder();
    private Thread t;
    private Bruteforce BruteForceAlgoritme;
    private Nextfit NextFitAlgoritme;
    private Firstfit FirstFitAlgoritme;
    private Bestfit BestFitAlgoritme;
    private EigenAlgoritme EigenAlgoritme;
    private Resultaat BruteForceResult, NextFitResult, FirstFitResult, BestFitResult, EigenFitResult;
    private final SelectieScherm selectieScherm;

    public Simulatie(SelectieScherm selectieScherm, ArrayList<Product> ArrayProducts, int BoxSize, boolean[] Algorithms) {
        initComponents();
        this.selectieScherm = selectieScherm;
        this.ArrayProducts = ArrayProducts;
        this.BoxSize = BoxSize;
        if (Algorithms[0]) {
            jlNextFitStatus.setText("In wachtrij");
            NextFitAlgoritme = new Nextfit();
            ArrayAlgoritmes.add(NextFitAlgoritme);
        }
        if (Algorithms[1]) {
            jlFirstFitStatus.setText("In wachtrij");
            FirstFitAlgoritme = new Firstfit();
            ArrayAlgoritmes.add(FirstFitAlgoritme);
        }
        if (Algorithms[2]) {
            jlBestFitStatus.setText("In wachtrij");
            BestFitAlgoritme = new Bestfit();
            ArrayAlgoritmes.add(BestFitAlgoritme);
        }
        if (Algorithms[3]) {
            jlEigenFitStatus.setText("In wachtrij");
            EigenAlgoritme = new EigenAlgoritme();
            ArrayAlgoritmes.add(EigenAlgoritme);
        }
        if (Algorithms[4]) {
            jlBruteforceStatus.setText("In wachtrij");
            BruteForceAlgoritme = new Bruteforce(ArrayProducts, BoxSize);
            ArrayAlgoritmes.add(BruteForceAlgoritme);
        }
        // Action listeners voor de knoppen
        jbCancel.addActionListener(this);
        jbSave.addActionListener(this);
        jbContinue.addActionListener(this);
        
        // Totaal volume verkrijgen
        vol = getVolume();
        jlTotalVolumeProducts.setText(Integer.toString(vol));
        setVisible(true);
        
        // Thread starten
        StartThread();
        
        // Eind resultaat instellen voor het opslaan
        endResult.append("Naam Algoritme;Aantal dozen;Tijd (ms)\n\n");

    }

    private void StartThread() {
        if (t == null) {
            t = new Thread(this, "StartSimulatie");
            t.start();
        }
    }

    // Opslaan van resultaat, met keuze voor de map waar de bestanden heen moeten
    private void SaveResults() {
        PrintWriter pwFileWriter = null;
        JFileChooser jfChooser;
        jfChooser = new JFileChooser();
        jfChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfChooser.setDialogTitle("Selecteer map");
        if (jfChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String location = jfChooser.getCurrentDirectory().getAbsolutePath() + "\\" + jfChooser.getSelectedFile().getName();
            try {
                pwFileWriter = new PrintWriter(new File(location + "\\Resultaat.csv"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Er ging iets mis tijdens het opslaan", "Foutmelding",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            pwFileWriter.write(endResult.toString());
            pwFileWriter.close();
            if (BruteForceResult != null) {
                BruteForceResult.SaveScreen(location + "\\Bruteforce.png");
            }
            if (NextFitResult != null) {
                NextFitResult.SaveScreen(location + "\\Nextfit.png");
            }
            if (BestFitResult != null) {
                BestFitResult.SaveScreen(location + "\\Bestfit.png");
            }
            if (FirstFitResult != null) {
                FirstFitResult.SaveScreen(location + "\\Firstfit.png");
            }
            if (EigenFitResult != null) {
                EigenFitResult.SaveScreen(location + "\\EigenFit.png");
            }
            try {
                Desktop.getDesktop().open(new File(location));
            } catch (IOException ex) {
            }
        }
    }

    // Voeg resultaten toe aan de stringbuilder
    private void AppendResultaat(String naam, int grootte, long tijdsduur) {
        endResult.append(naam).append(";").append(grootte).append(";").append(tijdsduur).append("\n");
    }

    // Voeg resultaten toe aan de stringbuilder
    private void AppendResultaat(String tekst, String naam, String result) {
        endResult.append(tekst).append(";").append(naam).append(";").append(result).append("\n");
    }

    // Functie om een hyperlink van een label te maken
    // http://stackoverflow.com/questions/15892844/underlined-jlabel
    private void MakeHyperlink(javax.swing.JLabel label) {
        label.setText("Bekijk resultaat");
        label.setForeground(Color.blue);
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
        label.addMouseListener(this);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Functie om de simulatie te starten. Deze wordt uitgevoerd binnen de thread
    private void StartSimulation() {
        ArrayList<Bin> bins = new ArrayList<>();
        long nu, tijdsduur;
        for (Algoritme alg : ArrayAlgoritmes) {
            nu = Instant.now().toEpochMilli();
            jpProgress.setIndeterminate(true);
            if (alg instanceof Nextfit) {
                jlNextFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (NextFitAlgoritme.start(ArrayProducts, BoxSize));
                jlNextFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                ArrayResults.add(NextFitResult = new Resultaat(bins, alg, vol, BoxSize));
                MakeHyperlink(jlNextFitStatus);
            }
            if (alg instanceof Firstfit) {
                jlFirstFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (FirstFitAlgoritme.start(ArrayProducts, BoxSize));
                jlFirstFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                ArrayResults.add(FirstFitResult = new Resultaat(bins, alg, vol, BoxSize));
                MakeHyperlink(jlFirstFitStatus);
            }
            if (alg instanceof Bestfit) {
                jlBestFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (BestFitAlgoritme.start(ArrayProducts, BoxSize));
                jlBestFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                ArrayResults.add(BestFitResult = new Resultaat(bins, alg, vol, BoxSize));
                MakeHyperlink(jlBestFitStatus);
            }
            if (alg instanceof EigenAlgoritme) {
                nu = Instant.now().toEpochMilli();
                jlEigenFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (EigenAlgoritme.start(ArrayProducts, BoxSize));
                jlEigenFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                ArrayResults.add(EigenFitResult = new Resultaat(bins, alg, vol, BoxSize));
                MakeHyperlink(jlEigenFitStatus);
            }
            if (alg instanceof Bruteforce) {
                nu = Instant.now().toEpochMilli();
                jlBruteforceStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (BruteForceAlgoritme.start());
                jlBruteforceStatus.setText("Voltooid");
                MakeHyperlink(jlBruteforceStatus);
                alg.setBins(bins.size());
                ArrayResults.add(BruteForceResult = new Resultaat(bins, alg, vol, BoxSize));
            }
            tijdsduur = Instant.now().toEpochMilli() - nu;
            AppendResultaat(alg.getName(), bins.size(), tijdsduur);
            alg.setEndTime(tijdsduur);
        }
        jpProgress.setIndeterminate(false);
        jpProgress.setValue(100);
        setTitle("Bin Packing Problem Simulation - Voltooid");
        jlCurrentSimulation.setText("Voltooid");
        jbCancel.setEnabled(false);
        jbSave.setEnabled(true);
        jbContinue.setEnabled(true);
        CalculateAlgorithms();
    }

    // Verkrijg totale volume van producten
    private int getVolume() {
        int v = 0;
        for (Product ArrayProduct : ArrayProducts) {
            v += ArrayProduct.getLength();
        }
        return v;
    }

    // Functie om de algoritmes te calculeren op basis van tijd en grootte
    private void CalculateAlgorithms() {
        long fastestTime = -1, eft = -1;
        int bins = -1, fbins = -1;
        String fastestAlg = "", EfficientAlg = "";
        for (Resultaat result : ArrayResults) {
            if (fastestTime == -1 && bins == -1) {
                fastestTime = result.getAlgoritme().getEndTime();
                bins = result.getBins().size();
            }
            if (fastestTime >= result.getAlgoritme().getEndTime()) {
                fastestTime = result.getAlgoritme().getEndTime();
                fastestAlg = result.getAlgoritme().getName();
                fbins = result.getAlgoritme().getBins();
            }
            if (bins >= result.getBins().size()) {
                bins = result.getBins().size();
                EfficientAlg = result.getAlgoritme().getName();
                eft = result.getAlgoritme().getEndTime();
            }
        }
        jlFastestAlgorithm.setText(fastestAlg + " (" + fastestTime + "ms)");
        endResult.append("\nSnelste algoritme:\n");
        AppendResultaat(fastestAlg, Integer.toString(fbins), Long.toString(fastestTime));
        endResult.append("\nEfficientste algoritme:\n");
        jlEfficientAlgorithm.setText(EfficientAlg + " (" + bins + " dozen)");
        AppendResultaat(EfficientAlg, bins, eft);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpProgress = new javax.swing.JProgressBar();
        jbCancel = new javax.swing.JButton();
        jpPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlFastestAlgorithm = new javax.swing.JLabel();
        jlEfficientAlgorithm = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlTotalVolumeProducts = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlBruteforceStatus = new javax.swing.JLabel();
        jlNextFitStatus = new javax.swing.JLabel();
        jlFirstFitStatus = new javax.swing.JLabel();
        jlBestFitStatus = new javax.swing.JLabel();
        jlCurrentSimulation = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlEigenFitStatus = new javax.swing.JLabel();
        jbSave = new javax.swing.JButton();
        jbContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bin Packing Problem Simulation - Bezig");
        setResizable(false);

        jLabel5.setText("Brute force");

        jLabel1.setText("Next fit");

        jLabel2.setText("First fit");

        jLabel3.setText("Best fit");

        jpProgress.setForeground(new java.awt.Color(0, 204, 0));
        jpProgress.setValue(50);

        jbCancel.setText("Annuleren");

        jpPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Resultaten:");

        jLabel8.setText("Snelste algoritme:");

        jLabel9.setText("Efficiëntste algoritme:");

        jlFastestAlgorithm.setText("Wordt berekend...");

        jlEfficientAlgorithm.setText("Wordt berekend...");

        jLabel10.setText("Totaal volume producten:");

        jlTotalVolumeProducts.setText("0");

        javax.swing.GroupLayout jpPanelLayout = new javax.swing.GroupLayout(jpPanel);
        jpPanel.setLayout(jpPanelLayout);
        jpPanelLayout.setHorizontalGroup(
            jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlEfficientAlgorithm, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jlFastestAlgorithm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTotalVolumeProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPanelLayout.setVerticalGroup(
            jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jlFastestAlgorithm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jlEfficientAlgorithm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jlTotalVolumeProducts))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jLabel6.setText("Simulatie met algoritme: ");

        jlBruteforceStatus.setText("Wordt niet uitgevoerd");

        jlNextFitStatus.setText("Wordt niet uitgevoerd");

        jlFirstFitStatus.setText("Wordt niet uitgevoerd");

        jlBestFitStatus.setText("Wordt niet uitgevoerd");

        jlCurrentSimulation.setText("x");

        jLabel4.setText("Eigen fit");

        jlEigenFitStatus.setText("Wordt niet uitgevoerd");

        jbSave.setText("Resultaten opslaan");
        jbSave.setEnabled(false);

        jbContinue.setText("Doorgaan");
        jbContinue.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbContinue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlEigenFitStatus)
                                    .addComponent(jlBruteforceStatus)
                                    .addComponent(jlNextFitStatus)
                                    .addComponent(jlFirstFitStatus)
                                    .addComponent(jlBestFitStatus)))
                            .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlCurrentSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlCurrentSimulation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jlNextFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jlFirstFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jlBestFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jlEigenFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jlBruteforceStatus))
                        .addGap(153, 153, 153)
                        .addComponent(jbSave))
                    .addComponent(jpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbCancel)
                        .addComponent(jbContinue))
                    .addComponent(jpProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbContinue;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlBestFitStatus;
    private javax.swing.JLabel jlBruteforceStatus;
    private javax.swing.JLabel jlCurrentSimulation;
    private javax.swing.JLabel jlEfficientAlgorithm;
    private javax.swing.JLabel jlEigenFitStatus;
    private javax.swing.JLabel jlFastestAlgorithm;
    private javax.swing.JLabel jlFirstFitStatus;
    private javax.swing.JLabel jlNextFitStatus;
    private javax.swing.JLabel jlTotalVolumeProducts;
    private javax.swing.JPanel jpPanel;
    private javax.swing.JProgressBar jpProgress;
    // End of variables declaration//GEN-END:variables

    // Required overrides
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jlBruteforceStatus) {
            BruteForceResult.setVisible(true);
        } else if (e.getSource() == jlBestFitStatus) {
            BestFitResult.setVisible(true);
        } else if (e.getSource() == jlNextFitStatus) {
            NextFitResult.setVisible(true);
        } else if (e.getSource() == jlEigenFitStatus) {
            EigenFitResult.setVisible(true);
        } else if (e.getSource() == jlFirstFitStatus) {
            FirstFitResult.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {
        StartSimulation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbCancel) {
            t.stop();
            jpProgress.setForeground(Color.red);
            jpProgress.setValue(100);
            jpProgress.setIndeterminate(false);
            jlCurrentSimulation.setText("Geannuleerd");
            jbSave.setEnabled(true);
            jbContinue.setEnabled(true);
            jbCancel.setEnabled(false);
            setTitle("Bin Packing Problem Simulation - Geannuleerd");
        } else if (e.getSource() == jbSave) {
            SaveResults();
        } else if (e.getSource() == jbContinue) {
            this.setVisible(false);
            for (Resultaat res : ArrayResults) {
                res = null;
            }
            ArrayResults.removeAll(ArrayResults);
            endResult = null;
            selectieScherm.setVisible(true);
            dispose();
        }
    }
}
