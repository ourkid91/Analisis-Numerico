
public class Main {

    // Variables del Problema
    static double h0 = 0.599 * Math.pow(10,6);              // Distancia inicial entre la sonda y la superficie de la Tierra
    static double a_v0 = Math.PI / 2;                       // Angulo que se forma entre el eje x y el vector velocidad inicial de la sonda  
    
    static double x1 = -4.670 * Math.pow(10,6);             // Coordenada x de la Tierra en el sistema de referencia usado
    static double y1 = 0;                                   // Coordenada y de la Tierra en el sistema de referencia usado
    static double r1 = 6.731 * Math.pow(10,6);				// Radio de la Tierra
    static double M1 = 5972 * Math.pow(10,21);				// Masa de la Tierra
    
    static double x2 = 379.7 * Math.pow(10,6);              // Coordenada x de la Luna en el sistema de referencia usado
    static double y2 = 0;                                   // Coordenada y de la Luna en el sistema de referencia usado    
    static double r2 = 1.738 * Math.pow(10,6);				// Radio de la Luna
    static double M2 = 73.48 * Math.pow(10,21);				// Masa de la Luna
    
    static double x0 = x1 - r1 - h0;                        // Coordenada inicial x de la sonda
    static double y0 = 0;                                   // Coordenada inicial y de la sonda
    
    static double G = 6.674 * Math.pow(10,-11);				// Constante universal de gravitación 
    static double w = 4.236 * Math.pow(10,-7);				// Velocidad angular
    
    static double v0;        // Velocidad inicial
    static double vx0;       // Velocidad inicial en x
    static double vy0;       // Velocidad inicial en y
    
    static double d1 = Math.sqrt(Math.pow((x1 - x0),2) + Math.pow((y1 - y0),2));   // Distancia entre la sonda y la Tierra 
    static double d2 = Math.sqrt(Math.pow((x2 - x0),2) + Math.pow((y2 - y0),2));   // Distancia entre la sonda y la Luna
    static double dc = Math.sqrt(Math.pow(x0,2) + Math.pow(y0,2));                 // Distancia entre la sonda y el centro de masa del sistema 

    static double a_f1 = Math.atan((y1 - y0) / (x1 - x0));    // Angulo entre el eje x y la fuerza que ejerce la Tierra sobre la sonda 
    static double a_f2 = Math.atan((y2 - y0) / (x2 - x0));    // Angulo entre el eje x y la fuerza que ejerce la Luna sobre la sonda
    static double a_fc = Math.atan(y0 / x0);	              // Angulo entre el eje x y la fuerza centrifuga
    
    // Variables para la solucion del problema    
    static double tiempo_total;			//Tiempo de la simulación
    
    
    public static void main(String[] args) {
    	
        // (PARTE 1)
    	inicializar_velocidad(Math.sqrt((G * M1) / (r1 + h0)));
    	tiempo_total = 2 * Math.PI * (r1 + h0) / v0;

    }


	private static void inicializar_velocidad(double velocidad_inicial) {
		v0 = velocidad_inicial;
		vx0 = v0 * Math.cos(a_v0);
		vy0 = v0 * Math.sin(a_v0);
		
	}
    
	private static void actualizar_variables(double x, double y) {
		d1 = Math.sqrt(Math.pow((x1 - x),2) + Math.pow((y1 - y),2));
		d2 = Math.sqrt(Math.pow((x2 - x),2) + Math.pow((y2 - y),2));
		dc = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
		a_f1 = Math.atan((y1 - y) / (x1 - x));
		a_f2 = Math.atan((y2 - y) / (x2 - x));
		a_fc = Math.atan(y / x);		
	}
	
}