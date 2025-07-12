package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PainelEditarCelula extends JDialog{
    private JPanel painelTipoCelula;
    private JPanel painelEstadoCelula;
    private JPanel painelCentral;
    private JPanel painelSul;
    
    private ButtonGroup grupoTipoCelula;
    private ButtonGroup grupoEstadoCelula;
    
    private JRadioButton radioClassica;
    private JRadioButton radioForte;
    private JRadioButton radioTimida;
    private JRadioButton radioMatematica;
    
    private JRadioButton radioViva;
    private JRadioButton radioMorta;
    
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    
    public PainelEditarCelula(JFrame pai){
        super(pai, "Editar Celula", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(300, 250);
        
        painelTipoCelula = new JPanel();
        painelTipoCelula.setBorder(BorderFactory.createTitledBorder("Tipo da celula:"));
        grupoTipoCelula = new ButtonGroup();
        radioClassica = new JRadioButton("Classica");
        radioClassica.setSelected(true);
        radioForte = new JRadioButton("Forte");
        radioTimida = new JRadioButton("Timida");
        radioMatematica = new JRadioButton("Matematica");
        grupoTipoCelula.add(radioClassica);
        grupoTipoCelula.add(radioForte);
        grupoTipoCelula.add(radioTimida);
        grupoTipoCelula.add(radioMatematica);   
        painelTipoCelula.add(radioClassica);
        painelTipoCelula.add(radioForte);
        painelTipoCelula.add(radioTimida);
        painelTipoCelula.add(radioMatematica);       
        
        painelEstadoCelula = new JPanel();
        painelEstadoCelula.setBorder(BorderFactory.createTitledBorder("Estado da celula:"));
        grupoEstadoCelula = new ButtonGroup();
        radioViva = new JRadioButton("Viva");
        radioMorta = new JRadioButton("Morta");
        grupoEstadoCelula.add(radioViva);
        grupoEstadoCelula.add(radioMorta);
        painelEstadoCelula.add(radioViva);
        painelEstadoCelula.add(radioMorta);
        
        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setActionCommand("Cancelar");
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setActionCommand("Salvar");
   
        
        botaoCancelar.addActionListener(e -> dispose());

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        painelCentral = new JPanel(new GridLayout(2, 1));
        painelCentral.add(painelTipoCelula);
        painelCentral.add(painelEstadoCelula);
        add(painelCentral, BorderLayout.CENTER);
        
        painelSul = new JPanel();
        painelSul.add(botaoCancelar);
        painelSul.add(botaoSalvar);
        add(painelSul, BorderLayout.SOUTH);
                       
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
