// PROBLEMA 2: COMBINACIONES DE n ELEMENTOS DE m EN m
//             SE CUMPLE QUE n>=m y m>=0

package alg77777777.t5;

public class Combinaciones

{  

	static int repeticiones; 
	//número de veces que se calcula p.e. combiDyV(5,2) 

	public static void main (String[] arg) 
	{

		System.out.println("ESCRIBIR LA MATRIZ DE SOLUCIONES");
		System.out.println("EN UN CASO MUY SENCILLO n=4;m=2");
		System.out.println (combiPD(4,2));

		System.out.println(); 
		System.out.println("PARA n=10;m=5");
		System.out.println (combiPD(10,5));
		repeticiones=0;
		System.out.println (combiDyV(10,5)+"**"+repeticiones);

		System.out.println();
		System.out.println("PARA n=40;m=20 QUEDARA COLGADO EN DyV");
		System.out.println (combiPD(40,20));
		System.out.println (combiDyV(40,20));

	} 


	public static long combiDyV(int n,int m) 
	/* Versión recursiva divide y vencerás. 
Resuelve muchas veces los mismos subproblemas.
Tiene complejidad no polinómica (intratable),
y el caso peo sucede ante llamadas del tipo 
combiDyV(n,n/2) */ 
	{
		if (n==5&&m==2) repeticiones++;
		if (n==m) return 1;
		else if (m==0) return 1;
		else return combiDyV(n-1,m) +  
				combiDyV(n-1,m-1);
	}


	public static long combiPD (int n,int m) 
	/* Versión programación dinámica. 
Cada subproblema solo  se resuelve una vez,
para lo que tenemos la matriz de subsoluciones
en la que las vamos anotando. 
La complejidad temporal es cuadrática 0(n*m) */
	{
		int[][]sol=new int[n+1][m+1];
		for (int i=0;i<=n;i++) sol[i][0]=1;

		for (int j=0;j<=m;j++) sol[j][j]=1;

		for (int j=1;j<=m;j++)
			for (int i=j+1;i<=n;i++) 
				sol[i][j]=sol[i-1][j]+sol[i-1][j-1];

		if (n<=10)  // escribir matriz calculada 
			for(int j=0;j<=m;j++)
			{
				for(int i=0;i<=n;i++)
					System.out.print(sol[i][j]+"*");
				System.out.println();
			}
		return sol[n][m];
	}

}