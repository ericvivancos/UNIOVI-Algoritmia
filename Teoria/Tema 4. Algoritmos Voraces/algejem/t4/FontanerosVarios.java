// PROBLEMA 8: VARIOS FONTANEROS DILIGENTES
// CALIDAD ÓPTIMA (minimiza el tiempo medio
// de espera de clientes)



/* Este programa resuelve el problema mediante el devorador:
"SJF (Shortest job first), o sea, primero, lo más corto " */

public class FontanerosVarios 
{

static int n;            // número averías 
static int f;            // número fontaneros
static String[]cliente;  //nombre de clientes
static String[]fontanero;//nombre fontaneros 
static int[]t;           //tiempo de reparación
static int[]asignacion; 
 
public static void main (String arg [] )
{
f=3;
fontanero=new String[f];
fontanero[0]="GOTERA";
fontanero[1]="OTILIO";
fontanero[2]="CHAPUZAS"; 

n=10;
cliente=new String[n];
for (int i=0;i<n;i++) cliente[i]="CLIENTE "+i;

t=new int[n];
// SE SUPONE YA ORDENADOS CRECIENTEMENTE POR TIEMPO
// SI NO LO ESTUVIERAN, HABRÍA QUE ORDENARLOS
t[0]=500;t[1]=600;t[2]=1000;t[3]=1200;t[4]=1300;
t[5]=1500;t[6]=1600;t[7]=1800;t[8]=1900;t[9]=2000;

asignacion=new int[n];

devorador();

System.out.println ("EDICTO DE OBLIGADO CUMPLIMIENTO:");
for (int i=0;i<n;i++)
 {
  System.out.print (cliente[i]+" SERA ATENDIDO POR ");
  System.out.println(fontanero[asignacion[i]]);
 }
System.out.println();
System.out.println ("OPTIMIZA SIEMPRE EL TIEMPO MEDIO DE ESPERA");
} 

static void devorador () 
// es de complejidad lineal con el número clientes 
{
int font=0;
for (int i=0;i<n;i++)
 {
  asignacion[i]=font;
  font=(font+1)%f;
 }
}
}