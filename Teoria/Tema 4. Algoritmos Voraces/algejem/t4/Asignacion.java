//PROBLEMA 12:ASIGNACIÓN  n TRABAJADORES - n TAREAS



/* Este programa resuelve el problema mediante 
los dos devoradores básicos de cálculo de mínimos */

public class Asignacion
{
	static int [][]c;
	static int [] sol;

	public static void main (String arg [] )
	{

		int n = 4;
		c = new int[n][n] ;
		sol= new int [n];
		rellena (c);
		escribe (c); 

		System.out.println ("EL COSTE DEVORADOR1 ES="+devorador1 (c,sol));
		escribe1 (sol);
		System.out.println ();
		System.out.println ("EL COSTE DEVORADOR2 ES="+devorador2 (c,sol));
		escribe2 (sol);

	} // fin de main


	public static int devorador1 (int[][]c,int[]sol)
	/* este método es de complejidad cuadratica O(n^2) 
   se calcula mínimo de filas */
	{
		int n= sol.length;
		boolean []marca = new boolean[n];

		for (int k=0;k<n;k++) marca[k]=false; 

		int min,pos;
		int coste=0; 

		for(int i=0;i<n;i++)
		{
			min=Integer.MAX_VALUE; 
			pos=-1;
			for(int j=0;j<n;j++)
				if (!marca[j] && c[i][j]<min)
				{min=c[i][j];
				pos=j;}
			sol[i]=pos;
			coste=coste+min;
			marca[pos]=true;
		}
		return coste; 
	}  // fin de devorador1   


	public static int devorador2 (int[][]c,int[]sol)
	/* este metodo es de complejidad cuadratica O(n^2) 
   se calcula mínimo de columnas */
	{
		int n= sol.length;
		boolean []marca = new boolean[n];

		for (int k=0;k<n;k++) marca[k]=false; 

		int min,pos;
		int coste=0; 

		for(int j=0;j<n;j++)
		{
			min=Integer.MAX_VALUE; 
			pos=-1;
			for(int i=0;i<n;i++)
				if (!marca[i] && c[i][j]<min)
				{min=c[i][j]; 
				pos=i;}
			sol[j]=pos;
			coste=coste+min;
			marca[pos]=true;
		}
		return coste;  
	}  // fin de devorador2   


	public static void  rellena (int[][]c)
	/* carga la matriz de costes ejemplo */
	{
		c[0][0]=11;c[0][1]=12;c[0][2]=18;c[0][3]=40;
		c[1][0]=14;c[1][1]=15;c[1][2]=13;c[1][3]=22;
		c[2][0]=11;c[2][1]=17;c[2][2]=19;c[2][3]=23;
		c[3][0]=17;c[3][1]=14;c[3][2]=20;c[3][3]=28;
	}  // fin de rellena   


	public static void escribe (int[][]c)
	/* escribe la matriz de costes */
	{
		int n= c.length;
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
				System.out.print (c[i][j]+"//");
			System.out.println ();
		}
	}  // fin de escribe  


	public static void escribe1(int[]sol)
	/* escribe la asignacion calculada */
	{
		int n= sol.length;
		System.out.println ("LA ASIGNACION CALCULADA ES:"); 
		for (int i=0; i<n; i++)
			System.out.println ("AL TRABAJADOR "+i+" LE TOCA LA TAREA "+sol[i]);
		System.out.println ();
	}  // fin de escribe1   


	public static void escribe2(int[]sol)
	/* escribe la asignacion calculada */
	{
		int n= sol.length;
		System.out.println ("LA ASIGNACION CALCULADA ES:"); 
		for (int i=0; i<n; i++)
			System.out.println ("LA TAREA "+i+" LE TOCA AL TRABAJADOR "+sol[i]);
		System.out.println ();
	}  // fin de escribe2   

}  // FIN 