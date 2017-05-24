/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazijnrobot;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author wouterwijsman
 */
public class Pakbon {
    ArrayList<Product> producten;;
    int doos, ordernummer;
    
    public Pakbon(ArrayList<Product> producten, int doos, int order) {
        this.producten = producten;
        this.doos = doos;
        this.ordernummer = order;
    }
    
    //Generates the content of the Pakbon as a string
    private String getContent(){
        DateTimeFormatter datumFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dag = LocalDate.now();
        String content = "Pakbon;"+datumFormat.format(dag)+"\n\nOrdernummer:;"+ordernummer+";\nDoos:;"+doos+"\n\nProduct ID;Productnaam;\n"; 
        for(Product p: producten) {
            content += p.getId()+";"+p.getName()+";\n";
        }
        
        return content;
    }
    
    public void SaveResults(int doosnummer, JFrame parent) {
        PrintWriter pwFileWriter;
        
        //sla op als csv
        JFileChooser jfChooser = new JFileChooser();
        FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("csv files (*.csv)", "csv");
	jfChooser.setFileFilter(csvfilter);
        jfChooser.setDialogTitle("Pakbon doos "+doosnummer+" opslaan");
        if (jfChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            String location = jfChooser.getSelectedFile().getAbsolutePath() + ".csv";
            try {
                //write content of the file
                pwFileWriter = new PrintWriter(new File(location));
                String content = this.getContent();
                pwFileWriter.write(content);
            pwFileWriter.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Er ging iets mis tijdens het opslaan", "Foutmelding",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //open het gemaakte bestand
            try {
                Desktop.getDesktop().open(new File(location));
            } catch (IOException ex) {
                System.out.println("Couldn't open file");
            }
        }
    }
}
