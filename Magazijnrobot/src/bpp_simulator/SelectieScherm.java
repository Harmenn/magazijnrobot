package bpp_simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SelectieScherm extends javax.swing.JFrame implements ActionListener {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbStart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtInhoud = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtGrootte = new javax.swing.JTextField();
        jtAantalProducten = new javax.swing.JTextField();
        jbToevoegen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProducten = new javax.swing.JTable();
        jcBruteforce = new javax.swing.JCheckBox();
        jcNextfit = new javax.swing.JCheckBox();
        jcFirstfit = new javax.swing.JCheckBox();
        jcBestfit = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jbReset = new javax.swing.JButton();
        jcEigenAlgoritme = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bin Packing Problem Simulator");
        setResizable(false);

        jbStart.setText("Start Simulatie");

        jLabel1.setText("Inhoud per doos:");

        jtInhoud.setText("0");

        jLabel3.setText("Grootte product:");

        jLabel4.setText("Aantal met dit formaat:");

        jtGrootte.setText("0");
        jtGrootte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtGrootteActionPerformed(evt);
            }
        });

        jtAantalProducten.setText("0");

        jbToevoegen.setText("Toevoegen");

        jtProducten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grootte", "Aantal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProducten.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtProducten);
        if (jtProducten.getColumnModel().getColumnCount() > 0) {
            jtProducten.getColumnModel().getColumn(0).setResizable(false);
            jtProducten.getColumnModel().getColumn(1).setResizable(false);
        }

        jcBruteforce.setText("Brute Force");
        jcBruteforce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcBruteforceActionPerformed(evt);
            }
        });

        jcNextfit.setText("Next fit");

        jcFirstfit.setText("First fit");

        jcBestfit.setText("Best fit");

        jLabel5.setText("Selecteer te gebruiken algoritme:");

        jbReset.setText("Resetten");

        jcEigenAlgoritme.setText("Eigen algoritme");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtInhoud, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jcNextfit)
                                    .addComponent(jcFirstfit)
                                    .addComponent(jcBestfit)
                                    .addComponent(jcEigenAlgoritme)
                                    .addComponent(jcBruteforce))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbToevoegen)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtGrootte, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtAantalProducten, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbReset)
                        .addGap(18, 18, 18)
                        .addComponent(jbStart)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtGrootte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtAantalProducten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtInhoud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jbToevoegen)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcNextfit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcFirstfit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcBestfit)
                        .addGap(1, 1, 1)
                        .addComponent(jcEigenAlgoritme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcBruteforce)
                        .addGap(11, 11, 11))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbStart)
                    .addComponent(jbReset))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtGrootteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtGrootteActionPerformed
    }//GEN-LAST:event_jtGrootteActionPerformed

    private void jcBruteforceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcBruteforceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcBruteforceActionPerformed

    public SelectieScherm() {
        initComponents();

        jbStart.addActionListener(this);
        jbToevoegen.addActionListener(this);
        jbReset.addActionListener(this);
        setVisible(true);

    }

    public void addArray(int aantal, int grootte) {
        if (aantal > 0 && grootte > 0) {
            DefaultTableModel model = (DefaultTableModel) jtProducten.getModel();
            for (int i = 0; i < aantal; i++) {
                ArrayPakketten.add(new Product(grootte));
            }
            model.addRow(new Object[]{grootte, aantal});
        } else {
            JOptionPane.showMessageDialog(null, "De grootte en het aantal moet groter dan 0 zijn", "Foutmelding", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbReset;
    private javax.swing.JButton jbStart;
    private javax.swing.JButton jbToevoegen;
    private javax.swing.JCheckBox jcBestfit;
    private javax.swing.JCheckBox jcBruteforce;
    private javax.swing.JCheckBox jcEigenAlgoritme;
    private javax.swing.JCheckBox jcFirstfit;
    private javax.swing.JCheckBox jcNextfit;
    private javax.swing.JTextField jtAantalProducten;
    private javax.swing.JTextField jtGrootte;
    private javax.swing.JTextField jtInhoud;
    private javax.swing.JTable jtProducten;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Product> ArrayPakketten = new ArrayList<>();
    private boolean BruteForceEnabled = false;
    private boolean NextFitEnabled = false;
    private boolean FirstFitEnabled = false;
    private boolean BestFitEnabled = false;
    private boolean EigenAlgoritmeEnabled = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbToevoegen) {
            int grootte = tryParse(jtGrootte.getText());
            int aantal = tryParse(jtAantalProducten.getText());
            addArray(aantal, grootte);
            jtAantalProducten.setText("0");
            jtGrootte.setText("0");
        } else if (e.getSource() == jbReset) {
            ResetScherm();
        } else if (e.getSource() == jbStart) {
            int inhoud;
            inhoud = tryParse(jtInhoud.getText());
            if (inhoud > 0) {

                if (ArrayPakketten.size() > 0) {
                    BruteForceEnabled = jcBruteforce.isSelected();
                    NextFitEnabled = jcNextfit.isSelected();
                    FirstFitEnabled = jcFirstfit.isSelected();
                    BestFitEnabled = jcBestfit.isSelected();
                    EigenAlgoritmeEnabled = jcEigenAlgoritme.isSelected();
                    if (!BruteForceEnabled && !NextFitEnabled && !FirstFitEnabled && !BestFitEnabled && !EigenAlgoritmeEnabled) {
                        JOptionPane.showMessageDialog(null, "Er is geen algoritme geselecteerd!", "Foutmelding", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Simulatie s1 = new Simulatie(ArrayPakketten, inhoud, BruteForceEnabled, NextFitEnabled, FirstFitEnabled, BestFitEnabled, EigenAlgoritmeEnabled);
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Er zijn geen producten aan de lijst toegevoegd!", "Foutmelding", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Er is geen grootte van de doos / aantal dozen ingesteld!", "Foutmelding", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void ResetScherm() {
        jcBestfit.setSelected(false);
        jcBruteforce.setSelected(false);
        jcFirstfit.setSelected(false);
        jcNextfit.setSelected(false);
        jtAantalProducten.setText("0");
        jtGrootte.setText("0");
        jtInhoud.setText("0");
        DefaultTableModel model = (DefaultTableModel) jtProducten.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        for (Product pakket : ArrayPakketten) {
            pakket = null;
        }
        ArrayPakketten.removeAll(ArrayPakketten);
    }
}
