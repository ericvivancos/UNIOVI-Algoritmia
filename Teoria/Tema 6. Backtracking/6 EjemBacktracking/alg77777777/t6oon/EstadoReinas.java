package alg77777777.t6oon;

import java.util.Arrays;

public class EstadoReinas implements Estado {
	private static int n;	// número de reinas a colocar (coincide con el lado del tablero)
	private int[]sol;  //indica nº fila en que se coloca una reina
	
	private boolean[]a;//indica si se puede colocar una reina en la fila i
	private boolean[]b;//indica si se puede colocar una reina en la diagonal i+j
	private boolean[]c;//indica si se puede colocar una reina en la diagonal i-j+n-1
	private int col;			// nivel (columna)
	
	private int numHijo;	// número de hijo (fila) que estamos generando

	public EstadoReinas(int numReinas) {
		EstadoReinas.n= numReinas;	// número de reinas

		sol = new int [n];
		for (int i= 0; i<n; i++) sol[i]= -1;

		a= new boolean [n];
		for (int i=0;i<n;i++) a[i]=true;

		b= new boolean [2*n-1];
		for (int i=0;i<2*n-2;i++) b[i]=true;

		c= new boolean [2*n-1];
		for (int i=0;i<2*n-2;i++) c[i]=true;

		col= 0;	// paso en el que estamos en la construcción del estado solución
		numHijo= 0;	// Vamos a gerar el primer hijo
	}

	public EstadoReinas(EstadoReinas padre, int j, int i) {
		// copiamos toda la información del padre
		sol= Arrays.copyOf(padre.sol,n);
		a= Arrays.copyOf(padre.a,n);
		b= Arrays.copyOf(padre.b,2*n-1);
		c= Arrays.copyOf(padre.c,2*n-1);
		
		this.col= j;
		sol[j] =i;
		a[i]=false;
		b[i+j]=false;
		c[i-j+n-1]=false;
		this.col++;
	}

	@Override
	public boolean esSolucion() {
		if (col==n)
			return true;
		else
			return false;
	}

	@Override
	public Estado nextHijo() {
		Estado estadoHijo= null;
		boolean encontrado= false;
		
		while (!encontrado && numHijo<n) {
			if (a[numHijo] && b[numHijo+col] && c[numHijo-col+n-1])
			{
				estadoHijo= new EstadoReinas(this,col,numHijo);
				encontrado= true;
				
//				System.out.println("Nivel: "+j+" - Hijo: "+numHijo);
//				System.out.println(estadoHijo);
			}		
			numHijo++;	// generaremos el siguiente
		}
		
		return estadoHijo;
	}
	
	@Override
	public boolean hasNextHijos() {
		if (numHijo<n) 
			return true;
		else 
			return false;
	}
	
	@Override
	public String toString()
	{
		StringBuffer cad= new StringBuffer();
		
		for (int i= 0; i<n; i++)
		{
			for (int j= 0; j<n; j++)
				if (sol[j]==i)
					cad.append("X ");
				else
					cad.append("· ");
			cad.append("\n");
		}
		
		return cad.toString();				
	}


}
