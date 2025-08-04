package jv.kauan.trabalho_2_01;

import java.util.Scanner;

public class mainAntigo {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        int numInteracoes;
        
        // Passo 1
        System.out.print("Digite a quantidade de linhas do tabuleiro: ");
        int m = leitor.nextInt();
        System.out.print("Digite a quantidade de colunas do tabuleiro: ");
        int n = leitor.nextInt();
        
        System.out.println("Tipos: ");
        String[][] tipos = new String[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                tipos[i][j] = leitor.next();
            }
        }
        
        System.out.println("Estados: ");
        int[][] estados = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                estados[i][j] = leitor.nextInt();
            }
        }
        
        Tabuleiro tabuleiro = new Tabuleiro(m, n, tipos, estados);
        
        // Passo 4
        System.out.print("Digite o numero de interacoes: ");
        numInteracoes = leitor.nextInt();
        
        // Passo 5
        tabuleiro.comecarSimulacao(numInteracoes);
        
        // 0 1 0 0 1 0 0 1 0
    }
}
