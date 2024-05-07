// PROBLEMA 1: CÁLCULO DE FIBONACCI DE ORDEN n

package alg77777777.t5;

public class Fibonacci 
{  

	static int repeticiones; 
	//veces que se calcula p.e. fib(5) 

	public static void main (String[] arg) 
	{
		System.out.println("PARA n=20");
		System.out.println (fibPD(20));
		repeticiones=0;
		System.out.println (fibDyV(20)+"**"+repeticiones);

		System.out.println();
		System.out.println("PARA n=70 QUEDARA COLGADO EN DyV");
		System.out.println (fibPD(70));
		System.out.println (fibDyV(70));
	}


	public static long fibDyV (int n) 
	{
		if (n==5)  repeticiones++;
		if (n<=1)
			return n;
		else return fibDyV(n-1)+fibDyV(n-2);
	}


	public static long fibPD (int n) 
	/* Versión programación dinámica. Cada subproblema solo se 
resuelve una vez, para lo que tenemos un vector de 
subsoluciones en el que las vamos anotando. 
La complejidad temporal está claro que es lineal 0(n).
	 */
	{
		long[]f=new long[n+1]; // vector de subsoluciones
		f[0]=0;f[1]=1;
		for (int i=2;i<=n;i++)
			f[i]=f[i-1]+f[i-2];
		return f[n]; 
	}

}