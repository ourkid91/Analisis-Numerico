package GuiaCero;

/**
 * Ejercicio 1 de la guía cero
 */

public class G00EJ01 {

    private int limite;

    public G00EJ01(int N){
        this.limite = N;
    }

    // Se le pasa por parámetro el caso a ejecutar
    // Casos posibles: a), b) o c)
    public void ejecutar(String caso){
        System.out.print("Guía Cero - Ejercicio 1");
        switch (caso){
            case "a": {
                System.out.println(".a)");
                System.out.println("Solución: " + casoA());
                break;
            }
            case "b": {
                System.out.println(".b)");
                System.out.println("Solución: " + casoB());
                break;
            }
            case "c": {
                System.out.println(".c)");
                System.out.println("Solución: " + casoC());
                break;
            }
        }
    }

    private int casoA(){
        int sumatoria = 0;
        // calculo para A
        for (int i = 1; i <= this.limite; i++) {
             sumatoria += i;
        }

        return sumatoria;
    }

    private int casoB(){
        int sumatoria = 0;
        //calculo para B;
        for (int i = 1; i <= this.limite; i++) {
            if(i % 2 == 0){
                sumatoria += i;
            }
        }

        return sumatoria;
    }

    private int casoC(){
        int sumatoria = 0;
        //calculo para C;
        for (int i = 1; i <= this.limite; i++) {
            if(i % 2 == 1){
                sumatoria += i;
            }
        }

        return sumatoria;
    }
}
