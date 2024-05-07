package s4;

// CAMINOS SIMPLES ENTRE DOS NODOS DIFERENTES DE UN GRAFO
// SE CALCULAN TODOS LOS CAMINOS POSIBLES PARA IR CALCULANDO EL MEJOR (MENOR COSTE)
// SE HACE PODA AL SER PESOS POSITIVOS Y ESTAR CALCULANDO UN COSTE MINIMO
// La COMPLEJIDAD TEMPORAL asi por backtracking es factorial (intratable)

import java.util.Random;

public class CaminoMejorPoda 
{
static int n;
static String[] v; // vector de n nodos
static int[][] w; // matriz de pesos de aristas
static int origen;
static int destino;
static boolean[] marca;// para no repetir nodos (caminos simples)
static int[] camino; // trayectoria de camino que va calculando
static int coste; // coste acumulado del camino que va calculando
static int longitud; // nivel o longitud de camino que va calculando
static int nsol; // el numero total de caminos hallados hasta destino

static int[] caminoMejor; // para anotar el camino mejor
static int costeMejor; // coste del camino mejor
static int longMejor; // numero aristas del camino mejor


public static void main(String arg[]) 
{
	n = Integer.parseInt(arg[0]);  // 200 acaba, 500 ya no
	
	v = new String[n];
	darValorNodos();

	w = new int[n][n];
	darValorPesosAristas();

	Random r = new Random();
	do {
		origen = r.nextInt(n);
		destino = r.nextInt(n);
	} while (origen == destino);
	
	System.out.println("El NODO ORIGEN ES " + v[origen]);
	System.out.println("El NODO DESTINO ES " + v[destino]);

	marca = new boolean[n];
	for (int j = 0; j < n; j++)
		marca[j] = false;
	marca[origen] = true;

	camino = new int[n];
	caminoMejor = new int[n];

	camino[0] = origen;
	longitud = 0;
	nsol = 0;
	coste = 0;
	costeMejor = 10000000; // mayor que cualquier posible
	
	long t1=System.currentTimeMillis();
	backtracking(origen);
	long t2=System.currentTimeMillis();
	System.out.println("\n"+"n="+n+"  ****  TIEMPO (milisegundos)="+(t2-t1)+"\n"); 

	if (nsol == 0)
		System.out.println("NO HAY NINGUN CAMINO");
	else 
	{
		System.out.println("EL CAMINO MEJOR ES:");
		for (int l = 0; l <= longMejor; l++)
			System.out.print(v[caminoMejor[l]] + "**");
		System.out.println();
		System.out.println("SU COSTE MEJOR ES=" + costeMejor);
	}
} // fin de método main


static void darValorNodos()
	// un ejemplo de valores genericos a los nodos
{
	for (int i = 0; i < n; i++)
		v[i] = "NODO" + i;
	System.out.println("LOS NODOS SE LLAMAN ASI:");
	for (int i = 0; i < n; i++)
		System.out.println(v[i]);
}


static void darValorPesosAristas()
// un ejempo de valores aleatorios a los pesos de las aristas
{
	Random r = new Random();
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			{
			int x = 10 + r.nextInt(90);
			if (x < 30)
				x = -1; // no hay arista
			w[i][j] = x;
			}

	for (int i = 0; i < n; i++)
		w[i][i] = -1; // sin bucles

	System.out.println("LA MATRIZ DE PESOS ES:");
	for (int i = 0; i < n; i++) 
		{
		for (int j = 0; j < n; j++)
			System.out.print(w[i][j] + "*");
		System.out.println();
		}
	}


static void backtracking(int actual) // es recursivo
{
	if (actual == destino) // es estado solución
	{
		nsol++;
		if (coste < costeMejor) 
			{
			for (int l = 0; l <= longitud; l++)
				caminoMejor[l] = camino[l];
			costeMejor = coste;
			longMejor = longitud;
			}
	}

	else
		for (int j = 0; j < n; j++)
			if (!marca[j] && w[actual][j] != -1 && coste < costeMejor) // PODA LOGICA
				{
				longitud++;
				coste = coste + w[actual][j];
				marca[j] = true;
				camino[longitud] = j;
				backtracking(j);
				longitud--;
				coste = coste - w[actual][j];
				marca[j] = false;
				}
}  // de backtracking

} // de clase
