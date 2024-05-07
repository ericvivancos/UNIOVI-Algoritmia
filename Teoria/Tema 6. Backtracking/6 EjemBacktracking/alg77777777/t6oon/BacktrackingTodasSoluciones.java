package alg77777777.t6oon;

public class BacktrackingTodasSoluciones
{
	static int cont= 0;
	
	public static void main(String[] args)
	{
		Estado estadoInicial= new EstadoReinas(6);
		backtracking(estadoInicial);
		if (cont==0)
			System.out.println("No hay solucion");
	}
	
	public static void backtracking(Estado e)
	{
		if (e.esSolucion())
		{
			System.out.println("Solución "+cont+ "----------------------------------------------------");
			System.out.println(e);
			cont++;
		}
		else	// eliminar si hay soluciones después de una solución
		{
			Estado estadoHijo= null;
			while(e.hasNextHijos()) {
				// siguiente estado hijo válido
				estadoHijo= e.nextHijo();
				
				if (estadoHijo!=null)	// puede que no queden hijos válidos
					backtracking(estadoHijo);
			} 
		}
	}
}