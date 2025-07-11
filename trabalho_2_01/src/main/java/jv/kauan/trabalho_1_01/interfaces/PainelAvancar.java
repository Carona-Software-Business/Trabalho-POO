package jv.kauan.trabalho_1_01.interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PainelAvancar extends JDialog{
    private JLabel labelInteracoes;
    private JLabel labelDelay;
    private JTextField campoInteracoes;
    private JSpinner spinnerDelay;
    private JButton botaoIniciar;
    private JButton botaoCancelar;
    
    public PainelAvancar(JFrame pai){
        super(pai, "Avançar Automaticamente", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,300);
        setLayout(new GridLayout(3,2));
        
        labelInteracoes = new JLabel("Digite a quantidade de interaçoes: ");
        add(labelInteracoes);
        
        campoInteracoes = new JTextField();
        add(campoInteracoes);
        
        labelDelay = new JLabel("Selecione o delay (ms): ");
        add(labelDelay);
        
        spinnerDelay = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 100));
        add (spinnerDelay);
        
        botaoIniciar = new JButton("Iniciar");
        add(botaoIniciar);
        
        botaoCancelar = new JButton("Cancelar");
        add(botaoCancelar);
        
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Iniciando...");
            }
        });
            
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
