public class Sor {

    private float[] f;                       //Solucion matricial
    private float[] u;                       //Solucion al problema
    private float[] semilla;                 //Solucion al problema de arranque
    private float error;                     //Error de la solucion
    private int dimension;                   //Dimension del problema (n)
    private GaussSeidel gs;
    private float w;                         //  1 <= Omega <= 2.   Omega == 1 es equivalente a usar Gauss-Seidel

    public Sor(float[] solucion, float[] semilla){
        u = new float[solucion.length];
        f = solucion;
        this.semilla = semilla.clone();
        error = 0.0f;
        dimension = u.length - 1;
        gs = new GaussSeidel(solucion,semilla);
        w = 1.0f;
    }

    public void calcular() {

        // Itero con Gauss-Seidel para obtener los u(k+1)
        gs.calcular();

        // Solución en u[0]
        u[0] = 0.0f;

        // Solucion en u[1]
        u[1] = u[1] * (1 - w) + w * gs.obtenerSolucion()[1];

        // Solucion de u[2] a u[n-2]
        for (int i = 2; i <= dimension - 2; i++) {
            u[i] = u[i] * (1 - w) + w * gs.obtenerSolucion()[i];
        }

        // Solucion de u[n-1]
        u[dimension-1] = u[dimension-1] * (1 - w) + w * gs.obtenerSolucion()[dimension-1];

        // Solucion de u[n]
        u[dimension] = u[dimension] * (1 - w) + w * gs.obtenerSolucion()[dimension];

        error = Operador.presicionRelavita(u,semilla);

        for (int i = 0; i < u.length; i++) {
            semilla[i] = u[i];
        }
    }

    public float[] obtenerSolucion(){return u;}

    public float obtenerError(){return error;}
}
