package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jv.kauan.trabalho_2_01.Tabuleiro;

public class PainelEditar extends JDialog{
    PainelEditarCelula painelEditarCelula;
    private JLabel labelEditar; 
    
    private JButton[][] botoesTabuleiro;
    private JButton botaoCancelar;
    private JButton botaoSalvar;
    
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;
    
    public PainelEditar(MainWindow pai, Tabuleiro tabuleiro){
        super(pai, "Editar", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        
        painelNorte = new JPanel(new FlowLayout());
        add(painelNorte, BorderLayout.NORTH);
        labelEditar = new JLabel("Editar");
        labelEditar.setFont(new Font("SansSerif", Font.BOLD, 38));
        painelNorte.add(labelEditar);
        
        painelCentro = new JPanel(new GridLayout(tabuleiro.getLinhas() - 2,
                                                    tabuleiro.getColunas() - 2));
        add(painelCentro);
        botoesTabuleiro = new JButton[tabuleiro.getLinhas() - 2]
                                        [tabuleiro.getColunas() - 2];
        
        ActionListener tabuleiroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                    JButton botaoClicado = (JButton) e.getSource();
                    
                    String nome = botaoClicado.getName();
                    String[] partes = nome.split(" ");
                    int pos[] = new int[2];
                    pos[0] = Integer.parseInt(partes[0]);
                    pos[1] = Integer.parseInt(partes[1]);
                    
                    painelEditarCelula = new PainelEditarCelula(pai, tabuleiro, 
                                                                pos, botaoClicado);
            }
        };
        
        for(int i = 1; i < tabuleiro.getLinhas() - 1; i++){
            for(int j = 1; j < tabuleiro.getColunas() - 1; j++){
                botoesTabuleiro[i-1][j-1] = new JButton(tabuleiro.getSimbolo(i, j));
                botoesTabuleiro[i-1][j-1].addActionListener(tabuleiroListener);
                botoesTabuleiro[i-1][j-1].setName(i + " " + j);
                painelCentro.add(botoesTabuleiro[i-1][j-1]);
            }
        }
        
        painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        add(painelSul, BorderLayout.SOUTH);
        botaoCancelar = new JButton("Cancelar");
        botaoSalvar = new JButton("Salvar");
        painelSul.add(botaoCancelar);
        painelSul.add(botaoSalvar);
        botaoCancelar.setPreferredSize(new Dimension(160, 30));
        botaoSalvar.setPreferredSize(new Dimension(160, 30));
        
        botaoCancelar.addActionListener(e -> dispose());
        
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabuleiro.editarTabuleiro();
                pai.setEdicaoSalva(true);
                dispose();
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
