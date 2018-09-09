package GuiaCero;

public class Tiempo {

    private long start;
    private long end;

    public Tiempo(){

    }

    public void start(){
        start = System.currentTimeMillis();
    }

    public void end(){
        end = System.currentTimeMillis() - start;
        System.out.println("tiempo: "+ end * Math.pow(10,-3) + " segundos");
    }
}
