package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import jv.kauan.trabalho_2_01.Tabuleiro;
import jv.kauan.trabalho_2_01.Mensagens;

public class MainWindow extends JFrame {
    PainelAvancar painelAvancar;
    PainelEditar painelEditar;
    
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;
    private JPanel painelContainerTabuleiro;
    private JPanel painelTabuleiro;

    private JLabel labelJogoDaVida;
    private JLabel labelInteracoes;
    
    private JButton botaoAvancar;
    private JButton botaoAvancarA;
    private JButton botaoPausar;
    
    private JMenuBar barraDeMenus;
    
    private JMenu menuArquivo;
    private JMenu menuAjuda;
    private JMenu menuComoUsar;
    
    private JMenuItem menuItemAbrir;
    private JMenuItem menuItemSalvar;
    private JMenuItem menuItemEditar;
    private JMenuItem menuItemSair;    
    private JMenuItem menuItemCelula;   
    private JMenuItem menuItemFormato;
    private JMenuItem menuItemSobre;
    private JMenuItem menuItemComoAbrir;
    private JMenuItem menuItemComoSalvar;
    private JMenuItem menuItemComoEditar;
    private JMenuItem menuItemComoAvancar;
    
    private JLabel[][] labelTabuleiro;
    
    private JFileChooser fileManager;
    private File file;
    
    private Tabuleiro tabuleiro;
    
    private boolean edicaoSalva;
    
    private Timer timer;
    private int delay;
    private int repeticaoMax;
    private int repeticaoAtual;
    private ActionListener simulacaoListener;
    private boolean pausado;
    private boolean comecar;

    public MainWindow() {
        super("Jogo da vida");
        
        
        fileManager = new JFileChooser();
        fileManager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        tabuleiro = new Tabuleiro();
        
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        
        painelNorte = new JPanel();
        painelNorte.setLayout(new BoxLayout(painelNorte, BoxLayout.Y_AXIS));
        painelNorte.setAlignmentX(CENTER_ALIGNMENT);
        add(painelNorte, BorderLayout.NORTH);
        
        labelJogoDaVida = new JLabel("Jogo Da Vida");
        labelJogoDaVida.setFont(new Font("SansSerif", Font.BOLD, 48));
        painelNorte.add(labelJogoDaVida);
        
        labelInteracoes = new JLabel("Interação");
        labelInteracoes.setFont(new Font("SansSerif", Font.PLAIN, 20));
        labelInteracoes.setBorder(new EmptyBorder(30, 0, 5, 0));
        painelNorte.add(labelInteracoes);
        
        labelJogoDaVida.setAlignmentX(CENTER_ALIGNMENT);
        labelInteracoes.setAlignmentX(CENTER_ALIGNMENT);
        
        painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(painelCentro, BorderLayout.CENTER);
        
        
        painelContainerTabuleiro = new JPanel();
        painelContainerTabuleiro.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelCentro.add(painelContainerTabuleiro, BorderLayout.CENTER);
        
        painelTabuleiro = new JPanel();
        painelTabuleiro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        painelTabuleiro.setMinimumSize(new Dimension(600, 400));
        painelTabuleiro.setPreferredSize(new Dimension(600, 400));
        painelTabuleiro.setMaximumSize(new Dimension(600, 400));
        painelContainerTabuleiro.add(painelTabuleiro);
        
        painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));  
        
        botaoAvancar = new JButton("Avançar");
        botaoAvancar.setActionCommand("Avançar");
        
        botaoAvancarA = new JButton("Avançar Automaticamente");
        botaoAvancarA.setActionCommand("Avançar Automaticamente");
        
        botaoPausar = new JButton("Pausar");
        botaoPausar.setActionCommand("Pausar");
        botaoPausar.setEnabled(false);
        
        painelSul.add(botaoAvancar);
        painelSul.add(botaoAvancarA);
        painelSul.add(botaoPausar);
        
        botaoAvancar.setPreferredSize(new Dimension(200, 30));
        botaoAvancarA.setPreferredSize(new Dimension(200, 30));
        botaoPausar.setPreferredSize(new Dimension(200, 30));
        
        add(painelSul, BorderLayout.SOUTH);
        
        barraDeMenus = new JMenuBar();

        menuArquivo = new JMenu("Arquivo");
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemAbrir.setActionCommand("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        menuItemSalvar.setActionCommand("Salvar");
        menuItemEditar = new JMenuItem("Editar");
        menuItemEditar.setActionCommand("Editar");
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setActionCommand("Sair");
        menuArquivo.add(menuItemAbrir);
        menuArquivo.add(menuItemSalvar);
        menuArquivo.add(menuItemEditar);
        menuArquivo.add(menuItemSair);
        barraDeMenus.add(menuArquivo);
        
        menuComoUsar = new JMenu("Como usar");
        menuItemComoAbrir = new JMenuItem("Como abrir");
        menuItemComoAbrir.setActionCommand("Como abrir");
        menuItemComoSalvar = new JMenuItem("Como salvar");
        menuItemComoSalvar.setActionCommand("Como salvar");
        menuItemComoEditar = new JMenuItem("Como editar");
        menuItemComoEditar.setActionCommand("Como editar");
        menuItemComoAvancar = new JMenuItem("Como avançar");
        menuItemComoAvancar.setActionCommand("Como avançar");
        menuComoUsar.add(menuItemComoAbrir);
        menuComoUsar.add(menuItemComoSalvar);
        menuComoUsar.add(menuItemComoEditar);
        menuComoUsar.add(menuItemComoAvancar);
        
        menuAjuda = new JMenu("Ajuda");
        menuItemCelula = new JMenuItem("Tipos de celulas");
        menuItemCelula.setActionCommand("Tipos de celulas");
        menuItemFormato = new JMenuItem("Formato do arquivo");
        menuItemFormato.setActionCommand("Formato do arquivo");
        menuItemSobre = new JMenuItem("Sobre");
        menuItemSobre.setActionCommand("Sobre");
        menuAjuda.add(menuComoUsar);
        menuAjuda.add(menuItemCelula);
        menuAjuda.add(menuItemFormato);
        menuAjuda.add(menuItemSobre);
        barraDeMenus.add(menuAjuda);
        
        setJMenuBar(barraDeMenus);

        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "Abrir":
                        if(!timerIsRunning()) {
                            if(!tabuleiro.estaVazio())
                                JOptionPane.showMessageDialog(MainWindow.this, 
                                    Mensagens.SALVAR_TABULEIRO.toString(), 
                                    Mensagens.TITULO_SALVAR_TABULEIRO.toString(), 
                                    JOptionPane.INFORMATION_MESSAGE);
                            
                            if(abrirArquivo()) {
                                criarInterfaceTabuleiro();
                                labelInteracoes.setText("Interacao: " + 
                                        tabuleiro.getInteracao());
                                timer = null;
                                botaoPausar.setEnabled(false);
                            }
                        }
                        break;

                    case "Salvar":
                        if(!timerIsRunning()) {
                             if (tabuleiro.estaVazio())
                                JOptionPane.showMessageDialog(rootPane, 
                                                Mensagens.TABULEIRO_INEXISTENTE.toString(),
                                                Mensagens.TITULO_TABULEIRO_INEXISTENTE.toString(),
                                                JOptionPane.INFORMATION_MESSAGE);
                            else
                                salvarArquivo();
                        }
                        break;
                        
                    case "Editar":
                        if(!timerIsRunning()) {
                            if(tabuleiro.estaVazio())
                                JOptionPane.showMessageDialog(rootPane, 
                                        Mensagens.TABULEIRO_INEXISTENTE.toString(), 
                                        Mensagens.TITULO_TABULEIRO_INEXISTENTE.toString(), 
                                        JOptionPane.INFORMATION_MESSAGE);
                            else
                                painelEditar = new PainelEditar(MainWindow.this, tabuleiro);
                                if(edicaoSalva) {
                                    criarInterfaceTabuleiro();
                                    edicaoSalva = false;
                                }
                        }
                        break;

                    case "Sair":
                        dispose();
                        break;

                    case "Como abrir":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.COMO_ABRIR);
                        break;
                        
                    case "Como salvar":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.COMO_SALVAR);
                        break;
                    
                    case "Como editar":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.COMO_EDITAR);
                        break;

                    case "Como avançar":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.COMO_AVANCAR);
                        break;

                    case "Tipos de celulas":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.TIPOS_CELULAS);
                        break;
                        
                    case "Formato do arquivo":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.FORMATO_ARQUIVO);                              
                        break;

                    case "Sobre":
                        JOptionPane.showMessageDialog(rootPane, 
                                Mensagens.SOBRE);
                        break;
                }
            }
        };
        
        menuItemAbrir.addActionListener(menuListener);
        menuItemSalvar.addActionListener(menuListener);
        menuItemEditar.addActionListener(menuListener);
        menuItemSair.addActionListener(menuListener);
        menuItemComoAbrir.addActionListener(menuListener);
        menuItemComoSalvar.addActionListener(menuListener);
        menuItemComoEditar.addActionListener(menuListener);
        menuItemComoAvancar.addActionListener(menuListener);
        menuItemCelula.addActionListener(menuListener);
        menuItemFormato.addActionListener(menuListener);
        menuItemSobre.addActionListener(menuListener);
        
        botaoAvancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabuleiro.estaVazio())
                    JOptionPane.showMessageDialog(rootPane, 
                            Mensagens.TABULEIRO_INEXISTENTE.toString(), 
                            Mensagens.TITULO_TABULEIRO_INEXISTENTE.toString(), 
                            JOptionPane.INFORMATION_MESSAGE);
                else {
                    avancarInteracao();
                }
            }
        });
        
        botaoAvancarA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabuleiro.estaVazio())
                    JOptionPane.showMessageDialog(rootPane, 
                            Mensagens.TABULEIRO_INEXISTENTE.toString(), 
                            Mensagens.TITULO_TABULEIRO_INEXISTENTE.toString(), 
                            JOptionPane.INFORMATION_MESSAGE);
                else {
                    painelAvancar = new PainelAvancar(MainWindow.this);
                    if(comecar) {
                        comecarSimulacao();
                        repeticaoAtual = 0;
                        pausado = false;
                        botaoPausar.setText("Pausar");
                        botaoPausar.setEnabled(true);
                        botaoAvancar.setEnabled(false);
                        botaoAvancarA.setEnabled(false);
                    }
                }
            }
        });
        
        botaoPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pausado) {
                    pausado = true;
                    timer.stop();
                    botaoPausar.setText("Retomar");
                    botaoAvancar.setEnabled(true);
                    botaoAvancarA.setEnabled(true);
                } else {
                    pausado = false;
                    timer.start();
                    botaoPausar.setText("Pausar");
                    botaoAvancar.setEnabled(false);
                    botaoAvancarA.setEnabled(false);
                }
            }
        });
        
        simulacaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Começou");
                if(!pausado && repeticaoAtual < repeticaoMax) {
                    avancarInteracao();
                    repeticaoAtual++;
                } else if(repeticaoAtual >= repeticaoMax){
                    comecar = false;
                    timer.stop();
                    botaoPausar.setEnabled(false);
                    botaoAvancar.setEnabled(true);
                    botaoAvancarA.setEnabled(true);
                    repeticaoAtual = 0;
                }
            }
        };
        comecar = false;
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private boolean abrirArquivo() {
        int res = fileManager.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            file = fileManager.getSelectedFile();

            try {
                tabuleiro = new Tabuleiro(file);
                return true;

            } catch (FileNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this,
                        Mensagens.ARQUIVO_INEXISTENTE.toString(),
                        Mensagens.TITULO_ARQUIVO_INEXISTENTE.toString(), 
                        JOptionPane.ERROR_MESSAGE);
                return false;

            } catch (RuntimeException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this,
                        Mensagens.ARQUIVO_INVALIDO.toString(),
                        Mensagens.TITULO_ARQUIVO_INVALIDO.toString(), 
                        JOptionPane.ERROR_MESSAGE);
                return false;
                
            }

        } else if (res == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(this,
                    Mensagens.ESCOLHA_ARQUIVO.toString(),
                    Mensagens.TITULO_ESCOLHA_ARQUIVO.toString(), 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }
    
    private void salvarArquivo() {
        int res = fileManager.showSaveDialog(this);
        file = fileManager.getSelectedFile();
        
        if(res == JFileChooser.APPROVE_OPTION) {
            try(PrintWriter writer = new PrintWriter(file)) {
                // Salvar arquivo.
                writer.print(tabuleiro.salvarTabuleiro());
                
                JOptionPane.showMessageDialog(rootPane,
                        Mensagens.TABULEIRO_SALVO.toString(),
                        Mensagens.TITULO_TABULEIRO_SALVO.toString(), 
                        JOptionPane.INFORMATION_MESSAGE);
                
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(rootPane,
                        Mensagens.ERRO_SALVAMENTO.toString(),
                        Mensagens.TITULO_ERRO_SALVAMENTO.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void criarInterfaceTabuleiro() {
        painelContainerTabuleiro.remove(painelTabuleiro);
        
        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(tabuleiro.getLinhas(), 
                                                    tabuleiro.getColunas()));
        painelTabuleiro.setMinimumSize(new Dimension(600, 400));
        painelTabuleiro.setPreferredSize(new Dimension(600, 400));
        painelTabuleiro.setMaximumSize(new Dimension(600, 400));
        painelContainerTabuleiro.add(painelTabuleiro);
        
        labelTabuleiro = new JLabel[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i = 0; i < tabuleiro.getLinhas(); i++) {
            for(int j = 0; j < tabuleiro.getColunas(); j++) {
                labelTabuleiro[i][j] = new JLabel(tabuleiro.printarCelula(i, j));
                labelTabuleiro[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                painelTabuleiro.add(labelTabuleiro[i][j]);
            }
        }
        
        painelContainerTabuleiro.revalidate();
        painelContainerTabuleiro.repaint();
    }
    
    private void comecarSimulacao() {
        timer = new Timer(delay, simulacaoListener);
        timer.setInitialDelay(1500);
        timer.start();
    }
    
    private void avancarInteracao() {
        tabuleiro.avancarInteracao();
        labelInteracoes.setText("Interação: " + tabuleiro.getInteracao());
        criarInterfaceTabuleiro();
    }
    
    private boolean timerIsRunning() {
        if(timer != null && timer.isRunning()){
            JOptionPane.showMessageDialog(MainWindow.this, 
                    Mensagens.INTERACAO_RODANDO.toString(), 
                    Mensagens.TITULO_INTERACAO_RODANDO.toString(), 
                    JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }
    
    // Setters
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setRepeticao(int repeticao) {
        this.repeticaoMax = repeticao;
    }

    public void setComecar(boolean comecar) {
        this.comecar = comecar;
    }

    public void setEdicaoSalva(boolean edicaoSalva) {
        this.edicaoSalva = edicaoSalva;
    }
}
