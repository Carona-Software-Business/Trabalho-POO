package jv.kauan.trabalho_1_01.celulas;

public class CelulaBorda extends Celula{

    public CelulaBorda() {
        super(0, 0, ".");
        this.isVivo = false;
    }
    
    @Override
    public boolean definirEstado(Celula[][] tabuleiro, int posL, int posC) {
        return false;
    }
    
}
