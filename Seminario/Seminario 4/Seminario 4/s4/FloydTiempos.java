package s4;

// MIDE TIEMPOS de FLOYD CRECIENDO n
// SE ESPERA COMPLEJIDAD CUBICA O(n^3)

import java.util.Random;

public class FloydTiempos {
static String[] v; // vector de nodos
static int[][] pesos; // matriz de pesos
static int[][] costes; // matriz de costes
static int[][] escalas; // matriz para calcular trayectorias

public static void main(String arg[]) 
{
	long t1, t2;
	for (int n = 50; n <= 100000; n *= 2) 
	{
		v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODO" + i;

		pesos= new int[n][n];
		costes= new int[n][n];
		escalas= new int[n][n];

		rellenaPesos(pesos);

		t1 = System.currentTimeMillis();
		floyd(pesos,costes,escalas);
		caminoMinimo(0, n-1);  //por ejemplo de origen y destino
		t2 = System.currentTimeMillis();
		System.out.println("n=" + n + "***TIEMPO=" + (t2 - t1));
	}
} // de main


static void floyd(int[][] pesos, int[][] costes, int[][] escalas)
/* ITERATIVO CON COMPLEJIDAD CUBICA O(n^3) */
{
	int n = pesos.length;

	// inicialicemos costes
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			costes[i][j] = pesos[i][j];

	// inicialicemos escalas
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			escalas[i][j] = -1;

	// aplicando floyd
	for (int k = 0; k < n; k++)
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (costes[i][k]+costes[k][j] < costes[i][j]) 
				{
					costes[i][j]=costes[i][k]+costes[k][j];
					escalas[i][j] = k;
				}
}


static void caminoMinimo(int origen, int destino) {
	if (costes[origen][destino] >= 10000000)
		System.out.println("NO HAY CAMINO");
	else 
	{
		System.out.print(v[origen] + "---");
		trayectoria(origen, destino);
		System.out.println(v[destino]);
	}
}


static void trayectoria(int i, int j)
/* ES RECURSIVO y EL CASO PEOR es O(n), ES O(n) si escribe todos los nodos */

{
	int c = escalas[i][j];
	if (c >= 0) 
	{
		trayectoria(i, c);
		System.out.print(v[c] + "---");
			trayectoria(c, j);
	}
}


static void rellenaPesos(int[][] pesos)
/* carga la matriz de costes ejemplo */
{
	Random r = new Random();
	for (int i = 0; i < pesos.length; i++)
		for (int j = 0; j < pesos.length; j++)
			pesos[i][j] = 10+r.nextInt(90);
}

}
