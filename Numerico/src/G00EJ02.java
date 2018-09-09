package GuiaCero;

public class G00EJ02 {

    private int limite;

    public G00EJ02(int N){
        this.limite = N;
    }

    // Se le pasa por parámetro el caso a ejecutar
    // Casos posibles: a), b) , c) o d)
    public void ejecutar(String caso){
        System.out.print("Guía Cero - Ejercicio 2");
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
            case "d": {
                System.out.println(".d)");
                System.out.println("Solución: " + casoD());
                break;
            }
        }
    }

    private double casoA(){
        double sumatoria = 0;
        // calculo para A
        for (int i = 1; i <= this.limite; i++) {
            sumatoria += 1d/i;
        }

        return sumatoria;
    }

    private double casoB(){
        double sumatoria = 0;
        //calculo para B;
        for (int i = 1; i <= this.limite; i++) {
            sumatoria += 1d/(i*i);
        }

        return sumatoria;
    }

    private double casoC(){
        double sumatoria = 0;
        //calculo para C;
        for (int i = 1; i <= this.limite; i++) {
            sumatoria += Math.pow(-1,i) * 1d/i;
        }

        return sumatoria;
    }
    private double casoD(){
        double sumatoria = 0;
        //calculo para D;
        for (int i = 1; i <= this.limite; i++) {
            sumatoria += Math.pow(-1,i) * 1d/(i*i);
        }

        return sumatoria;
    }
}
