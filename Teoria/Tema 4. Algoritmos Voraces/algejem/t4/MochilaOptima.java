//PROBLEMA 3: MOCHILA FRACCIONABLE DE CARGA m Tm.
//CARGA ÓPTIMA (maximizar beneficio de lo que se carga)
//FRACCIONABLE SIGNIFICA QUE PODEMOS CARGAR LA CANTIDAD
//QUE QUERAMOS DE CADA ELEMENTO 



/* Este programa resuelve el problema mediante 
el devorador: "mientras no se exceda el tope, cargar
lo que mayor beneficio aporta" */

public class MochilaOptima
{
	static int m;         // capacidad mochila
	static String[]nombre;// nombre elemento
	static int[]peso;     // peso elemento
	static int[]beneficio;// beneficio/Tm de cada elemento

	public static void main (String arg [] )
	{
		m= Integer.parseInt(arg[0]);
		
		int n=8; // número elementos 
		nombre=new String[n];
		nombre[0]="Hierro";nombre[1]="Carbon";nombre[2]="Cobre";
		nombre[3]="Zinc";nombre[4]="Plomo";nombre[5]="Niquel";
		nombre[6]="Platino";nombre[7]="Aluminio";

		beneficio=new int[n];
		// SE SUPONE YA ORDENADO DECRECIENTEMENTE POR BENEFICIO 
		beneficio[0]=90;beneficio[1]=45;beneficio[2]=82;beneficio[3]=80;
		beneficio[4]=70;beneficio[5]=68;beneficio[6]=165;beneficio[7]=60;

		peso=new int[n];
		peso[0]=450;peso[1]=3000;peso[2]=250;peso[3]=600;
		peso[4]=500;peso[5]=400;peso[6]=80;peso[7]=6000;

		System.out.println ("CAPACIDAD DE LA MOCHILA= "+m+" Toneladas");
		System.out.println ("SE CARGA LO SIGUIENTE:");

		int valorMochila= resolverMochila();

		System.out.println ();
		System.out.println ("BENEFICIO DE LA CARGA= "+valorMochila+" Euros");

		System.out.println ();
		System.out.println ("CARGA SIEMPRE OPTIMA"); 
	} 

	static void mostrarElementos()
	{
		System.out.print("Mineral: ");
		for (int i= 0; i<nombre.length; i++)
		{
			System.out.print(nombre);
		}

	}
	
	static int resolverMochila ()
	/* este método es de complejidad lineal con el número de 
elementos que se carguen, que nunca es mayor que n */
	{
		int n=peso.length;
		int valor=0;
		int cabe=m;
		int indice=0;
		while (m>0 && indice<n )
		{ 
			if (cabe>=peso[indice] ) //cabe entero
			{
				System.out.println (nombre[indice]+ "  SE GARGA "+peso[indice]+" Tm.");
				valor=valor+peso[indice]*beneficio[indice];
				cabe=cabe-peso[indice];
				indice++;
			}   
			else  // no cabe entero
			{
				System.out.println (nombre[indice]+ "  SE GARGA "+cabe+" Tm.");
				valor=valor+cabe*beneficio[indice];
				cabe=0;
				indice++;
			}
		}
		System.out.println("SE HAN CARGADO UN TOTAL DE "+(m-cabe)+" Tm.");
		return valor; 
	} 
}  // FIN 