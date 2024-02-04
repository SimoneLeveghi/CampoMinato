import java.util.LinkedList;
import java.util.Random;

public class CampoMinato {
    private int[][] campo;

    public CampoMinato(int righe, int colonne) throws Exception {
        if(righe <= 0 || colonne <= 0) throw new Exception("Parametri invalidi");
        this.campo = new int[righe][colonne];
    }

    public void inizializzaCampo() {
        for(int i = 0; i < this.campo.length; ++i) {
            for(int j = 0; j < this.campo[0].length; ++j) {
                this.campo[i][j] = 0;
            }
        }
    }

    public void posizionaMine(int nMine) {
        LinkedList<Integer> celleDisponibili = new LinkedList<Integer>();
        for(int i = 0; i < campo.length * campo[0].length; ++i) {
            celleDisponibili.add(i);
        }

        Random r = new Random();

        for(int i = 0; i < nMine; ++i) {
            int cella = r.nextInt(celleDisponibili.size());
            celleDisponibili.remove(cella);

            int riga = cella / this.campo[0].length;
            int colonna = cella % this.campo[0].length;

            this.campo[riga][colonna] = -1;
        }
    }

    public String stampaCampo() {
        String ret = "";

        for(int[] riga : this.campo) {
            for(int cella : riga) {
                ret += (
                        cella <= -1? "■" :
                                (cella == 0? "□" : cella)
                ) + "\t";
            }
        }

        return ret;
    }
}
