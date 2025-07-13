package jv.kauan.trabalho_1_01.celulas;

public abstract class Celula implements Cloneable {
    protected boolean isVivo;
    
    protected int vizinhosMorrer;
    protected int vizinhosRenascer;
    //protected int vizinhos;
    
    protected String simbolo;
    //private String simboloMorte = ".";

    public Celula(int vizinhosMorrer, int vizinhosRenascer, String simbolo) {
        this.vizinhosMorrer = vizinhosMorrer;
        this.vizinhosRenascer = vizinhosRenascer;
        this.simbolo = simbolo;
    }
    
    // SetVida
    public void setVida(boolean vida) {
        this.isVivo = vida;
    }
    
    // GetVida
    public boolean isVivo() {
        return this.isVivo;
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    
    
    // Metodo que irá analisar se ela continua viva ou não
    //protected abstract void definirEstado();
    public abstract boolean definirEstado(Celula[][] tabuleiro, int posL, int posC);
    
    
    // Analisar Visinhos
    protected int contarVisinhos(Celula[][] tabuleiro, int posL, int posC) {
        int qtdVizinhos = 0;
        
        for(int i = (posL - 1); i <= (posL + 1); i++) {
            for(int j = (posC - 1); j <= (posC + 1); j++) {
                if(i != posL || j != posC) {
                    if(tabuleiro[i][j].isVivo()){
                        qtdVizinhos++;
                    }
                }
            }
        }
        
        return qtdVizinhos;
    }
    
    @Override
    public Celula clone() {
        try {
            return (Celula) super.clone();
            
        } catch(CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        if(this.isVivo) return this.simbolo + " ";
        else return "." + " ";
    }
    
    
   
}
