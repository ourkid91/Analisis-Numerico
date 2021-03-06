public class Operador {

    // Cantidad de decimales significativos a utilizar
    public static int decimalesSignificativos = 6;

    // Redondeo de un numero a tantos decimalesSignificativos
    public static float redondear(double numero){
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

    // Seteo de decimales significativos a utilizar
    public static void setearDecimalesSignificativos(int decimales) {
        decimalesSignificativos = decimales;
    }
    
}