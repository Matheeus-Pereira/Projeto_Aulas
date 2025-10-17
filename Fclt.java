package projeto_aulas;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Fclt {

    public static void escreve(String t[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        for (int i = 0; i < t.length; i++) {
            escreve(t[i]);

        }
    }

    public static void escreve(int t[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        for (int i = 0; i < t.length; i++) {
            escreve(t[i]);

        }
    }

    public static void escreve(double t[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        for (int i = 0; i < t.length; i++) {
            escreve(t[i]);

        }
    }

    public static void escreve(int t) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println(t);
    }

    public static void escreve(float t) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println(t);
    }

    public static void escreve(double t) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println(t);
    }

    public static void escreve(String t) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println(t);
        //essa função é static pra não ter que declarar ela no main 
        /*exemplo:
        Ouro o = new Ouro();  
        o.escreve("Olá mundo!!!");
        se não tivesse static*
         */
    }

    public static void escreve(String d, float l) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.printf(d, l);
    }
}
