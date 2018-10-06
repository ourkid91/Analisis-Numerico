public class Sor {

    private float[] f;                       //Solucion matricial
    private float[] u;                       //Solucion al problema
    private float[] semilla;                 //Solucion al problema de arranque
    private float error;                     //Error de la solucion
    private int dimension;                   //Dimension del problema (n)
    private float w;                         //  1 <= Omega <= 2.   Omega == 1 es equivalente a usar Gauss-Seidel

    public Sor(float[] solucion, float[] semilla){
        u = new float[solucion.length];
        f = solucion;
        this.semilla = semilla.clone();
        error = 1.0f;
        dimension = u.length - 1;
        w = 1.0f;
    }

    public void calcular() {

        // Solución en u[0]
        u[0] = 0.0f;

        // Solucion en u[1]
        u[1] = (semilla[1] * (1 - w)) + (w * ((4*u[0] + 4*semilla[2] - semilla[3] + f[1]) / 5.0f));

        // Solucion de u[2] a u[n-2]
        for (int i = 2; i <= dimension - 2; i++) {
            u[i] = (semilla[i] * (1 - w)) + (w * (((-1)*u[i-2] + 4*u[i-1] + 4*semilla[i+1] - semilla[i+2] + f[i]) / 6.0f));
        }

        // Solucion de u[n-1]
        u[dimension - 1] = (semilla[dimension - 1] * (1 - w)) + (w * (((-1)*u[dimension-3] + 4*u[dimension-2] + 4*semilla[dimension] + f[dimension-1]) / 5.0f));

        // Solucion de u[n]
        u[dimension] = 0.0f;


        error = Operador.presicionRelavita(u,semilla);

        for (int i = 0; i < u.length; i++) {
            semilla[i] = u[i];
        }
    }

    public float[] obtenerSolucion(){
        return u;
    }

    public float obtenerError(){
        return error;
    }

    public void setearOmega(float omega) {
        w = omega;
    }

    public void reiniciarVariables(){
        Operador.reiniciarVariables(u,semilla);
        error = 1.0f;
    }

    public float omegaOptimo(){
        float omega = 1.0f;
        int minimasIteraciones;
        int iteraciones = 1;
        float errorOptimo = 1.0f;
        setearOmega(omega);

        while (!Operador.condicionCorte(error)){
            calcular();
            iteraciones++;
        }
        minimasIteraciones = iteraciones - 1;

        float incremento = 0.05f;
        for (float w = (1+incremento); Operador.redondear(w,2) <= 1.95f; w += incremento){
            reiniciarVariables();
            iteraciones = 1;
            setearOmega(Operador.redondear(w,2));
            while (!Operador.condicionCorte(error)){
                calcular();
                iteraciones++;
            }
            if((iteraciones - 1 <= minimasIteraciones)){
                if((iteraciones - 1 == minimasIteraciones) && (error < errorOptimo)){
                    minimasIteraciones = iteraciones - 1;
                    omega = Operador.redondear(w,2);
                    errorOptimo = error;
                } else if(iteraciones - 1 != minimasIteraciones){
                    minimasIteraciones = iteraciones - 1;
                    omega = Operador.redondear(w,2);
                    errorOptimo = error;
                }
            }
            System.out.print("Omega: "+Operador.redondear(w,2)+ "  " +"\t");
            Operador.mostrarSolucion(u,error,iteraciones - 1);
            System.out.println("Omega optimo: " + omega);
        }
        return omega;
    }
}
