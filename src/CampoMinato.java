import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
                this.campo[i][j] = -2;
            }
        }

        posizionaMine(nMine);
    }

    private void posizionaMine(int nMine) throws Exception {
        if(nMine <= 0) throw new Exception("Numero mine non valido");
        if(!gameOver) {
            LinkedList<Integer> celleDisponibili = new LinkedList<Integer>();
            for(int i = 0; i < this.campo.length * this.campo[0].length; ++i) {
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
                if(cella == -2) {
                    ret += "■";
                }
                else if(cella == 0) {
                    ret += "□";
                }
                else if(cella <= -3) {
                    ret += "⚑";
                }
                else {
                    ret += cella;
                }
                ret += "\t";
            }
            ret += "\n";
        }

        return ret;
    }

    public boolean scopriCella(int riga, int colonna) throws Exception {
        if(
                !(riga < 0 || riga >= this.campo.length) ||
                !(colonna < 0 || colonna >= this.campo[0].length)
        ) {

            if (this.campo[riga][colonna] == -1) {
                gameOver = true;
            } else {
                if((this.campo[riga][colonna] = mineAdiacenti(riga, colonna)) == 0) {
                    scopriCelleVuote(riga, colonna);
                }

            }
        }
        return !gameOver;
    }
    private int mineAdiacenti(int riga, int colonna) {
        int count = 0;
        for(int i = -1; i < 2; ++i) {
            for(int j = -1; j < 2; ++j) {
                try {
                    if(this.campo[riga + i][colonna + j] == -1) count++;
                }
                catch(Exception ignored) {}
            }
        }

        return count;
    }
    private void scopriCelleVuote(int riga, int colonna) {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.add(riga * this.campo[0].length + colonna);
        while(!ll.isEmpty()) {
            int cella = ll.pop();
            int rigaC = cella / this.campo[0].length;
            int colonnaC = cella % this.campo[0].length;

            this.campo[rigaC][colonnaC] = mineAdiacenti(rigaC, colonnaC);
            visited.add(cella);

            int cellaSup;
            if(this.campo[rigaC - 1][colonnaC] == 0 && !visited.contains(cellaSup = (rigaC - 1) * this.campo[0].length + colonnaC)) {
                ll.add(cellaSup);
            }

            int cellaInf;
            if(this.campo[rigaC + 1][colonnaC] == 0 && !visited.contains(cellaInf = (rigaC + 1) * this.campo[0].length + colonnaC)) {
                ll.add(cellaInf);
            }

            int cellaDx;
            if(this.campo[rigaC][colonnaC - 1] == 0 && !visited.contains(cellaDx = (rigaC) * this.campo[0].length + colonnaC - 1)) {
                ll.add(cellaDx);
            }

            int cellaSx;
            if(this.campo[rigaC][colonnaC + 1] == 0 && !visited.contains(cellaSx = (rigaC) * this.campo[0].length + colonnaC + 1)) {
                ll.add(cellaSx);
            }
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }
}