package jv.kauan.trabalho_1_01;

import jv.kauan.trabalho_1_01.celulas.CelulaTimida;
import jv.kauan.trabalho_1_01.celulas.CelulaClassica;
import jv.kauan.trabalho_1_01.celulas.CelulaMatematica;
import jv.kauan.trabalho_1_01.celulas.Celula;
import jv.kauan.trabalho_1_01.celulas.CelulaBorda;
import jv.kauan.trabalho_1_01.celulas.CelulaForte;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import jv.kauan.trabalho_1_01.interfaces.MainWindow;

public class Trabalho_2_01 {
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
  
    }
}
