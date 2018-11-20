
public class Main {

    // Variables del problema
    static double h0 = 0.599 * Math.pow(10,6);          // Distancia inicial entre la sonda y la superficie de la Tierra
    static double a_v0 = Math.PI / 2;                   // Angulo que se forma entre el eje x y el vector velocidad inicial de la sonda  
    
    static double x1 = -4.670 * Math.pow(10,6);         // Coordenada x de la Tierra en el sistema de referencia usado
    static double y1 = 0;                               // Coordenada y de la Tierra en el sistema de referencia usado
    static double r1 = 6.731 * Math.pow(10,6);	        // Radio de la Tierra
    static double M1 = 5972 * Math.pow(10,21);	        // Masa de la Tierra
    
    static double x2 = 379.7 * Math.pow(10,6);          // Coordenada x de la Luna en el sistema de referencia usado
    static double y2 = 0;                               // Coordenada y de la Luna en el sistema de referencia usado    
    static double r2 = 1.738 * Math.pow(10,6);      	// Radio de la Luna
    static double M2 = 73.48 * Math.pow(10,21);	        // Masa de la Luna
    
    static double x0 = x1 - r1 - h0;                    // Coordenada inicial x de la sonda
    static double y0 = 0;                               // Coordenada inicial y de la sonda
    
    static double G = 6.674 * Math.pow(10,-11);	        // Constante universal de gravitación 
    static double w = 4.236 * Math.pow(10,-7);          // Velocidad angular
    
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
    static double tiempo_total;	        //Tiempo de la simulación
    static double paso_de_termino;		//Paso de término (Tn+1 - Tn)
    
    
    public static void main(String[] args) {
    	
        // (PARTE 1)
    	M2 = 0;
    	w = 0;
    	inicializar_velocidad(Math.sqrt((G * M1) / (r1 + h0)));
    	tiempo_total = 2 * Math.PI * (r1 + h0) / v0;
    	paso_de_termino = 0.1;
    	euler(x0 , y0 , vx0 , vy0,paso_de_termino,tiempo_total);
    	runge_kutta_2(x0 , y0 , vx0 , vy0,paso_de_termino,tiempo_total);
    	
    }
    
    private static void euler(double x0, double y0 , double vx0, double vy0, double h, double tiempo_simulacion) {
    	
    	double an = x0;
    	double bn = y0;
    	double cn = vx0;
    	double dn = vy0;

    	double an_siguiente=0,bn_siguiente=0,cn_siguiente=0,dn_siguiente=0;
    	
    	int i = 0;
    	while(!choco(an,bn) && (h * i < tiempo_simulacion)) {
    		
    		an_siguiente = an + h * fa(an,bn,cn,dn);
    		bn_siguiente = bn + h * fb(an,bn,cn,dn);
    		cn_siguiente = cn + h * fc(an,bn,cn,dn);
    		dn_siguiente = dn + h * fd(an,bn,cn,dn);
    		
    		if(i % 1000 == 0) {
    		System.out.println("t"+(i+1)+"	x = "+ an_siguiente +"  y = "+ bn_siguiente +"  vx = "+ cn_siguiente +"  vy = "+ dn_siguiente);
    		}
    		
    		an = an_siguiente;
    		bn = bn_siguiente;
    		cn = cn_siguiente;
    		dn = dn_siguiente;
    		
    		i++;
    	}
		System.out.println("t"+(i+1)+"	x = "+ an_siguiente +"  y = "+ bn_siguiente +"  vx = "+ cn_siguiente +"  vy = "+ dn_siguiente);    	
    }

    private static void runge_kutta_2(double x0, double y0 , double vx0, double vy0, double h, double tiempo_simulacion) {
    	
    	double an = x0;
    	double bn = y0;
    	double cn = vx0;
    	double dn = vy0;
    	
    	double q1a,q2a,q1b,q2b,q1c,q2c,q1d,q2d,an_siguiente=0,bn_siguiente=0,cn_siguiente=0,dn_siguiente=0;
    	
    	int i = 0;
    	while(!choco(an,bn) && (h * i < tiempo_simulacion)) {
    		q1a = h * fa(an,bn,cn,dn);
    		q1b = h * fb(an,bn,cn,dn);
    		q1c = h * fc(an,bn,cn,dn);
    		q1d = h * fd(an,bn,cn,dn);
    	
    		q2a = h * fa(an + q1a,bn + q1b,cn + q1c,dn + q1d);
    		q2b = h * fb(an + q1a,bn + q1b,cn + q1c,dn + q1d);
    		q2c = h * fc(an + q1a,bn + q1b,cn + q1c,dn + q1d);
    		q2d = h * fd(an + q1a,bn + q1b,cn + q1c,dn + q1d);	
    	
    		an_siguiente = an + (double) 1/2 * (q1a + q2a);
    		bn_siguiente = bn + (double) 1/2 * (q1b + q2b);
    		cn_siguiente = cn + (double) 1/2 * (q1c + q2c);
    		dn_siguiente = dn + (double) 1/2 * (q1d + q2d);
		
    		if(i % 1000 == 0) {
    			System.out.println("t"+(i+1)+"	x = "+ an_siguiente +"  y = "+ bn_siguiente +"  vx = "+ cn_siguiente +"  vy = "+ dn_siguiente);
    		}
		
    		an = an_siguiente;
    		bn = bn_siguiente;
    		cn = cn_siguiente;
    		dn = dn_siguiente;
		
    		i++;
    	}
    	System.out.println("t"+(i+1)+"	x = "+ an_siguiente +"  y = "+ bn_siguiente +"  vx = "+ cn_siguiente +"  vy = "+ dn_siguiente);    	
    }
    
	public static double fa(double an, double bn, double cn, double dn) {		
		return cn;
	}
	
	public static double fb(double an, double bn, double cn, double dn) {		
		return dn;
	}
	
	public static double fc(double an, double bn, double cn, double dn) {
		actualizar_variables(an,bn);
		
		double fuerza_1_x = G * M1 / Math.pow(d1,2) * Math.cos(a_f1);
		double fuerza_2_x = G * M2 / Math.pow(d2,2) * Math.cos(a_f2);
		double fuerza_c_x = Math.pow(w,2) * dc * Math.cos(a_fc);
		
		return fuerza_1_x + fuerza_2_x + fuerza_c_x;
	}
	
	public static double fd(double an, double bn, double cn, double dn) {
		actualizar_variables(an,bn);
		
		double fuerza_1_y = G * M1 / Math.pow(d1,2) * Math.sin(a_f1);
		double fuerza_2_y = G * M2 / Math.pow(d2,2) * Math.sin(a_f2);
		double fuerza_c_y = Math.pow(w,2) * dc * Math.sin(a_fc);
		
		return fuerza_1_y + fuerza_2_y + fuerza_c_y;
	}
	
	//Devuelve True si la sonda choco con la Tierra o con la Luna.
	private static boolean choco(double x, double y) {
		return choco_con_tierra(x,y) || choco_con_luna(x,y);
	}

	private static boolean choco_con_tierra(double x, double y) {
		if(Math.sqrt(Math.pow(x - x1,2) + Math.pow(y,2) ) < r1) {
			System.out.println("Chocó con la Tierra");
			return true;
		}
		return false;
	}
	
	private static boolean choco_con_luna(double x, double y) {
		if(Math.sqrt(Math.pow(x - x2,2) + Math.pow(y,2) ) < r2) {
			System.out.println("Chocó con la Luna");
			return true;
		}
		return false;
	}

	private static void inicializar_velocidad(double velocidad_inicial) {
		v0 = velocidad_inicial;
		vx0 = v0 * Operador.redondear(Math.cos(a_v0));
		vy0 = v0 * Math.sin(a_v0);
	}
    
	private static void actualizar_variables(double x, double y) {
		d1 = Math.sqrt(Math.pow((x1 - x),2) + Math.pow((y1 - y),2));
		d2 = Math.sqrt(Math.pow((x2 - x),2) + Math.pow((y2 - y),2));
		dc = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));		
		a_f1 = arco_tangente((x1 - x),(y1 - y));
		a_f2 = arco_tangente((x2 - x),(y2 - y));
		a_fc = arco_tangente(x,y);		
	}
	
	private static double arco_tangente(double x, double y) {
		if (x == 0) {
			if (y > 0) return (double) 1/2 * Math.PI;
			else return (double) 3/2 * Math.PI;
		}
		
		double angulo = Math.atan(y / x);

		if (x < 0) angulo = angulo + Math.PI;
		else if (y < 0) angulo = angulo + 2 * Math.PI;
		
		return angulo;
	}
	
}