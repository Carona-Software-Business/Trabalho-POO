package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class PainelEditar extends JDialog{
    PainelEditarCelula painelEditarCelula;
    private JLabel labelEditar; 
    
    private JButton[][] tabuleiro;
    private JButton botaoCancelar;
    private JButton botaoSalvar;
    
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;
    
    public PainelEditar(JFrame pai){
        super(pai, "Editar", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        
        painelNorte = new JPanel(new FlowLayout());
        add(painelNorte, BorderLayout.NORTH);
        labelEditar = new JLabel("Editar");
        labelEditar.setFont(new Font("SansSerif", Font.BOLD, 38));
        painelNorte.add(labelEditar);
        
        painelCentro = new JPanel(new GridLayout(5,5));
        add(painelCentro);
        tabuleiro = new JButton[5][5];
        
        ActionListener tabuleiroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    painelEditarCelula = new PainelEditarCelula(pai);
            }
        };
        
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tabuleiro[i][j] = new JButton(i + "" + j);
                tabuleiro[i][j].addActionListener(tabuleiroListener);
                painelCentro.add(tabuleiro[i][j]);
            }
        }
        
        painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        add(painelSul, BorderLayout.SOUTH);
        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setActionCommand("Cancelar");
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setActionCommand("Salvar");
        painelSul.add(botaoCancelar);
        painelSul.add(botaoSalvar);
        botaoCancelar.setPreferredSize(new Dimension(160, 30));
        botaoSalvar.setPreferredSize(new Dimension(160, 30));
        
        botaoCancelar.addActionListener(e -> dispose());
        
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
