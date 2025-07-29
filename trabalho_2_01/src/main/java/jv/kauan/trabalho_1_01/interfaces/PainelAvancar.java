package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PainelAvancar extends JDialog{
    private JPanel painelCentro;
    private JPanel painelSul;
    private JLabel labelInteracoes;
    private JLabel labelDelay;
    private JTextField campoInteracoes;
    private JSpinner spinnerDelay;
    private JButton botaoIniciar;
    private JButton botaoCancelar;
    
    public PainelAvancar(MainWindow pai){
        super(pai, "Avançar Automaticamente", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(370,150);
        setLayout(new BorderLayout());       
        
        labelInteracoes = new JLabel("Quantidade de interaçoes: ");
        campoInteracoes = new JTextField();       
        labelDelay = new JLabel("Selecione o delay (ms): ");       
        spinnerDelay = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 100));
        
        painelCentro = new JPanel(new GridLayout(2,2));
        painelCentro.add(labelInteracoes);
        painelCentro.add(campoInteracoes);
        painelCentro.add(labelDelay);
        painelCentro.add(spinnerDelay);
        add(painelCentro);
        
        botaoIniciar = new JButton("Iniciar");     
        botaoCancelar = new JButton("Cancelar");
        
        painelSul = new JPanel();
        painelSul.add(botaoCancelar);
        painelSul.add(botaoIniciar);
        add(painelSul, BorderLayout.SOUTH);
        
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int interacoes = Integer.parseInt(campoInteracoes.getText());
                    
                    if(interacoes <= 0 || interacoes > 100) 
                        JOptionPane.showMessageDialog(rootPane, 
                                "As interações devem estar entre 1 e 100!", 
                                "Interação inválida", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        pai.setRepeticao(interacoes);
                        pai.setDelay((int) spinnerDelay.getValue());
                        pai.setComecar(true);
                        dispose();
                    }
                
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, 
                            "Digite apenas números!", 
                            "Interacao inválida", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
            
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pai.setComecar(false);
                dispose();
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
