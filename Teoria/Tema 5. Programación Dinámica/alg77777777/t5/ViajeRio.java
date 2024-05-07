// PROBLEMA 5: VIAJE MÁS BARATO POR RÍO
// VAMOS A PLANTEAR EL PROBLEMA EXPLICADO EN
// EL APARTADO 5.6 DEL LIBRO BASE

package alg77777777.t5;

public class ViajeRio

{  

	public static void main (String arg [] )
	{
		int n=5; // número embarcaderos

		int[][]t=new int[n][n]; // tarifas desde ei a ej (i<j)

		t[0][1]=6;t[0][2]=9;t[0][3]=12;t[0][4]=22;
		t[1][2]=5;t[1][3]=12;t[1][4]=17;
		t[2][3]=4;t[2][4]=14;
		t[3][4]=9;
		System.out.println("TABLA DE TARIFAS ENTRE EMBARCADEROS:");
		escribirMatriz(t);

		int[][]c=new int[n][n]; // costes mínimos a calcular (i<j)

		viajeRioPD (t,c);

		System.out.println();
		System.out.println("TABLA DE COSTES MINIMOS:");
		escribirMatriz(c);

		System.out.println();
		System.out.println("PARA SABER LA RUTA DE COSTE MINIMO HABRIA QUE");
		System.out.println("HACER ALGO PARECIDO A FLOYD (PRACTICA 2013)");
	}


	static void viajeRioPD (int[][]t,int[][]c) 
	/* programación dinámica 
La complejidad temporal es cúbica 0(n^3) */
	{
		int n=t.length;

		for (int i=0;i<n;i++) c[i][i]=0;

		for (int i=n-2;i>=0;i--) //¡OJO!:descendente 
			for (int j=i+1;j<n;j++)
			{
				int min=Integer.MAX_VALUE;
				for (int k=i+1;k<=j;k++)
				{
					int valork=t[i][k]+c[k][j];
					if (valork<min) min=valork;
				}
				c[i][j]=min;
			}
	}

	static void escribirMatriz(int[][]a)
	{
		int n=a.length;
		for (int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				if (a[i][j]<10) System.out.print(" *"+a[i][j]);
				else System.out.print("*"+a[i][j]);
			System.out.println();
		}   
	}

}