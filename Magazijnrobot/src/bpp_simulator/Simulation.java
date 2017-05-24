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

import bpp_simulator.algorithms.Algorithm;
import bpp_simulator.algorithms.Bestfit;
import bpp_simulator.algorithms.Bruteforce;
import bpp_simulator.algorithms.OwnFit;
import bpp_simulator.algorithms.Firstfit;
import bpp_simulator.algorithms.Nextfit;

public class Simulation extends javax.swing.JFrame implements MouseListener, ActionListener, Runnable {

    private ArrayList<Product> arrayProducts = new ArrayList<>();
    private final ArrayList<Algorithm> arrayAlgorithms = new ArrayList<>();
    private final ArrayList<Result> arrayResults = new ArrayList<>();
    private final int boxSize, vol;
    private StringBuilder endResult = new StringBuilder();
    private Thread t;
    private Bruteforce bruteForceAlgorithm;
    private Nextfit nextFitAlgorithm;
    private Firstfit firstFitAlgorithm;
    private Bestfit bestFitAlgorithm;
    private OwnFit ownFitAlgorithm;
    private Result bruteForceResult, nextFitResult, firstFitResult, bestFitResult, ownFitResult;
    private final SelectieScherm selectieScherm;

    public Simulation(SelectieScherm selectieScherm, ArrayList<Product> arrayProducts, int boxSize, boolean[] algorithms) {
        initComponents();
        this.selectieScherm = selectieScherm;
        this.arrayProducts = arrayProducts;
        this.boxSize = boxSize;

        // Hier was vast een makkelijkere oplossing voor, maar voor nu wordt er door een array van booleans heen gelopen.
        // Deze wordt meegegeven vanuit het selectieScherm.
        if (algorithms[0]) {
            jlNextFitStatus.setText("In wachtrij");
            nextFitAlgorithm = new Nextfit();
            arrayAlgorithms.add(nextFitAlgorithm);
        }
        if (algorithms[1]) {
            jlFirstFitStatus.setText("In wachtrij");
            firstFitAlgorithm = new Firstfit();
            arrayAlgorithms.add(firstFitAlgorithm);
        }
        if (algorithms[2]) {
            jlBestFitStatus.setText("In wachtrij");
            bestFitAlgorithm = new Bestfit();
            arrayAlgorithms.add(bestFitAlgorithm);
        }
        if (algorithms[3]) {
            jlEigenFitStatus.setText("In wachtrij");
            ownFitAlgorithm = new OwnFit();
            arrayAlgorithms.add(ownFitAlgorithm);
        }
        if (algorithms[4]) {
            jlBruteforceStatus.setText("In wachtrij");
            bruteForceAlgorithm = new Bruteforce(arrayProducts, boxSize);
            arrayAlgorithms.add(bruteForceAlgorithm);
        }
        // Action listeners voor de knoppen
        jbCancel.addActionListener(this);
        jbSave.addActionListener(this);
        jbContinue.addActionListener(this);

        // Totaal volume verkrijgen
        vol = getVolume();
        jlTotalVolumeProducts.setText(Integer.toString(vol));
        setVisible(true);

        // Thread starten, starten met simulatie
        StartThread();

        // Eind resultaat instellen voor het opslaan
        endResult.append("Naam Algoritme;Aantal dozen;Tijd (ms)\n\n");

        // WindowListener om te kijke of er op het sluit knopje wordt geklikt.
        // Om te voorkomen dat de simulatie per ongeluk weg wordt geklikt.
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (t.isAlive()) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Weet je zeker dat je de simulatie wilt stoppen?", "Simulatie stoppen",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        ExitWindow();

                    }
                } else {
                    ExitWindow();
                }
            }
        });
    }

    // Starten van de Simulation Thread
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
            for (Result result : arrayResults) {
                result.SaveScreen(location + "\\" + result.getAlgoritme().getName() + ".png");
            }
            try {
                Desktop.getDesktop().open(new File(location));
            } catch (IOException ex) {
            }
        }
    }

    // Voeg resultaten toe aan de stringbuilder
    private void appendResult(String naam, int grootte, long tijdsduur) {
        endResult.append(naam).append(";").append(grootte).append(";").append(tijdsduur).append("\n");
    }

    // Voeg resultaten toe aan de stringbuilder
    private void appendResult(String tekst, String naam, String result) {
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
        long timeNow, timeElapsed;
        for (Algorithm alg : arrayAlgorithms) {
            timeNow = Instant.now().toEpochMilli();
            jpProgress.setIndeterminate(true);
            if (alg instanceof Nextfit) {
                jlNextFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (nextFitAlgorithm.start(arrayProducts, boxSize));
                jlNextFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                arrayResults.add(nextFitResult = new Result(bins, alg, vol, boxSize));
                MakeHyperlink(jlNextFitStatus);
            }
            if (alg instanceof Firstfit) {
                jlFirstFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (firstFitAlgorithm.start(arrayProducts, boxSize));
                jlFirstFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                arrayResults.add(firstFitResult = new Result(bins, alg, vol, boxSize));
                MakeHyperlink(jlFirstFitStatus);
            }
            if (alg instanceof Bestfit) {
                jlBestFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (bestFitAlgorithm.start(arrayProducts, boxSize));
                jlBestFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                arrayResults.add(bestFitResult = new Result(bins, alg, vol, boxSize));
                MakeHyperlink(jlBestFitStatus);
            }
            if (alg instanceof OwnFit) {
                timeNow = Instant.now().toEpochMilli();
                jlEigenFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (ownFitAlgorithm.start(arrayProducts, boxSize));
                jlEigenFitStatus.setText("Voltooid");
                alg.setBins(bins.size());
                arrayResults.add(ownFitResult = new Result(bins, alg, vol, boxSize));
                MakeHyperlink(jlEigenFitStatus);
            }
            if (alg instanceof Bruteforce) {
                timeNow = Instant.now().toEpochMilli();
                jlBruteforceStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(alg.getName());
                bins = (bruteForceAlgorithm.start());
                jlBruteforceStatus.setText("Voltooid");
                MakeHyperlink(jlBruteforceStatus);
                alg.setBins(bins.size());
                arrayResults.add(bruteForceResult = new Result(bins, alg, vol, boxSize));
            }
            timeElapsed = Instant.now().toEpochMilli() - timeNow;
            appendResult(alg.getName(), bins.size(), timeElapsed);
            alg.setEndTime(timeElapsed);
        }
        jpProgress.setIndeterminate(false);
        jpProgress.setValue(100);
        setTitle("Bin Packing Problem Simulation - Voltooid");
        jlCurrentSimulation.setText("Voltooid");
        jbCancel.setEnabled(false);
        jbSave.setEnabled(true);
        jbContinue.setEnabled(true);
        calculateAlgorithms();
    }

    // Verkrijg totale volume van producten
    private int getVolume() {
        int v = 0;
        for (Product ArrayProduct : arrayProducts) {
            v += ArrayProduct.getLength();
        }
        return v;
    }

    // Functie om de algoritmes te calculeren op basis van tijd en grootte
    private void calculateAlgorithms() {
        long fastestTime = -1, eft = -1;
        int bins = -1, fbins = -1;
        String fastestAlg = "", efficientAlg = "";
        for (Result result : arrayResults) {
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
                efficientAlg = result.getAlgoritme().getName();
                eft = result.getAlgoritme().getEndTime();
            }
        }
        jlFastestAlgorithm.setText(fastestAlg + " (" + fastestTime + "ms)");
        endResult.append("\nSnelste algoritme:\n");
        appendResult(fastestAlg, Integer.toString(fbins), Long.toString(fastestTime));
        endResult.append("\nEfficientste algoritme:\n");
        jlEfficientAlgorithm.setText(efficientAlg + " (" + bins + " dozen)");
        appendResult(efficientAlg, bins, eft);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
            bruteForceResult.setVisible(true);
        } else if (e.getSource() == jlBestFitStatus) {
            bestFitResult.setVisible(true);
        } else if (e.getSource() == jlNextFitStatus) {
            nextFitResult.setVisible(true);
        } else if (e.getSource() == jlEigenFitStatus) {
            ownFitResult.setVisible(true);
        } else if (e.getSource() == jlFirstFitStatus) {
            firstFitResult.setVisible(true);
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
            // Annuleren knop, zorgt ervoor dat de thread gestopt wordt en de simulatie gecancelled
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
            // Opslaan van resultaten
            SaveResults();
        } else if (e.getSource() == jbContinue) {
            // Weer terug naar selectiescherm
            ExitWindow();
        }
    }

    // Deze functie zorgt ervoor dat de window netjes wordt gesloten
    public void ExitWindow() {
        this.setVisible(false);
        for (Result res : arrayResults) {
            res = null;
        }
        arrayResults.removeAll(arrayResults);
        endResult = null;
        selectieScherm.setVisible(true);
        dispose();
    }
}
