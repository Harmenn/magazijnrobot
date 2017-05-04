package bpp_simulator;

import java.time.Instant;
import java.util.ArrayList;

public class Simulatie extends javax.swing.JFrame {

    private ArrayList<Product> ArrayPakketten = new ArrayList<>();
    private int DoosInhoud;
    private Algoritme Algoritmes;
    private Bruteforce BruteForceAlgoritme;
    private Nextfit NextFitAlgoritme;
    private Firstfit FirstFitAlgoritme;
    private Bestfit BestFitAlgoritme;
    private EigenAlgoritme EigenAlgoritme;

    public Simulatie(ArrayList<Product> ArrayPakketten, int DoosInhoud, boolean BruteForceEnabled, boolean NextFitEnabled, boolean FirstFitEnabled, boolean BestFitEnabled, boolean EigenAlgoritmeEnabled) {
        initComponents();
        setResizable(false);

        setVisible(true);
        this.ArrayPakketten = ArrayPakketten;
        this.DoosInhoud = DoosInhoud;

        Algoritmes = new Algoritme();
        if (BruteForceEnabled) {
            jlBruteforceStatus.setText("In wachtrij");
            BruteForceAlgoritme = new Bruteforce(ArrayPakketten, DoosInhoud);
            Algoritmes.addAlgoritme(BruteForceAlgoritme);
        }
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
        StartSimulatie();
    }

    public int getDoosInhoud() {
        return DoosInhoud;
    }

    private void StartSimulatie() {
        int dozen;
        long nu, tijdsduur;
        for (Algoritme Algoritme1 : Algoritmes.getAlgoritmes()) {
            if (Algoritme1 instanceof Bruteforce) {
                nu = Instant.now().toEpochMilli();
                jlBruteforceStatus.setText("Uitvoeren...");
                jlHuidigeSimulatie.setText("Bruteforce");
                jProgressBar1.setIndeterminate(true);
                BruteForceAlgoritme.start();
                jlBruteforceStatus.setText("Voltooid");
                tijdsduur = Instant.now().toEpochMilli() - nu;
                System.out.println("Bruteforce tijd: " + tijdsduur + "ms");
            }
            if (Algoritme1 instanceof Nextfit) {
                nu = Instant.now().toEpochMilli();
                jlNextFitStatus.setText("Uitvoeren...");
                jlHuidigeSimulatie.setText("Nextfit");
                jProgressBar1.setIndeterminate(true);
                dozen = (NextFitAlgoritme.start(ArrayPakketten, DoosInhoud));
                jlNextFitStatus.setText("Voltooid");
                tijdsduur = Instant.now().toEpochMilli() - nu;
                System.out.println("Nextfit:\nAantal dozen:" + dozen + "\nTijd: " + tijdsduur + "ms\n");

            }
            if (Algoritme1 instanceof Firstfit) {
                nu = Instant.now().toEpochMilli();
                jlFirstFitStatus.setText("Uitvoeren...");
                jlHuidigeSimulatie.setText("Firstfit");
                jProgressBar1.setIndeterminate(true);
                dozen = (FirstFitAlgoritme.start(ArrayPakketten, DoosInhoud));
                jlFirstFitStatus.setText("Voltooid");
                tijdsduur = Instant.now().toEpochMilli() - nu;
                System.out.println("Firstfit:\nAantal dozen:" + dozen + "\nTijd: " + tijdsduur + "ms\n");
            }
            if (Algoritme1 instanceof Bestfit) {
                nu = Instant.now().toEpochMilli();
                jlBestFitStatus.setText("Uitvoeren...");
                jlHuidigeSimulatie.setText("Bestfit");
                jProgressBar1.setIndeterminate(true);
                dozen = (BestFitAlgoritme.start(ArrayPakketten, DoosInhoud));
                jlBestFitStatus.setText("Voltooid");
                tijdsduur = Instant.now().toEpochMilli() - nu;
                System.out.println("Bestfit:\nAantal dozen:" + dozen + "\nTijd: " + tijdsduur + "ms\n");
            }
            if (Algoritme1 instanceof EigenAlgoritme) {
                nu = Instant.now().toEpochMilli();
                jlEigenFitStatus.setText("Uitvoeren...");
                jlHuidigeSimulatie.setText("Eigen Algoritme");
                jProgressBar1.setIndeterminate(true);
                dozen = (EigenAlgoritme.start(ArrayPakketten, DoosInhoud));
                jlEigenFitStatus.setText("Voltooid");
                tijdsduur = Instant.now().toEpochMilli() - nu;
                System.out.println("Eigenfit:\nAantal dozen:" + dozen + "\nTijd: " + tijdsduur + "ms\n");
            }
        }
        jProgressBar1.setIndeterminate(false);
        jProgressBar1.setValue(100);
        setTitle("Bin Packing Problem Simulation - Voltooid");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlGrootte = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlAantal = new javax.swing.JLabel();
        jlBruteForce = new javax.swing.JLabel();
        jlNextFit = new javax.swing.JLabel();
        jlFirstFit = new javax.swing.JLabel();
        jlBestFit = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jbAnnuleren = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jlProgressie = new javax.swing.JLabel();
        jlBruteforceStatus = new javax.swing.JLabel();
        jlNextFitStatus = new javax.swing.JLabel();
        jlFirstFitStatus = new javax.swing.JLabel();
        jlBestFitStatus = new javax.swing.JLabel();
        jlHuidigeSimulatie = new javax.swing.JLabel();
        jlEigenFit = new javax.swing.JLabel();
        jlEigenFitStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bin Packing Problem Simulation - Bezig");

        jLabel1.setText("Huidig product:");

        jLabel2.setText("Grootte:");

        jlGrootte.setText("x");

        jLabel3.setText("Aantal:");

        jlAantal.setText("x");

        jlBruteForce.setText("Brute force");

        jlNextFit.setText("Next fit");

        jlFirstFit.setText("First fit");

        jlBestFit.setText("Best fit");

        jLabel4.setText("Product ");

        jProgressBar1.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar1.setValue(50);

        jbAnnuleren.setText("Annuleren");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        jLabel5.setText("Simulatie met algoritme: ");

        jlProgressie.setText("0 van 0");

        jlBruteforceStatus.setText("Wordt niet uitgevoerd");

        jlNextFitStatus.setText("Wordt niet uitgevoerd");

        jlFirstFitStatus.setText("Wordt niet uitgevoerd");

        jlBestFitStatus.setText("Wordt niet uitgevoerd");

        jlHuidigeSimulatie.setText("x");

        jlEigenFit.setText("Best fit");

        jlEigenFitStatus.setText("Wordt niet uitgevoerd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jbAnnuleren))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlGrootte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlAantal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jlBruteForce)
                            .addComponent(jlNextFit)
                            .addComponent(jlFirstFit)
                            .addComponent(jlBestFit)
                            .addComponent(jLabel4)
                            .addComponent(jlEigenFit))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEigenFitStatus)
                            .addComponent(jlProgressie)
                            .addComponent(jlBruteforceStatus)
                            .addComponent(jlNextFitStatus)
                            .addComponent(jlFirstFitStatus)
                            .addComponent(jlBestFitStatus))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlHuidigeSimulatie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jlHuidigeSimulatie))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jlGrootte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jlAantal))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlBruteForce)
                            .addComponent(jlBruteforceStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlNextFit)
                            .addComponent(jlNextFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFirstFit)
                            .addComponent(jlFirstFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlBestFit)
                            .addComponent(jlBestFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEigenFit)
                            .addComponent(jlEigenFitStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jlProgressie))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbAnnuleren, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton jbAnnuleren;
    private javax.swing.JLabel jlAantal;
    private javax.swing.JLabel jlBestFit;
    private javax.swing.JLabel jlBestFitStatus;
    private javax.swing.JLabel jlBruteForce;
    private javax.swing.JLabel jlBruteforceStatus;
    private javax.swing.JLabel jlEigenFit;
    private javax.swing.JLabel jlEigenFitStatus;
    private javax.swing.JLabel jlFirstFit;
    private javax.swing.JLabel jlFirstFitStatus;
    private javax.swing.JLabel jlGrootte;
    private javax.swing.JLabel jlHuidigeSimulatie;
    private javax.swing.JLabel jlNextFit;
    private javax.swing.JLabel jlNextFitStatus;
    private javax.swing.JLabel jlProgressie;
    // End of variables declaration//GEN-END:variables
}
