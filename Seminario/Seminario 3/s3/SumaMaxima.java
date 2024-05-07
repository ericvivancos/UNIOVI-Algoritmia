// PROBLEMA 11: SUMA MAXIMA DE TODAS LAS
// SUBSECUENCIAS DE n ELEMENTOS
// mediante 3 algoritmos diferentes

package s3;

import java.util.Random ;

public class SumaMaxima
{
static int []v= {5, -4, 3, 2, 5, -1};

/*  Este metodo retorna valores aleatorios a un vector de enteros, 
    utiliza para ello la clase Random del paquete java.util */
public static void ordenAleatorio (int[]v)
{
	Random r= new Random ();
	int n= v.length;
	for(int i=0;i<n;i++)
   		v[i]=r.nextInt(20000)-9999;
}     


/** este algoritmo es iterativo
Es cúbico 0(n^3)  */
public static int sumamax1(int[]v)
{
	int n=v.length;
	int max=0; 
	for(int i=0;i<n;i++)
		for(int j=i;j<n;j++)
		{
			int suma=0;
			for(int k=i;k<=j;k++)
				suma=suma+v[k];
			if(suma>max) max=suma;
		}
	return max;
} 

/** este algoritmo es iterativo
Es cuadrático 0(n^2) */
public static int sumamax2(int[]v)
{
	int n=v.length;
	int max = 0;
	for(int i=0;i<n;i++)
	{
		int suma=0;
		for(int j=i;j<n;j++)
		{
			suma=suma+v[j];
			if(suma>max) max=suma;
		}
	}
	return max;
} 

private static int sumarec(int iz,int de)
{	
	if (iz==de) {
		return v[iz];
	}
	else
	{
		int m=(iz+de)/2;
		int maxiz=sumarec(iz,m);
		int maxde=sumarec(m+1,de);
		int maxCent= maxDesdeCentro(iz,de,m);

		return mayor(maxiz,maxde,maxCent);
	}
}

/** este algoritmo es recursivo
	DyV POR DIVISIÓN con a=2;b=2;k=1 === O(nlogn)
 */
public static int sumamax3 (int[]v)
{
	return sumarecTraza (0,v.length-1);
}
	private static int sumarecTraza (int iz,int de)
{	
	if (iz==de) {
	//	System.out.println("Trivial v["+iz+"]= "+v[iz]);
		return v[iz];
	}
	else
	{
		int m=(iz+de)/2;
		int maxiz=sumarecTraza(iz,m);
		int maxde=sumarecTraza(m+1,de);
		int maxCent= maxDesdeCentro(iz,de,m);
	//	System.out.println("maxiz= "+maxiz+"\tmaxde= "+maxde+"\tmaxCent= "+maxCent+"\tmayor --> "+mayor(maxiz,maxde,maxCent));
		
		return mayor(maxiz,maxde,maxCent);
	}
}

/** calcula el valor máximo de la subsecuencia que pasa
 * de una mitad a la otra mitad 
 * @param iz índice del elemento más a la izquierda
 * @param de índice del elemento más a la derecha
 * @param m índice del elemento central de la subsecuencia
 * @return valor máximo de la subsecuencia
 */
private static int maxDesdeCentro(int iz, int de, int m)
{
	int s1=0;int maxs1=Integer.MIN_VALUE;
	for(int i=m;i>=iz;i--)
	{
		s1=s1+v[i];
		if (s1>maxs1) maxs1=s1;
	}
	int s2=0;int maxs2=Integer.MIN_VALUE;
	for(int i=m+1;i<=de;i++)
	{
		s2=s2+v[i];
		if (s2>maxs2) maxs2=s2;
	}
	return maxs1+maxs2;
}

/** devuelve el mayor de los tres
 */
private static int mayor(int a,int b,int c)
{ 
	if (a>=b && a>=c) return a;
	else 
		if (b>=a && b>=c) return b;
		else return c;
}


 
public static void main (String arg [] )
// PROBAR FUNCIONAMIENTO Y MEDIR TIEMPOS
{

System.out.println("PROBAR FUNCIONAMIENTO");
System.out.println("ALGORITMO1 iter= "+sumamax1(v));
System.out.println("ALGORITMO2 iter= "+sumamax2(v));
System.out.println("ALGORITMO3 DyV= "+sumamax3(v));

System.out.println("\n\nMEDIR TIEMPOS  (milisegundos)");

long tini;long tfin; long t1=0; long t2=0;long t3=0;
int res;
	
for (int n= 100; n<=100000000; n*= 2)
{
	v=new int [n];
	ordenAleatorio(v);
	if (t1<5000)
          {
	    tini= System.currentTimeMillis();
	    res=sumamax1(v);
	    tfin= System.currentTimeMillis();
	    t1= tfin-tini;
  	    if (t1>=50) System.out.println ("ALGORITMO1 ** n="+n+"\t"+t1);
	  }
	if (t2<5000)
          {		
	    tini= System.currentTimeMillis();
	    res=sumamax2(v);
	    tfin= System.currentTimeMillis();
	    t2= tfin-tini;
	    if (t2>=50) System.out.println ("ALGORITMO2 ** n="+n+"\t"+t2);
          }

	if (t3<5000)
          {
	    tini= System.currentTimeMillis();
	    res=sumamax3(v);
	    tfin= System.currentTimeMillis();
	    t3= tfin-tini;
	    if (t3>=50) System.out.println ("ALGORITMO3 ** n="+n+"\t"+t3);
          }
	System.out.println ("******************");
}
}
}