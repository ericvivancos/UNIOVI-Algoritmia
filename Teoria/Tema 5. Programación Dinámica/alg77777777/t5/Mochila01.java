// PROBLEMA 4: PROBLEMA DE LA MOCHILA (0/1)
// VAMOS A PLANTEAR EL DOS CASOS
// LOS DOS FALLAN POR EL DEVORADOR VISTO

// AQUÍ SOLO CALCULA EL BENEFICIO MÁXIMO
// EN EL LIBRO BASE VIENE CÓMO SABER QUÉ SE CARGA

package alg77777777.t5;

public class Mochila01

{  

	private static boolean imprime= false;

	public static void main (String arg [] )
	{
		//CASO 1: MUY SENCILLO. EL DEVORADOR VISTO FALLA (DA 60)
		int m=7; // tope mochila 
		int n=3; // número elementos

		int[]beneficio=new int[n]; // beneficio/Kg.
		// SE SUPONE YA ORDENADO DECRECIENTEMENTE POR BENEFICIO 
		beneficio[0]=12;beneficio[1]=10;beneficio[2]=10;

		int[]peso=new int[n];
		peso[0]=5;peso[1]=4;peso[2]=3;

		System.out.println("CASO1-CAPACIDAD DE LA MOCHILA= "+m+" kg.");

		imprime= true;
		int valorMochila=mochilaPD (m,beneficio,peso);

		System.out.println();
		System.out.println ("BENEFICIO DE LA CARGA= "+valorMochila+" Euros" );
		System.out.println ();
		System.out.println ("CARGA SIEMPRE OPTIMA");


		//CASO 2:EL MISMO QUE FALLÓ EN alg77777777.t4.MochilaNoOptima
		m=55;
		n=8;  
		String[]nombre=new String[n];
		nombre[0]="AZUCAR";nombre[1]="LENTEJAS";nombre[2]="ARROZ";
		nombre[3]="PATATAS";nombre[4]="GARBANZOS";nombre[5]="MACARRONES";
		nombre[6]="TOMATES";nombre[7]="PERAS";

		beneficio=new int[n];
		// SE SUPONE YA ORDENADO DECRECIENTEMENTE POR BENEFICIO 
		beneficio[0]=90;beneficio[1]=85;beneficio[2]=82;beneficio[3]=80;
		beneficio[4]=70;beneficio[5]=68;beneficio[6]=65;beneficio[7]=60;

		peso=new int[n];
		peso[0]=45;peso[1]=30;peso[2]=25;peso[3]=60;
		peso[4]=50;peso[5]=40;peso[6]=80;peso[7]=600;

		System.out.println();
		System.out.println("CASO2-CAPACIDAD DE LA MOCHILA= "+m+" kg.");

		imprime= true;
		valorMochila=mochilaPD (m,beneficio,peso);

		System.out.println();
		System.out.println ("BENEFICIO DE LA CARGA= "+valorMochila+" Euros" );
		System.out.println ();
		System.out.println ("CARGA SIEMPRE OPTIMA");
	} 


	static int mochilaPD (int m,int[]beneficio,int[]peso) 
	/* Versión programación dinámica. 
La complejidad temporal es cuadrática 0(n*m) */
	{
		int n=peso.length;
		int[][]sol=new int[n][m+1];
		int cargaNo=0;
		int cargaSi=0;

		for (int i=0;i<=m;i++)  // solo puede meter primer elemento
			if (i<peso[0]) sol[0][i]=0;
			else sol[0][i]=peso[0]*beneficio[0]; 


		for (int i=1;i<n;i++)
			for (int j=0;j<=m;j++)
			{ 
				cargaNo=sol[i-1][j];
				if (j-peso[i]>=0)
					cargaSi= beneficio[i]*peso[i] + sol[i-1][j-peso[i]];
				else cargaSi=-1;
				sol[i][j]=mayor(cargaNo,cargaSi);
			}

		if (imprime)  //escribe matriz resultados
		{
			for (int i=0;i<n;i++)
			{
				for (int j=0;j<=m;j++)
					if (sol[i][j]<10) System.out.print(" *"+sol[i][j]);
					else System.out.print("*"+sol[i][j]);
				System.out.println();
			}
		}
		return sol[n-1][m];
	}

	static int mayor (int x,int y)
	{
		if (x>=y) return x;
		else return y;
	}

}