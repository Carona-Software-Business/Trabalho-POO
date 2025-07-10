package jv.kauan.trabalho_1_01.celulas;

public class CelulaClassica extends Celula {
    
    int vizinhosMorrer2;
    
    public CelulaClassica() {
        super(2, 3, "+");
        this.vizinhosMorrer2 = 3;
    }
    
    @Override
    public boolean definirEstado(Celula[][] tabuleiro, int posL, int posC) {
        int qtdVizinhos = this.contarVisinhos(tabuleiro, posL, posC);
        
        if(!this.isVivo) {
            if(qtdVizinhos == this.vizinhosRenascer) {
                return true;
            }
            return false;
        } else {
            if(qtdVizinhos < this.vizinhosMorrer || qtdVizinhos > this.vizinhosMorrer2) {
                return false;
            }
            return true;
        }
    }
}
