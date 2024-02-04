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

                System.out.print("Inserisci la riga e la colonna della cella che vuoi scoprire: ");
                riga = s.nextInt();
                colonna = s.nextInt();

                cm.scopriCella(riga, colonna);

            }


        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}