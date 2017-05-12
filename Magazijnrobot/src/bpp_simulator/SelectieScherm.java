package bpp_simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SelectieScherm extends javax.swing.JFrame implements ActionListener {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jbStart = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtBinSize = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtProductSize = new javax.swing.JTextField();
        jtProductAmount = new javax.swing.JTextField();
        jbAddProduct = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProducts = new javax.swing.JTable();
        jcBruteforce = new javax.swing.JCheckBox();
        jcNextfit = new javax.swing.JCheckBox();
        jcFirstfit = new javax.swing.JCheckBox();
        jcBestfit = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jbReset = new javax.swing.JButton();
        jcOwnFit = new javax.swing.JCheckBox();
        jbRemoveProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bin Packing Problem Simulator");
        setResizable(false);

        jbStart.setText("Start Simulatie");

        jLabel1.setText("Inhoud per doos:");

        jtBinSize.setText("10");

        jLabel3.setText("Grootte product:");

        jLabel4.setText("Aantal met dit formaat:");

        jtProductSize.setText("0");

        jtProductAmount.setText("0");

        jbAddProduct.setText("Toevoegen");

        jtProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jtProducts.setRowSelectionAllowed(false);
        jtProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtProducts);
        if (jtProducts.getColumnModel().getColumnCount() > 0) {
            jtProducts.getColumnModel().getColumn(0).setResizable(false);
            jtProducts.getColumnModel().getColumn(1).setResizable(false);
        }

        jcBruteforce.setText("Brute Force");

        jcNextfit.setText("Next fit");

        jcFirstfit.setText("First fit");

        jcBestfit.setText("Best fit");

        jLabel5.setText("Selecteer te gebruiken algoritme:");

        jbReset.setText("Resetten");

        jcOwnFit.setText("Eigen algoritme");

        jbRemoveProduct.setText("Verwijder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtBinSize, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbRemoveProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbReset)
                        .addGap(18, 18, 18)
                        .addComponent(jbStart))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtProductSize, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtProductAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jcNextfit)
                                    .addComponent(jcFirstfit)
                                    .addComponent(jcBestfit)
                                    .addComponent(jcOwnFit)
                                    .addComponent(jcBruteforce))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtProductSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtProductAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAddProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(jcOwnFit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcBruteforce)
                            .addGap(11, 11, 11))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtBinSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbStart)
                            .addComponent(jbReset)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbRemoveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAddProduct;
    private javax.swing.JButton jbRemoveProduct;
    private javax.swing.JButton jbReset;
    private javax.swing.JButton jbStart;
    private javax.swing.JCheckBox jcBestfit;
    private javax.swing.JCheckBox jcBruteforce;
    private javax.swing.JCheckBox jcFirstfit;
    private javax.swing.JCheckBox jcNextfit;
    private javax.swing.JCheckBox jcOwnFit;
    private javax.swing.JTextField jtBinSize;
    private javax.swing.JTextField jtProductAmount;
    private javax.swing.JTextField jtProductSize;
    private javax.swing.JTable jtProducts;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Product> ArrayProducts = new ArrayList<>();

    public SelectieScherm() {
        initComponents();
        jbStart.addActionListener(this);
        jbAddProduct.addActionListener(this);
        jbReset.addActionListener(this);
        jbRemoveProduct.addActionListener(this);
        setVisible(true);

    }

    public void addRow(int aantal, int grootte) {
        if (aantal > 0 && grootte > 0) {
            DefaultTableModel model = (DefaultTableModel) jtProducts.getModel();
            for (int i = 0; i < aantal; i++) {
                ArrayProducts.add(new Product(grootte));
            }
            model.addRow(new Object[]{grootte, aantal});
        } else {
            JOptionPane.showMessageDialog(null, "De grootte en het aantal moet groter dan 0 zijn", "Foutmelding",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeRow(int row) {
        if (jtProducts.getRowCount() > 0 && jtProducts.getSelectedRow() >= 0) {
            DefaultTableModel model = (DefaultTableModel) jtProducts.getModel();
            model.removeRow(row);
            ArrayProducts.remove(row);
        }
    }

    public void ExitButton(boolean Shutdown) {
        if (Shutdown) {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    private void StartSim() {
        int binSize;
        binSize = tryParse(jtBinSize.getText());
        if (binSize > 0) {
            if (ArrayProducts.size() > 0) {
                for (Product ArrayProduct : ArrayProducts) {
                    if (ArrayProduct.getLength() > binSize) {
                        JOptionPane.showMessageDialog(null, "Één of meerdere producten is groter dan de inhoud van de doos", "Foutmelding",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                boolean[] Algorithms = new boolean[5];
                Algorithms[0] = jcNextfit.isSelected();
                Algorithms[1] = jcFirstfit.isSelected();
                Algorithms[2] = jcBestfit.isSelected();
                Algorithms[3] = jcOwnFit.isSelected();
                Algorithms[4] = jcBruteforce.isSelected();
                if (Algorithms[0] == false && Algorithms[1] == false && Algorithms[2] == false && Algorithms[3] == false && Algorithms[4]) {
                    JOptionPane.showMessageDialog(null, "Er is geen algoritme geselecteerd!", "Foutmelding",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Simulatie s1 = new Simulatie(this, ArrayProducts, binSize, Algorithms);
                    setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Er zijn geen producten aan de lijst toegevoegd!",
                        "Foutmelding", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Er is geen inhoud van de doos ingesteld!",
                    "Foutmelding", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbAddProduct) {
            addRow(tryParse(jtProductAmount.getText()), tryParse(jtProductSize.getText()));
            jtProductAmount.setText("0");
            jtProductSize.setText("0");
        } else if (e.getSource() == jbReset) {
            ResetScherm();
        } else if (e.getSource() == jbStart) {
            StartSim();
        } else if (e.getSource() == jbRemoveProduct) {
            removeRow(jtProducts.getSelectedRow());
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
        if (JOptionPane.showConfirmDialog(null,
                "Weet je zeker dat je de ingevoerde gegevens wil resetten?", "Resetten", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            jcBestfit.setSelected(false);
            jcBruteforce.setSelected(false);
            jcFirstfit.setSelected(false);
            jcNextfit.setSelected(false);
            jtProductAmount.setText("0");
            jtProductSize.setText("0");
            jtBinSize.setText("10");
            DefaultTableModel model = (DefaultTableModel) jtProducts.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            for (Product pakket : ArrayProducts) {
                pakket = null;
            }
            ArrayProducts.removeAll(ArrayProducts);
        }
    }
}
