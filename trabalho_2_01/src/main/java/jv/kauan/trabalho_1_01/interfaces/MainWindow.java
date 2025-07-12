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
import java.util.InputMismatchException;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import jv.kauan.trabalho_1_01.Tabuleiro;

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
    
    //private JTextArea textTabuleiro;
    
    private JFileChooser fileManager;
    private File file;
    
    private Tabuleiro tabuleiro;
    
    private int interacao;
    
    private boolean edicaoSalva;
    
    private Timer timer;
    private int delay;
    private int repeticaoMax;
    private int rep;
    private ActionListener simulacaoListener;
    private boolean pausado;
    private boolean comecar;

    public MainWindow() {
        super("Jogo da vida");
        
        
        fileManager = new JFileChooser();
        fileManager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        interacao = 0;
        tabuleiro = new Tabuleiro();
        
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        
        painelNorte = new JPanel();
        painelNorte.setLayout(new BoxLayout(painelNorte, BoxLayout.Y_AXIS));
        painelNorte.setAlignmentX(CENTER_ALIGNMENT);
        add(painelNorte, BorderLayout.NORTH);
        
        labelJogoDaVida = new JLabel("Jogo Da Vida");
        labelJogoDaVida.setFont(new Font("SansSerif", Font.BOLD, 48));
        painelNorte.add(labelJogoDaVida);
        
        labelInteracoes = new JLabel("Interação: " + interacao);
        labelInteracoes.setFont(new Font("SansSerif", Font.PLAIN, 20));
        labelInteracoes.setBorder(new EmptyBorder(50, 0, 20, 0));
        painelNorte.add(labelInteracoes);
        
        labelJogoDaVida.setAlignmentX(CENTER_ALIGNMENT);
        labelInteracoes.setAlignmentX(CENTER_ALIGNMENT);
        
        painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(painelCentro, BorderLayout.CENTER);
        
        /*
        textTabuleiro = new JTextArea();
        textTabuleiro.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textTabuleiro);
        painelCentro.add(scrollPane, BorderLayout.CENTER);
        */
        painelContainerTabuleiro = new JPanel();
        painelContainerTabuleiro.setLayout(new FlowLayout(FlowLayout.CENTER));
        //painelContainerTabuleiro.setBackground(Color.red);
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
                        if (abrirArquivo()) {
                            criarInterfaceTabuleiro();
                            interacao = 0;
                            labelInteracoes.setText("Interacao: " + interacao);
                        }

                        break;

                    case "Salvar":
                        if (tabuleiro.tabulerioVazio())
                            JOptionPane.showMessageDialog(rootPane,
                                    "Não há nenhum tabuleiro no momento!\nAbra um tabuleiro!",
                                    "Abra um tabuleiro.", JOptionPane.INFORMATION_MESSAGE);
                        else
                            salvarArquivo();
                        break;
                        
                    case "Editar":
                        //Adicionar o metodo para editar
                        if(tabuleiro.tabulerioVazio())
                            JOptionPane.showMessageDialog(rootPane, 
                                "Não há nenhum tabuleiro no momento!\nAbra um tabuleiro!", 
                                "Abra um tabuleiro.", JOptionPane.INFORMATION_MESSAGE);
                        else
                            painelEditar = new PainelEditar(MainWindow.this, tabuleiro);
                            if(edicaoSalva) {
                                criarInterfaceTabuleiro();
                                edicaoSalva = false;
                            }
                        break;

                    case "Sair":
                        dispose();
                        break;

                    case "Como abrir":
                        JOptionPane.showMessageDialog(rootPane, "📁 Como abrir um arquivo:\n"
                                + "◦Para abrir um arquivo salvo anteriormente, siga os passos abaixo:\n"
                                + "\n"
                                + "1 - Clique no menu Arquivo, localizado no canto superior esquerdo da tela.\n"
                                + "\n"
                                + "2 - Selecione a opção Abrir.\n"
                                + "\n"
                                + "3 - Na janela que será exibida, escolha o arquivo desejado no seu computador.\n"
                                + "\n"
                                + "4 - Clique em Abrir para carregar o conteúdo no programa.\n"
                                + "\n"
                                + "*Certifique-se de que o arquivo esteja no formato compatível com o sistema.");
                        break;
                        
                    case "Como salvar":
                        JOptionPane.showMessageDialog(rootPane, "💾  Como salvar um tabuleiro:\n"
                                + "◦Para salvar o tabuleiro atual, siga os passos:\n"
                                + "\n"
                                + "1 - Com o tabuleiro carregado ou editado na tela, vá até o menu Arquivo, no canto superior esquerdo.\n"
                                + "\n"
                                + "2 - Clique na opção Salvar.\n"
                                + "\n"
                                + "3 - Uma janela será exibida permitindo que você:\n"
                                + "\n"
                                + "    Escolha a pasta onde deseja salvar o arquivo.\n"
                                + "\n"
                                + "    Digite o nome do arquivo.\n"
                                + "\n"
                                + "4 - Após definir o local e o nome, clique em Salvar.\n"
                                + "\n"
                                + "* O tabuleiro será gravado no local escolhido com as configurações atuais.");
                        break;
                    
                    case "Como editar":
                        JOptionPane.showMessageDialog(rootPane, "✏️ Como editar o tabuleiro:\n"
                                + "◦Para editar uma célula do tabuleiro, siga os passos abaixo:\n"
                                + "\n"
                                + "1 - Vá até o menu Arquivo, no canto superior esquerdo da tela.\n"
                                + "\n"
                                + "2 - Clique na opção Editar.\n"
                                + "\n"
                                + "3 - Uma nova janela será aberta exibindo o tabuleiro em formato de botões.\n"
                                + "\n"
                                + "4 - Clique no botão correspondente à célula que deseja modificar.\n"
                                + "\n"
                                + "5 - Um menu será exibido para que você selecione:\n"
                                + "\n"
                                + "    ◦O tipo da célula (Clássica, Forte, Tímida ou Matemática).\n"
                                + "\n"
                                + "    ◦O estado da célula (Viva ou Morta).\n"
                                + "\n"
                                + "6 - Após fazer as alterações, clique em Salvar na janela de edição.\n"
                                + "\n"
                                + "*A célula será atualizada com as novas configurações.\n"
                                + "\n"
                                + "Atenção: o tabuleiro só será alterado se você clicar no botão Salvar.");
                        break;

                    case "Como avançar":
                        JOptionPane.showMessageDialog(rootPane, "⏭️ Como avançar o tabuleiro:\n"
                                + "O sistema oferece duas formas de avançar as interações do tabuleiro:\n"
                                + "\n"
                                + "🔹 Avançar manualmente:\n"
                                + "1 - Na tela principal, clique no botão Avançar.\n"
                                + "\n"
                                + "2 - O tabuleiro será atualizado para a próxima interação (uma única vez).\n"
                                + "\n"
                                + "🔹 Avançar automaticamente:\n"
                                + "1 - Clique no botão Avançar automaticamente, também localizado na tela principal.\n"
                                + "\n"
                                + "2 - Uma nova janela será exibida.\n"
                                + "\n"
                                + "3 - Nessa janela, você deve:\n"
                                + "\n"
                                + "    ◦Informar o número de interações desejado.\n"
                                + "\n"
                                + "    ◦Definir o tempo de delay (em milissegundos) entre cada interação.\n"
                                + "\n"
                                + "4 - Após configurar, clique em Iniciar.\n"
                                + "\n"
                                + "5 - O tabuleiro começará a avançar automaticamente com base nas configurações fornecidas.\n\n"
                                + "⚠️ Observações:\n"
                                + "◦Enquanto as interações automáticas estiverem em andamento, o tabuleiro será atualizado a cada intervalo de tempo definido.\n"
                                + "\n"
                                + "◦Você pode interromper a execução precionando o botão de pausar.");
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
                //JOptionPane.showMessageDialog(rootPane, "Avançar");
                if(tabuleiro.tabulerioVazio())
                    JOptionPane.showMessageDialog(rootPane, 
                            "Não há nenhum tabuleiro no momento!\nAbra um tabuleiro!", 
                            "Abra um tabuleiro.", JOptionPane.INFORMATION_MESSAGE);
                else {
                    tabuleiro.avancarInteracao();
                    criarInterfaceTabuleiro();
                    labelInteracoes.setText("Interação: " + (++interacao));
                }
            }
        });
        
        botaoAvancarA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabuleiro.tabulerioVazio())
                    JOptionPane.showMessageDialog(rootPane, 
                            "Não há nenhum tabuleiro no momento!\nAbra um tabuleiro!", 
                            "Abra um tabuleiro.", JOptionPane.INFORMATION_MESSAGE);
                else {
                    painelAvancar = new PainelAvancar(MainWindow.this);
                    if(comecar) {
                        //System.out.println("Rep Max: " + repeticaoMax);
                        comecarSimulacao();
                        rep = 0;
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
                System.out.println("Começou");
                if(!pausado && rep < repeticaoMax) {
                    tabuleiro.avancarInteracao();
                    labelInteracoes.setText("Interação: " + (++interacao));
                    criarInterfaceTabuleiro();
                    rep++;
                } else if(rep >= repeticaoMax){
                    comecar = false;
                    timer.stop();
                    botaoPausar.setEnabled(false);
                    botaoAvancar.setEnabled(true);
                    botaoAvancarA.setEnabled(true);
                    rep = 0;
                }
            }
        };
        comecar = false;
        pausado = false;
        rep = 0;
        
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
                        "Este arquivo não existe.",
                        "Arquivo não existente.", JOptionPane.ERROR_MESSAGE);
                return false;

            } catch (NoSuchElementException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this,
                        "Este arquivo é inválido!"
                        + "\nVá na sessão ajuda para ver sobre o formato do arquivo.",
                        "Arquivo inválido.", JOptionPane.ERROR_MESSAGE);
                return false;

            }

        } else if (res == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Escolha um arquivo!",
                    "Arquivo inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
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
    
    private void criarInterfaceTabuleiro() {
        painelContainerTabuleiro.remove(painelTabuleiro);
        
        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
        painelTabuleiro.setMinimumSize(new Dimension(600, 400));
        painelTabuleiro.setPreferredSize(new Dimension(600, 400));
        painelTabuleiro.setMaximumSize(new Dimension(600, 400));
        //painelTabuleiro.setBackground(Color.BLUE);
        painelContainerTabuleiro.add(painelTabuleiro);
        
        labelTabuleiro = new JLabel[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i = 0; i < tabuleiro.getLinhas(); i++) {
            for(int j = 0; j < tabuleiro.getColunas(); j++) {
                labelTabuleiro[i][j] = new JLabel(tabuleiro.toString(i, j));
                labelTabuleiro[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                painelTabuleiro.add(labelTabuleiro[i][j]);
            }
        }
        
        painelContainerTabuleiro.revalidate();
        painelContainerTabuleiro.repaint();
    }
    
    private void comecarSimulacao() {
        System.out.println("Criou");
        timer = new Timer(delay, simulacaoListener);
        timer.setInitialDelay(1500);
        timer.start();
    }
    
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
