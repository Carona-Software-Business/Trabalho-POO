package jv.kauan.trabalho_1_01.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import jv.kauan.trabalho_1_01.Tabuleiro;

public class MainWindow extends JFrame {
    PainelAvancar painelAvancar;
    
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;

    private JLabel labelJogoDaVida;
    
    private JButton botaoAvancar;
    private JButton botaoAvancarA;
    private JButton botaoPausar;
    
    private JMenuBar barraDeMenus;
    
    private JMenu menuArquivo;
    private JMenu menuAjuda;
    
    private JMenuItem menuItemAbrir;
    private JMenuItem menuItemSalvar;
    private JMenuItem menuItemEditar;
    private JMenuItem menuItemSair;
    private JMenuItem menuItemAjuda;    
    private JMenuItem menuItemCelula;   
    private JMenuItem menuItemFormato;
    private JMenuItem menuItemSobre;
    
    private JTextArea textTabuleiro;
    
    private JFileChooser fileManager;
    private File file;
    
    private Tabuleiro tabuleiro;

    public MainWindow() {
        super("Jogo da vida");
        
        
        fileManager = new JFileChooser();
        fileManager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        
        painelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelNorte.setBorder(new EmptyBorder(30, 10, 10, 10));
        add(painelNorte, BorderLayout.NORTH);
        
        labelJogoDaVida = new JLabel("Jogo Da Vida");
        labelJogoDaVida.setFont(new Font("SansSerif", Font.BOLD, 48));
        painelNorte.add(labelJogoDaVida);
        
        painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(painelCentro, BorderLayout.CENTER);
        
        textTabuleiro = new JTextArea();
        textTabuleiro.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textTabuleiro);
        painelCentro.add(scrollPane, BorderLayout.CENTER);
        
        painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));   
        
        botaoAvancar = new JButton("Avançar");
        botaoAvancar.setActionCommand("Avançar");
        
        botaoAvancarA = new JButton("Avançar Automaticamente");
        botaoAvancarA.setActionCommand("Avançar Automaticamente");
        
        botaoPausar = new JButton("Pausar");
        botaoPausar.setActionCommand("Pausar");
        
        painelSul.add(botaoAvancar);
        painelSul.add(botaoAvancarA);
        painelSul.add(botaoPausar);
        
        botaoAvancar.setPreferredSize(new Dimension(160, 30));
        botaoAvancarA.setPreferredSize(new Dimension(160, 30));
        botaoPausar.setPreferredSize(new Dimension(160, 30));
        
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
        
        menuAjuda = new JMenu("Ajuda");
        menuItemAjuda = new JMenuItem("Como usar");
        menuItemAjuda.setActionCommand("Como usar");
        menuItemCelula = new JMenuItem("Tipos de celulas");
        menuItemCelula.setActionCommand("Tipos de celulas");
        menuItemFormato = new JMenuItem("Formato do arquivo");
        menuItemFormato.setActionCommand("Formato do arquivo");
        menuItemSobre = new JMenuItem("Sobre");
        menuItemSobre.setActionCommand("Sobre");
        menuAjuda.add(menuItemAjuda);
        menuAjuda.add(menuItemCelula);
        menuAjuda.add(menuItemFormato);
        menuAjuda.add(menuItemSobre);
        barraDeMenus.add(menuAjuda);

        setJMenuBar(barraDeMenus);

        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado;
                switch (e.getActionCommand()) {
                    case "Abrir":
                        abrirArquivo();
                        break;
                        
                    case "Salvar":
                        salvarArquivo();
                        break;
                        
                    case "Editar":
                        //Adicionar o metodo para editar
                        JOptionPane.showMessageDialog(rootPane, "Editando...");
                        break;

                    case "Sair":
                        dispose();
                        break;

                    case "Como usar":
                        JOptionPane.showMessageDialog(rootPane, "Para abrir um arquivo, "
                                + "selecione Arquivo -> Abrir e então escolha o arquivo. "
                                + "\n\nPara salvar um arquivo, clique em Arquivo -> Salvar. "
                                + "O conteúdo atual do tabuleiro será salvo.");
                        break;
                        
                    case "Tipos de celulas":
                        JOptionPane.showMessageDialog(rootPane, "◦ Célula clássica: se comporta de maneira idêntica "
                                + "às células do jogo da vida original.\n"
                                + "*Quando viva, seu símbolo deve ser o caractere “+”;\n\n\n"
                                + "◦ Célula forte:\n"
                                + "– Caso esteja viva, morre apenas se seu número de vizinhos vivos é menor que 2.\n"
                                + "Caso contrário, continua viva.\n"
                                + "– Caso esteja morta, se torna viva se o seu número de vizinhos vivos é maior que\n"
                                + "4. Caso contrário, continua morta.\n"
                                + "*Quando viva, seu símbolo deve ser o caractere “@”.\n\n\n"
                                + "◦ Célula tímida:\n"
                                + "– Caso esteja viva, permanece viva apenas se nenhum de seus vizinhos está vivo.\n"
                                + "Caso contrário, morre.\n"
                                + "– Caso esteja morta, permanece morta enquanto algum de seus vizinhos estiver\n"
                                + "vivo. Caso contrário, se torna viva.\n"
                                + "*Quando viva, seu símbolo deve ser o caractere “&”.\n\n\n"
                                + "◦ Célula matemática:\n"
                                + "– Caso esteja viva, se mantém viva apenas se seu número de vizinhos vivos é impar.\n"
                                + "Caso contrário morre.\n"
                                + "– Caso esteja morta, só se torna viva se o seu número de vizinhos vivos é par. Caso\n"
                                + "contrário permanece morta.\n"
                                + "*Quando viva, seu símbolo deve ser o caractere “#”.\n\n\n"
                                + "◦ Célula de borda: sempre está morta, nunca viva.\n"
                                + "*Toda célula morta (não importando seu tipo) é representada pelo caractere “.”. ");
                        break;
                        
                    case "Formato do arquivo":
                        JOptionPane.showMessageDialog(rootPane, "◦O arquivo deve estar escrito desta maneira: \n"
                                + "- Tamanho do tabuleiro (x,y).\n"
                                + "ex: 2 2 criará um tabuleiro 2 por 2.\n\n"
                                + "- Tipos das celulas (+, @, &, #).\n"
                                + "ex: + + + + definirá as celulas do tabuleiro 2 por 2 como clássicas.\n\n"
                                + "- Estado das celulas (0/1)(0= morta 1= viva).\n"
                                + "ex: 1 1 1 1 definirá as celulas do tabuleiro 2 por 2 como vivas.");
                                
                        break;

                    case "Sobre":
                        JOptionPane.showMessageDialog(rootPane, "Interface para o Jogo da Vida.\n"
                                + "Feito por João Vitor e Kauan.");
                        break;
                }
            }
        };
        
        menuItemAbrir.addActionListener(menuListener);
        menuItemSalvar.addActionListener(menuListener);
        menuItemEditar.addActionListener(menuListener);
        menuItemSair.addActionListener(menuListener);
        menuItemAjuda.addActionListener(menuListener);
        menuItemCelula.addActionListener(menuListener);
        menuItemFormato.addActionListener(menuListener);
        menuItemSobre.addActionListener(menuListener);
        
        botaoAvancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Avançar");
            }
        });
        
        botaoAvancarA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelAvancar = new PainelAvancar(MainWindow.this);
            }
        });
        
        botaoPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Pausar");
            }
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void abrirArquivo(){
        int res = fileManager.showOpenDialog(this);
        
        if(res == JFileChooser.APPROVE_OPTION) {
            file = fileManager.getSelectedFile();
            
            try {
                tabuleiro = new Tabuleiro(file);
                // Mostrar Tabuleiro
                
            } catch(FileNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, 
                    "Este arquivo não existe.", 
                    "Arquivo não existente.", JOptionPane.ERROR_MESSAGE);
            } catch(NoSuchElementException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, 
                    "Este arquivo é inválido!"
                            + "\nVá na sessão ajuda para ver sobre o formato do arquivo.", 
                    "Arquivo inválido.", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(res == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(this, 
                    "Escolha um arquivo!", 
                    "Arquivo inválido.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void salvarArquivo() {
        int res = fileManager.showSaveDialog(this);
        file = fileManager.getSelectedFile();
        
        if(res == JFileChooser.APPROVE_OPTION) {
            try(PrintWriter writer = new PrintWriter(file);) {
                // Salvar arquivo.
                writer.print(tabuleiro.salvarTabuleiro());
                
                JOptionPane.showMessageDialog(rootPane,
                        "Tabuleiro salvo com sucesso!",
                        "Tabuleiro Salvo.", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(rootPane,
                        "Não foi possível salvar o tabuleiro.\n"
                                + "Salve corretamente!",
                        "Erro ao salvar o tabuleiro!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
