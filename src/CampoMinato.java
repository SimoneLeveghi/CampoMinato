import java.util.LinkedList;
import java.util.Random;

public class CampoMinato {
    private int[][] campo;
    private boolean gameOver;

    public CampoMinato(int righe, int colonne, int nMine) throws Exception {
        if(righe <= 0 || colonne <= 0) throw new Exception("Parametri invalidi");
        this.campo = new int[righe][colonne];

        inizializzaCampo(nMine);
    }

    public void inizializzaCampo(int nMine) throws Exception {
        for(int i = 0; i < this.campo.length; ++i) {
            for(int j = 0; j < this.campo[0].length; ++j) {
                this.campo[i][j] = 0;
            }
        }

        posizionaMine(nMine);
    }

    private void posizionaMine(int nMine) throws Exception {
        if(nMine <= 0) throw new Exception("Numero mine non valido");
        if(!gameOver) {
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

    public boolean scopriCella(int riga, int colonna) throws Exception {
        if(
                (riga < 0 || riga >= this.campo.length) ||
                (colonna < 0 || colonna >= this.campo[0].length)
        ) throw new Exception("Cella non valida");

        if(this.campo[riga][colonna] == -1) {
            gameOver = true;
        }
        else {
            this.campo[riga][colonna] = mineAdiacenti(riga, colonna);
            scopriCelleVuote(riga, colonna);
        }

        return !gameOver;
    }
    private int mineAdiacenti(int riga, int colonna) {
        int count = 0;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if(this.campo[riga - 1 + i][colonna - 1 + j] == -1) count++;
            }
        }

        return count;
    }
    private void scopriCelleVuote(int riga, int colonna) {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                if(mineAdiacenti(riga - 1 + i, colonna - 1 + j) == 0) {
                    scopriCelleVuote(riga - 1 + i, colonna - 1 + j);
                }
                else {
                    this.campo[riga][colonna] = mineAdiacenti(riga, colonna);
                }
            }
        }
    }
}
