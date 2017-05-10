package bpp_simulator;

import bpp_simulator.algoritmes.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.time.Instant;
import java.util.*;
import javax.swing.JFileChooser;

public class Simulatie extends javax.swing.JFrame implements MouseListener, ActionListener, Runnable {

    private ArrayList<Product> ArrayProducts = new ArrayList<>();
    private int BoxSize;
    private StringBuilder endResult = new StringBuilder();

    private Thread t;
    private Algoritme Algoritmes;
    private Bruteforce BruteForceAlgoritme;
    private Nextfit NextFitAlgoritme;
    private Firstfit FirstFitAlgoritme;
    private Bestfit BestFitAlgoritme;
    private EigenAlgoritme EigenAlgoritme;
    private Resultaat BruteForceResult, NextFitResult, FirstFitResult, BestFitResult, EigenFitResult;

    public Simulatie(ArrayList<Product> ArrayProducts, int BoxSize, boolean BruteForceEnabled, boolean NextFitEnabled, boolean FirstFitEnabled, boolean BestFitEnabled, boolean EigenAlgoritmeEnabled) {
        initComponents();
        setResizable(false);

        this.ArrayProducts = ArrayProducts;
        this.BoxSize = BoxSize;
        Algoritmes = new Algoritme();
        if (NextFitEnabled) {
            jlNextFitStatus.setText("In wachtrij");
            NextFitAlgoritme = new Nextfit();
            Algoritmes.addAlgoritme(NextFitAlgoritme);
        }
        if (FirstFitEnabled) {
            jlFirstFitStatus.setText("In wachtrij");
            FirstFitAlgoritme = new Firstfit();
            Algoritmes.addAlgoritme(FirstFitAlgoritme);
        }
        if (BestFitEnabled) {
            jlBestFitStatus.setText("In wachtrij");
            BestFitAlgoritme = new Bestfit();
            Algoritmes.addAlgoritme(BestFitAlgoritme);
        }
        if (EigenAlgoritmeEnabled) {
            jlEigenFitStatus.setText("In wachtrij");
            EigenAlgoritme = new EigenAlgoritme();
            Algoritmes.addAlgoritme(EigenAlgoritme);
        }
        if (BruteForceEnabled) {
            jlBruteforceStatus.setText("In wachtrij");
            BruteForceAlgoritme = new Bruteforce(ArrayProducts, BoxSize);
            Algoritmes.addAlgoritme(BruteForceAlgoritme);
        }
        jbCancel.addActionListener(this);
        jbSave.addActionListener(this);
        setVisible(true);
        if (t == null) {
            t = new Thread(this, "StartSimulatie");
            t.start();
        }
        endResult.append("Naam Algoritme");
        endResult.append(';');
        endResult.append("Aantal dozen");
        endResult.append(';');
        endResult.append("Tijd (ms)");
        endResult.append('\n');
        endResult.append('\n');
    }

    public int getBoxSize() {
        return BoxSize;
    }

    private void SaveResults() {
        PrintWriter pwFileWriter = null;
        JFileChooser jfChooser;
        jfChooser = new JFileChooser();
        jfChooser.setCurrentDirectory(new java.io.File("."));
        jfChooser.setDialogTitle("Selecteer map");
        jfChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfChooser.setAcceptAllFileFilterUsed(false);
        if (jfChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String locatie = jfChooser.getCurrentDirectory().getAbsolutePath() + "\\" + jfChooser.getSelectedFile().getName();
            try {
                pwFileWriter = new PrintWriter(new File(locatie + "\\Resultaat.csv"));
            } catch (FileNotFoundException ex) {
                System.out.println("Er ging iets mis");
            }
            pwFileWriter.write(endResult.toString());
            pwFileWriter.close();
            if (BruteForceResult != null) {
                BruteForceResult.setVisible(true);
                BruteForceResult.SaveScreen(locatie + "\\bruteforce.png");
                BruteForceResult.setVisible(false);
            }
            if (NextFitResult != null) {
                NextFitResult.setVisible(true);
                NextFitResult.SaveScreen(locatie + "\\Nextfit.png");
                NextFitResult.setVisible(false);
            }
        }
    }

    private void AppendResultaat(String naam, int grootte, long tijdsduur) {
        endResult.append(naam);
        endResult.append(';');
        endResult.append(grootte);
        endResult.append(';');
        endResult.append(tijdsduur);
        endResult.append('\n');
    }

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

    private void StartSimulation() {
        ArrayList<Bin> bins = new ArrayList<>();
        int vol = getVolume();
        long nu, tijdsduur;
        for (Algoritme Algoritme1 : Algoritmes.getAlgoritmes()) {
            nu = Instant.now().toEpochMilli();
            if (Algoritme1 instanceof Nextfit) {
                Algoritme1.setName("Nextfit");
                jlNextFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(Algoritme1.getName());

                bins = (NextFitAlgoritme.start(ArrayProducts, BoxSize));
                jlNextFitStatus.setText("Voltooid");
                NextFitResult = new Resultaat(bins, Algoritme1, vol, bins.size() * BoxSize);
                MakeHyperlink(jlNextFitStatus);
            }
            if (Algoritme1 instanceof Firstfit) {
                Algoritme1.setName("Firstfit");
                jlFirstFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(Algoritme1.getName());
                bins = (FirstFitAlgoritme.start(ArrayProducts, BoxSize));
                jlFirstFitStatus.setText("Voltooid");
                FirstFitResult = new Resultaat(bins, Algoritme1, vol, bins.size() * BoxSize);
                MakeHyperlink(jlFirstFitStatus);
            }
            if (Algoritme1 instanceof Bestfit) {
                Algoritme1.setName("Bestfit");
                jlBestFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(Algoritme1.getName());
                bins = (BestFitAlgoritme.start(ArrayProducts, BoxSize));
                jlBestFitStatus.setText("Voltooid");
                BestFitResult = new Resultaat(bins, Algoritme1, vol, bins.size() * BoxSize);
                MakeHyperlink(jlBestFitStatus);
            }
            if (Algoritme1 instanceof EigenAlgoritme) {
                Algoritme1.setName("Eigenfit");
                nu = Instant.now().toEpochMilli();
                jlEigenFitStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(Algoritme1.getName());
                bins = (EigenAlgoritme.start(ArrayProducts, BoxSize));
                jlEigenFitStatus.setText("Voltooid");
                EigenFitResult = new Resultaat(bins, Algoritme1, vol, bins.size() * BoxSize);
                MakeHyperlink(jlEigenFitStatus);
            }
            if (Algoritme1 instanceof Bruteforce) {
                Algoritme1.setName("Bruteforce");
                nu = Instant.now().toEpochMilli();
                jlBruteforceStatus.setText("Uitvoeren...");
                jlCurrentSimulation.setText(Algoritme1.getName());
                bins = (BruteForceAlgoritme.start());
                jlBruteforceStatus.setText("Voltooid");
                MakeHyperlink(jlBruteforceStatus);
                BruteForceResult = new Resultaat(bins, Algoritme1, vol, bins.size() * BoxSize);
            }
            jpProgress.setIndeterminate(true);
            tijdsduur = Instant.now().toEpochMilli() - nu;
            AppendResultaat(Algoritme1.getName(), bins.size(), tijdsduur);
            Algoritme1.setEndTime(tijdsduur);
        }
        CalculateAlgorithms();
        jpProgress.setIndeterminate(false);
        jpProgress.setValue(100);
        setTitle("Bin Packing Problem Simulation - Voltooid");
        jlCurrentSimulation.setText("Voltooid");
        System.out.println(endResult);
        jbCancel.setEnabled(false);
        jbSave.setEnabled(true);
    }

    private int getVolume() {
        int vol = 0;
        for (Product product : ArrayProducts) {
            vol += product.getLength();
        }
        return vol;
    }

    @SuppressWarnings("unchecked")
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
        jlBestAlgorithm = new javax.swing.JLabel();
        jlFastestAlgorithm = new javax.swing.JLabel();
        jlEfficientAlgorithm = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlBruteforceStatus = new javax.swing.JLabel();
        jlNextFitStatus = new javax.swing.JLabel();
        jlFirstFitStatus = new javax.swing.JLabel();
        jlBestFitStatus = new javax.swing.JLabel();
        jlCurrentSimulation = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlEigenFitStatus = new javax.swing.JLabel();
        jbSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bin Packing Problem Simulation - Bezig");

        jLabel5.setText("Brute force");

        jLabel1.setText("Next fit");

        jLabel2.setText("First fit");

        jLabel3.setText("Best fit");

        jpProgress.setForeground(new java.awt.Color(0, 204, 0));
        jpProgress.setValue(50);

        jbCancel.setText("Annuleren");

        jpPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Beste algoritme:");

        jLabel8.setText("Snelste algoritme:");

        jLabel9.setText("Efficiëntste algoritme:");

        jlBestAlgorithm.setText("jLabel10");

        jlFastestAlgorithm.setText("jLabel10");

        jlEfficientAlgorithm.setText("jLabel10");

        javax.swing.GroupLayout jpPanelLayout = new javax.swing.GroupLayout(jpPanel);
        jpPanel.setLayout(jpPanelLayout);
        jpPanelLayout.setHorizontalGroup(
            jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlEfficientAlgorithm))
                    .addGroup(jpPanelLayout.createSequentialGroup()
                        .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(32, 32, 32)
                        .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlBestAlgorithm)
                            .addComponent(jlFastestAlgorithm))))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jpPanelLayout.setVerticalGroup(
            jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jlBestAlgorithm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jlFastestAlgorithm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jlEfficientAlgorithm))
                .addContainerGap(209, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                    .addComponent(jbCancel)
                    .addComponent(jpProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlBestAlgorithm;
    private javax.swing.JLabel jlBestFitStatus;
    private javax.swing.JLabel jlBruteforceStatus;
    private javax.swing.JLabel jlCurrentSimulation;
    private javax.swing.JLabel jlEfficientAlgorithm;
    private javax.swing.JLabel jlEigenFitStatus;
    private javax.swing.JLabel jlFastestAlgorithm;
    private javax.swing.JLabel jlFirstFitStatus;
    private javax.swing.JLabel jlNextFitStatus;
    private javax.swing.JPanel jpPanel;
    private javax.swing.JProgressBar jpProgress;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jlBruteforceStatus) {
            if (BruteForceResult != null) {
                BruteForceResult.setVisible(true);
            }
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

    private void CalculateAlgorithms() {
        long fastestTime = 0;
        String fastestAlg = "";
        for (Algoritme alg : Algoritmes.getAlgoritmes()) {
            if (fastestTime > alg.getEndTime()) {
                fastestTime = alg.getEndTime();
                fastestAlg = alg.getName();
                System.out.println(alg.getName());
            }
            alg.getEndTime();
        }
        jlBestAlgorithm.setText("");
        jlFastestAlgorithm.setText(fastestAlg + " (" + fastestTime + ")");
        jlEfficientAlgorithm.setText("");
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
            setTitle("Bin Packing Problem Simulation - Geannuleerd");
        }
        if (e.getSource() == jbSave) {
            SaveResults();
        }
    }
}
