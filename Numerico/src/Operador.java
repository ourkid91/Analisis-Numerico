public class Operador {

    // Tolerancia
    public static float precision = 0.01f;

    // Cantidad de decimales significativos a utilizar
    public static int decimalesSignificativos = 6;

    // Maximo de un vector en valor absoluto
    public static float normaInfinitaEnVector(float[] vector){
        float aux = Math.abs(vector[0]);
        for (int i = 0; i < vector.length; i++) {
            if(aux < Math.abs(vector[i]))
                aux = Math.abs(vector[i]);
        }
        return aux;
    }

    // Maximo de la diferencia entre vectores en valor absoluto
    // v1 es u(k), y v2 es u(k-1)
    public static float normaInfinitaDiferencialVectorial(float[] v1, float[] v2){
        float[] resultado = new float[v1.length];
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = v1[i] - v2[i];
        }
        return normaInfinitaEnVector(resultado);
    }

    // Presicion relativa - Error
    public static float presicionRelativa(float[] v1, float[] v2){
        return (normaInfinitaDiferencialVectorial(v1,v2))/normaInfinitaEnVector(v1);

    }

    // Redondeo de un numero a tantos decimalesSignificativos
    public static float redondear(float numero){
        float resultado = (float) (numero * Math.pow(10, decimalesSignificativos));
        resultado = Math.round(resultado);
        resultado = (float) (resultado/Math.pow(10, decimalesSignificativos));

        return resultado;
    }

    // Redondeo de un numero a tantos decimales pasados por parametro
    public static float redondear(float numero, int decimales){
        float resultado = (float) (numero * Math.pow(10, decimales));
        resultado = Math.round(resultado);
        resultado = (float) (resultado/Math.pow(10, decimales));

        return resultado;
    }

    // Muestra la solucion iterativa
    public static void mostrarSolucion(float[] vector, float error, int iteracion){
        float[] muestra = new float[vector.length];
        for (int i = 0; i < vector.length; i++) {
            muestra[i] = redondear(vector[i]);
        }

        System.out.print("Solucion: [\t");
        for (int i = 0; i < muestra.length; i++) {
            //System.out.print(muestra[i] + " \t");
            System.out.print(vector[i] + " \t");
        }
        //System.out.print(" ]\terror:\t" + Operador.redondear(error));
        System.out.print(" ]\terror:\t" + (error));
        System.out.println("\titeraciones:\t" + iteracion);
    }

    // Reinicia la solucion iterativa y la semilla a un estado nulo
    public static void reiniciarVariables(float[] x, float[] semilla){
        for (int i = 0; i < semilla.length; i++) {
            semilla[i] = 0;
            x[i] = 0;
        }
    }

    // Criterios para terminar con las iteraciones
    // S
    public static boolean condicionCorte(float error){
        if((error >= precision))
            return false;
        else
            return true;
    }

//    // Seteo de decimales significativos a utilizar
//    public static void setearDecimalesSignificativos(int decimales) {
//        decimalesSignificativos = decimales;
//    }

    // Seteo de la tolerancia a utilizar en el calculo del error
    public static void setearPresicion(float tolerancia){
        precision = tolerancia;
    }
}
