import java.util.LinkedList;
import java.util.Random;

public class CampoMinato {
    int[][] campo;

    public CampoMinato(int righe, int colonne) {
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
            int cella = r.nextInt(0, celleDisponibili.size());
            celleDisponibili.remove(cella);

            int riga = cella / this.campo[0].length;
            int colonna = cella % this.campo[0].length;

            this.campo[riga][colonna] = -1;
        }
    }


}
