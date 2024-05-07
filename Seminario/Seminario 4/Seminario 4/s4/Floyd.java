package s4;

// CAMINOS MINIMOS EN UN GRAFO POR FLOYD-WARSHALL
// ES UNA SOLUCION POR PROGRAMACION DINAMICA
// SU COMPLEJIDAD TEMPORAL ES CUBICA O(n^3)

public class Floyd {
static String[] v; // vector de nodos
static int[][] pesos; // matriz de pesos de aristas
static int[][] costes; // matriz de costes de caminos de Floyd
static int[][] escalas; // matriz para escalas para trayectorias de Floyd

public static void main(String arg[]) 
{

	int origen= Integer.parseInt(arg[0]);
	int destino= Integer.parseInt(arg[1]);

	int n = 5;
	v = new String[n];
	for (int i = 0; i < n; i++)
		v[i] = "NODO" + i;

	pesos= new int[n][n];
	costes= new int[n][n];
	escalas= new int[n][n];

	rellenaPesos(pesos);
	System.out.println("MATRIZ DE PESOS ES:");
	escribeMatriz(pesos);

	floyd(pesos,costes,escalas);

	System.out.println("MATRIZ DE COSTES MINIMOS ES:");
	escribeMatriz(costes);

	System.out.println("MATRIZ ESCALAS ES:");
	escribeMatriz(escalas);

	caminoMinimo(origen, destino);
} // de main

static void floyd(int[][] pesos, int[][] costes, int[][] escalas)
/* ITERATIVO CON COMPLEJIDAD CUBICA O(n^3) */
{
	int n = pesos.length;

	// inicializamos la matriz de costes mínimos
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			costes[i][j] = pesos[i][j];

	// inicializamos la matriz de predecesores (recorrido)
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			escalas[i][j] = -1;

	// Aplicando Floyd-Warshall
	for (int k = 0; k < n; k++)
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (costes[i][k]+costes[k][j] < costes[i][j])
				{ // Si mejora la solución previa, modificamos
					costes[i][j] = costes[i][k]+costes[k][j]; 
					escalas[i][j] = k; // Indicamos el nodo escala
				}
}


static void caminoMinimo(int origen, int destino) 
{
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
	if (c >= 0) {
		trayectoria(i, c);
		System.out.print(v[c] + "---");
		trayectoria(c, j);
	}
}


static void rellenaPesos(int[][] w)
/* carga la matriz de costes ejemplo */
{
	for (int i = 0; i < w.length; i++)
		for (int j = 0; j < w.length; j++)
			w[i][j] = 10000000;
	w[0][1] = 10;
	w[0][3] = 30;
	w[0][4] = 100;
	w[1][2] = 50;
	w[2][4] = 10;
	w[3][2] = 20;
	w[3][4] = 60;
}


static void escribeMatriz(int[][] a)
/* imprime la matriz de costes */
{
	int n = a.length;
	for (int i = 0; i < n; i++) 
	{
		for (int j = 0; j < n; j++)
			System.out.print(a[i][j] + "//");
		System.out.println();
	}
	System.out.println();
}

}