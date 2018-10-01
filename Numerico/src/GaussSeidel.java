public class GaussSeidel {

    private float[] f;                       //Solucion matricial
    private float[] u;                       //Solucion al problema
    private float[] semilla;                 //Solucion al problema de arranque
    private float error;                     //Error de la solucion
    private int dimension;                   //Dimension del problema (n)

    public GaussSeidel(float[] solucion,float[] semilla){
        u = new float[solucion.length];
        f = solucion;
        this.semilla = semilla.clone();
        error = 0.0f;
        dimension = u.length - 1;
    }

    public void calcular() {
        // Solución en u[0]
        u[0] = 0.0f;

        // Solucion en u[1]
        u[1] = (4*u[0] + 4*semilla[2] - semilla[3] + f[1]) / 5.0f;

        // Solucion de u[2] a u[n-2]
        for (int i = 2; i <= dimension - 2; i++) {
            u[i] = (- u[i-2] + 4*u[i-1] + 4*semilla[i+1] - semilla[i+2] + f[i]) / 6.0f;
        }

        // Solucion de u[n-1]
        u[dimension-1] = (- u[dimension-3] + 4*u[dimension-2] + 4*semilla[dimension] + f[dimension-1]) / 5.0f;

        // Solucion de u[n]
        u[dimension] = 0.0f;

        error = Operador.presicionRelavita(u,semilla);

        for (int i = 0; i < u.length; i++) {
            semilla[i] = u[i];
        }
    }

    public void reiniciarVariables(){
        Operador.reiniciarVariables(u,semilla);
        error = 0.0f;
    }

    public float[] obtenerSolucion(){return u;}

    public float obtenerError(){return error;}
}
