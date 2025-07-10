package jv.kauan.trabalho_1_01.celulas;

public class CelulaForte extends Celula{

    public CelulaForte() {
        super(2, 4, "@");
    }
    
    @Override
    public boolean definirEstado(Celula[][] tabuleiro, int posL, int posC) {
        int qtdVizinhos = this.contarVisinhos(tabuleiro, posL, posC);
        
        if(this.isVivo) {
            if(qtdVizinhos < this.vizinhosMorrer) {
                return false;
            }
            return true;
        } else {
            if(qtdVizinhos > this.vizinhosRenascer) {
                return true;
            }
            return false;
        }
    }
    
}
