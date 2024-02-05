import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            CampoMinato cm = new CampoMinato(10, 10, 20);
            Scanner s = new Scanner(System.in);

            System.out.println("CAMPO MINATO");
            int riga, colonna;

            while(!cm.isGameOver()) {
                System.out.println(cm.stampaCampo());

                System.out.print("Cosa vuoi fare?\n1. Scopri cella;\n2. Posiziona bandiera");
                int opzione;
                do {
                    System.out.println("\nOpzione: ");
                    opzione = s.nextInt();
                } while(opzione < 0 || opzione > 2);

                if(opzione == 1) {
                    System.out.print("Inserisci la riga e la colonna della cella che vuoi scoprire: ");
                    riga = s.nextInt();
                    colonna = s.nextInt();

                    cm.scopriCella(riga, colonna);
                }
                else {
                    System.out.print("Inserisci la riga e la colonna della cella in cui vuoi posizionare la bandiera: ");
                    riga = s.nextInt();
                    colonna = s.nextInt();

                    cm.posizionaBandiera(riga, colonna);
                }
            }


        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}