package alg77777777.t6oon;

public class Backtracking1solucion
{
	static boolean haySolucion= false;
	
	public static void main(String[] args)
	{
		Estado estadoInicial= new EstadoReinas(35);
		long t1, t2;
		
		System.out.println("Estado inicial");
		System.out.println(estadoInicial);
		t1= System.currentTimeMillis();
		backtracking(estadoInicial);
		t2= System.currentTimeMillis();
		if (!haySolucion)
			System.out.println("No hay solucion");
		System.out.println("Tiempo backtracking: "+(t2-t1)+" milisegundos");
	}
	
	public static void backtracking(Estado e)
	{
		if (e.esSolucion())
		{
			System.out.println("Estado solución");
			System.out.println(e);
			haySolucion= true;
		}
		else
		{	
			Estado estadoHijo= null;
			while(e.hasNextHijos() && !haySolucion) {
				// siguiente estado hijo válido
				estadoHijo= e.nextHijo();
				
				if (estadoHijo!=null)	// puede que no queden hijos válidos
					backtracking(estadoHijo);
			} 
		}
	}
}