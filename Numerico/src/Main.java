
public class Main {

    // Variables del Problema
    static float E = 1.0f;                                      // Módulo de Young
    static float I = 1.0f;                                      // Momento de Inercia
    static float L = 1.0f;                                      // Longitud de la viga
    static int numeroGrupo = 11;                                // Numero de Grupo
    static int dimension = 5;                                   // Dimension del Problema (n)

    // Variables para la solución del problema
    static float[] q;                // Funcion de carga
    static float[] f;                // Solucion matricial
    static float[] semilla;          // Solucion de arranque


    public static void main(String[] args) {

        // Inicialización de variables y métodos
        calcularVariablesIniciales();
        inicializarSemilla();
        Sor sor = new Sor(f,semilla);

        // Seteo de precisión y cantidad de decimales significativos
        // (ITEM 4 DEL TP, CONSIDERANDO CAMBIAR LA DIMENSION PARA CADA CASO)
        Operador.setearPresicion(0.01f);
        Operador.setearDecimalesSignificativos(6);

        // Calculo SOR para distintos omegas. Desde 1 a 1.95, incrementando omega de a 0.05
        // (ITEM 4 DEL TP, CONSIDERANDO CAMBIAR LA DIMENSION PARA CADA CASO)
        float incrementoOmega = 0.05f;
        for (float w = 1; w <= 1.95f; w+= incrementoOmega) {
            System.out.println();
            System.out.println("Metodo Sor | dimensión n = " + dimension + " | Utilizando w = " + Operador.redondear(w,2));
            int iteracionSor = 1;
            sor.setearOmega(Operador.redondear(w,2));
            while (!Operador.condicionCorte(sor.obtenerError())) {
                sor.calcular();
                iteracionSor++;
            }
            Operador.mostrarSolucion(sor.obtenerSolucion(),sor.obtenerError(),iteracionSor - 1);
            sor.reiniciarVariables();
        }

        // Omega Optimo
        // (ITEM 5 DEL TP, CONSIDERANDO CAMBIAR LA DIMENSION PARA CADA CASO)
        System.out.println();
        System.out.println();
        System.out.println("Calculo de Omega òptimo experimentalmente:");
        float omegaOptimo = sor.omegaOptimo();
        sor.reiniciarVariables();
        System.out.println();
        System.out.println("El Omega que presento la menor cantidad de iteraciones con el menor error es: " + omegaOptimo);

        // Calculo SOR utilizando el omega optimo calculado previamente
        // (ITEM 6 DEL TP, CONSIDERANDO CAMBIAR LA DIMENSION PARA CADA CASO)
        Operador.setearPresicion(0.0001f);
        System.out.println();
        System.out.println("Metodo Sor | dimensión n = " + dimension + " | Utilizando w = " + Operador.redondear(omegaOptimo,2));
        int iteracionSorOptimo = 1;
        sor.setearOmega(omegaOptimo);
        while (!Operador.condicionCorte(sor.obtenerError())) {
            sor.calcular();
            iteracionSorOptimo++;
        }
        Operador.mostrarSolucion(sor.obtenerSolucion(),sor.obtenerError(),iteracionSorOptimo - 1);
        sor.reiniciarVariables();
    }

    // Calculo del vector solución F --> (f0,f1,f2...fn)
    // Calculo del vector función de carga q(x) --> (q0,q1,q2..qn)
    static void calcularVariablesIniciales(){
        q = new float[dimension + 1];
        f = new float[dimension + 1];
        float x;
        for (int i = 0; i <= dimension; i++) {
            x = (i*L) / (float) dimension;
            q[i] = numeroGrupo + ((numeroGrupo*numeroGrupo) * (x - (x*x)));
            f[i] = (float) ((q[i] / (E*I)) * Math.pow((L / (float) dimension),4));
        }
    }

    // Setear semilla en 0's    ->  [0,0,0,....0]
    static void inicializarSemilla(){
        semilla = new float[dimension + 1];
        for (int i = 0; i <= dimension; i++) {
            semilla[i] = 0.0f;
        }
    }

}
