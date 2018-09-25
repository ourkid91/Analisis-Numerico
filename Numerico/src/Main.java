
public class Main {

    // Variables del Problema
    static float E = 1.0f;                                      // Módulo de Young
    static float I = 1.0f;                                      // Momento de Inercia
    static float L = 1.0f;                                      // Longitud de la viga
    static int numeroGrupo = 11;                                // Numero de Grupo
    static int dimension = 5;                                   // Dimension del Problema (n)

    // Variables para la solución del problema
    static float[] f = new float[dimension + 1];                // Solucion matricial
    static float[] semilla = new float[dimension + 1];          // Solucion de arranque


    public static void main(String[] args) {

        calcularFi();
        inicializarSemilla();
        GaussSeidel gs = new GaussSeidel(f,semilla);
        Sor sor = new Sor(f,semilla);

        // Ejemplo para correr diez iteraciones con Gauss-Seidel
        System.out.println("Metodo Gauss-Seidel");
        int iteracionGS = 0;
        while (iteracionGS < 10) {
            gs.calcular();
            Operador.mostrarSolucion(gs.obtenerSolucion(), gs.obtenerError(), iteracionGS);
            iteracionGS++;
        }

        System.out.println();
        System.out.println();

        // Ejemplo para correr diez iteraciones con Sor usando w = 1 (debería dar lo mismo que GS)
        System.out.println("Metodo Sor con w = 1");
        int iteracionSor = 0;
        while (iteracionSor < 10) {
            sor.calcular();
            Operador.mostrarSolucion(sor.obtenerSolucion(),sor.obtenerError(),iteracionSor);
            iteracionSor++;
        }
    }

    // Calculo del vector soluciòn F --> (f0,f1,f2...fn)
    static void calcularFi(){
        float x;
        float Qx;
        for (int i = 0; i <= dimension; i++) {
            x = (i*L) / (float) dimension;
            Qx = numeroGrupo + ((numeroGrupo*numeroGrupo) * (x - (x*x)));
            f[i] = (float) ((Qx / (E*I)) * Math.pow((L / (float) dimension),4));
        }
    }

    // Setear semilla en 0's    ->  [0,0,0,....0]
    static void inicializarSemilla(){
        for (int i = 0; i <= dimension; i++) {
            semilla[i] = 0.0f;
        }
    }
}
