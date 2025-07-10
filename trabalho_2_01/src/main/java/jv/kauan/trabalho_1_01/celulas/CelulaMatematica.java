package jv.kauan.trabalho_1_01.celulas;

public class CelulaMatematica extends Celula {

    public CelulaMatematica() {
        super(0, 1, "#");
    }
    
    @Override
    public boolean definirEstado(Celula[][] tabuleiro, int posL, int posC) {
        int qtdVizinhos = this.contarVisinhos(tabuleiro, posL, posC);
        
        if(this.isVivo) {
            if(qtdVizinhos % 2 == 0) {
                return false;
            }
            return true;
        } else {
            if(qtdVizinhos % 2 == 0) {
                return true;
            }
            return false;
        }
    }
    
}
