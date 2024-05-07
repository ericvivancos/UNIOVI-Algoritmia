//PROBLEMA 4: MOCHILA (0/1) DE CARGA m kg.
//CARGA ÓPTIMA (maximizar beneficio de lo que se carga)
//(0/1) SIGNIFICA QUE CADA ELEMENTO O SE CARGA
//O NO SE CARGA (NO FRACCIONABLE) 



/* Este programa resuelve el problema mediante 
el devorador: "mientras no se exceda el tope, cargar
de forma completa (0/1) los que mayor beneficio aportan" */

public class MochilaNoOptima
{
static int m;         // capacidad mochila
static String[]nombre;// nombre elemento
static int[]peso;     // peso elemento
static int[]beneficio;// beneficio/kg de cada elemento

public static void main (String arg [] )
{
m=Integer.parseInt(arg[0]);
int n=8; // número elementos 
nombre=new String[n];
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

System.out.println ("CAPACIDAD DE LA MOCHILA= "+m+" kg.");
System.out.println ("SE CARGA LO SIGUIENTE:");

int valorMochila=devoradorObvio2 (m,beneficio,peso);

System.out.println ("BENEFICIO DE LA CARGA= "+valorMochila+" Euros" );
System.out.println ();
System.out.println ("CARGA NO SIEMPRE OPTIMA");
System.out.println ("p.e. PARA m=55 CARGA UN BENEFICIO DE 4050 Euros");
System.out.println ("SI CARGASE LENTEJAS Y ARROZ SE OBTENDRIA 4600 Euros");
} 

static int devoradorObvio2 (int m,int[]beneficio,int[]peso)
/* este método es de complejidad lineal con el número de elementos que
   se carguen, que nunca es mayor que n */
{
int n=peso.length;
int indice=0;
int valor=0;
int cabe=m;
while (indice<n )
 { 
  if (cabe>=peso[indice] ) //cabe 
    {
     System.out.println (nombre[indice]+ "  SE GARGA "+peso[indice]+" kg.");
     valor=valor+peso[indice]*beneficio[indice];
     cabe=cabe-peso[indice];
     indice++;
    } 
  else indice++;  
 }
System.out.println ("SE HAN CARGADO "+(m-cabe)+" Kg.");
return valor; 
} 
}  // FIN 