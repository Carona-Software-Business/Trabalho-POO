package jv.kauan.trabalho_1_01;

import jv.kauan.trabalho_1_01.celulas.CelulaTimida;
import jv.kauan.trabalho_1_01.celulas.CelulaClassica;
import jv.kauan.trabalho_1_01.celulas.CelulaMatematica;
import jv.kauan.trabalho_1_01.celulas.Celula;
import jv.kauan.trabalho_1_01.celulas.CelulaBorda;
import jv.kauan.trabalho_1_01.celulas.CelulaForte;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    
    Celula[][] tabuleiroAtual;
    Celula[][] tabuleiroProximo;
    
    private int interacao;
    
    // Construtores
    public Tabuleiro(int linhas, int colunas, String tipos[][], int[][] estados) {
        this.linhas = linhas + 2;
        this.colunas = colunas + 2;
        
        tabuleiroAtual = new Celula[this.linhas][this.colunas];
        tabuleiroProximo = new Celula[this.linhas][this.colunas];
        definirTiposCelulas(tipos);
        definirEstadosCelulas(estados);
        
        tabuleiroProximo = copiarTabuleiro(tabuleiroAtual);
        
        interacao = 0;
    }
    
    public Tabuleiro(File arquivo) throws FileNotFoundException, NoSuchElementException {
        Scanner scanner = new Scanner(arquivo);
        
        linhas = scanner.nextInt() + 2;
        colunas = scanner.nextInt() + 2;
        
        tabuleiroAtual = new Celula[linhas][colunas];
        
        String[][] simbolos = new String[linhas][colunas];
        for(int i = 0; i < (linhas - 2); i++)
            for(int j = 0; j < (colunas - 2); j++) {
                simbolos[i][j] = scanner.next();
            }
        definirTiposCelulas(simbolos);
        
        int[][] estados = new int[linhas][colunas];
        for(int i = 0; i < (linhas - 2); i++)
            for(int j = 0; j < (colunas - 2); j++){
                estados[i][j] = scanner.nextInt();
            }
        definirEstadosCelulas(estados);
        
        tabuleiroProximo = copiarTabuleiro(tabuleiroAtual);
        
        printarTabuleiro();
        
        interacao = 0;
    }
    
    // Construção do tabuleiro
    private void definirTiposCelulas(String[][] tipos) {
        for(int i = 0; i < linhas; i++) {
            for(int j = 0; j < colunas; j++) {
                // Definir celulas de borda
                if(i == 0 || i == (linhas - 1) || j == 0 ||
                        j == (colunas - 1)) {
                    tabuleiroAtual[i][j] = new CelulaBorda();
                    
                } else {
                    // Definir Celulas
                    switch (tipos[i-1][j-1]) {
                        case "+":
                            tabuleiroAtual[i][j] = new CelulaClassica();
                            break;
                        case "@":
                            tabuleiroAtual[i][j] = new CelulaForte();
                            break;
                        case "&":
                            tabuleiroAtual[i][j] = new CelulaTimida();
                            break;
                        case "#":
                            tabuleiroAtual[i][j] = new CelulaMatematica();
                            break;
                        default:
                            System.out.println("Esse tipo nao existe!");
                            tabuleiroAtual[i][j] = new CelulaClassica();
                            break;
                    }
                }
            }
        }
    }
    
    private void definirEstadosCelulas(int[][] estados) {
        for(int i = 1; i < (linhas - 1); i++) {
            for(int j = 1; j < (colunas - 1); j++) {
                if(estados[i-1][j-1] == 0) {
                    tabuleiroAtual[i][j].setVida(false);
                } else { 
                    tabuleiroAtual[i][j].setVida(true);
                }
            }
        }
    }
    
    // Simulação de Tabuleiro
    private void percorrerTabuleiro() {
        boolean situacao;
        
        for(int i = 1; i < (linhas - 1); i++) {
            for(int j = 1; j < (colunas - 1); j++) {
                situacao = tabuleiroAtual[i][j].definirEstado(tabuleiroAtual, i, j);
                tabuleiroProximo[i][j].setVida(situacao);
            }
        }
    }
    
    public void avancarInteracao() {
        percorrerTabuleiro();
        
        interacao++;
        
        // Mostrar Tabuleiro
        
        tabuleiroAtual = copiarTabuleiro(tabuleiroProximo);
        tabuleiroProximo = copiarTabuleiro(tabuleiroAtual);
    }
    
    public void avancarInteracao(int numeroInteracoes) {
        for(int i = 0; i < numeroInteracoes; i++) {
            avancarInteracao();
        }
    }
    
    // Printar - FAZER
    @Override
    public String toString() {
        String tabuleiroString = "";
        for(int i = 0; i < tabuleiroAtual.length; i++) {
            for(int j = 0; j < tabuleiroAtual[0].length; j++) {
                tabuleiroString += tabuleiroAtual[i][j].toString() + " ";
            }
            tabuleiroString += "\n";
        }
        return tabuleiroString;
    }
    
    public void printarTabuleiro() {
        for(int i = 0; i < linhas; i++) {
            for(int j = 0; j < colunas; j++) {
                System.out.print(tabuleiroProximo[i][j].toString());
            }
            System.out.println("");
        }
    }
    
    // Outros
    private Celula[][] copiarTabuleiro(Celula[][] origem) {
        Celula[][] destino = new Celula[linhas][colunas];
        
        for(int i = 0; i < linhas; i++) {
            for(int j = 0; j < colunas; j++) {
                destino[i][j] = origem[i][j].clone();
            }
        }
        
        return destino;
    }
    
    public String salvarTabuleiro() {
        String retorno = (linhas - 2) + " " + (colunas - 2) + "\n";
        
        for(int i = 1; i < (linhas - 1); i++) {
            for(int j = 1; j < (colunas - 1); j++) {
                retorno += tabuleiroAtual[i][j].getSimbolo();
                if(j != (colunas - 2)) {
                    retorno += " ";
                } else
                    retorno += "\n";
            }
        }
        
        for(int i = 1; i < (linhas - 1); i++) {
            for(int j = 1; j < (colunas - 1); j++) {
                if(tabuleiroAtual[i][j].getVida())
                    retorno += 1;
                else 
                    retorno += 0;
                if(j != (colunas - 2)) {
                    retorno += " ";
                } else 
                    retorno += "\n";
            }
        }
        
        return retorno;
    }
    
    // Antigo
    public void comecarSimulacao(int numeroInteracoes) {
        System.out.println("Ponto Inicial:");
        printarTabuleiro();
        
        for(int i = 0; i < numeroInteracoes; i++) {
            System.out.println();
            System.out.println("Interacao " + (i+1));
            
            percorrerTabuleiro();
            
            printarTabuleiro();
            
            tabuleiroAtual = copiarTabuleiro(tabuleiroProximo);
            tabuleiroProximo = copiarTabuleiro(tabuleiroAtual);
        }
    }
}
