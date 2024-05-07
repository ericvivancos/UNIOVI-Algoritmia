//PROBLEMA 7: FONTANERO DILIGENTE
//CALIDAD ÓPTIMA (minimiza el tiempo medio de espera de clientes)



/* Este programa resuelve el problema mediante el devorador:
"SJF (Shortest job first), o sea, primero, el más corto " */

public class Fontanero extends Thread
{
static String[]cliente;//nombre de clientes
static int[]t;         //tiempo de reparación

public static void main (String arg [] )
{

int n=10; // número averías 

cliente=new String[n];
for (int i=0;i<n;i++) cliente[i]="CLIENTE "+i;

t=new int[n];
// SE SUPONE YA ORDENADOS CRECIENTEMENTE POR TIEMPO
// SI NO LO ESTUVIERAN, HABRÍA QUE ORDENARLOS
// LOS TIEMPOS ESTÁN EN MILISEGUNDOS
t[0]=500;t[1]=600;t[2]=1000;t[3]=1200;t[4]=1300;
t[5]=1500;t[6]=1600;t[7]=1800;t[8]=1900;t[9]=2000;

devorador(cliente,t);
} 

static void devorador (String[]cliente,int[]t) 
// es de complejidad lineal con el número clientes 
{
System.out.println ("VAYA DIA QUE ME ESPERA");
int n=t.length;
for (int i=0;i<n;i++)
 {
  System.out.println ("ESTOY ATENDIENDO A "+cliente[i]);
  try{Thread.sleep(t[i]);} catch(Exception e){}
 }
System.out.println();
System.out.println ("OPTIMIZA SIEMPRE EL TIEMPO MEDIO DE ESPERA");
System.out.println ("VAMOS A CALCULAR ESE TIEMPO");
double tmedio=0.0;
int tesperai=0;
for (int i=0;i<n;i++)
 {
  tesperai=tesperai+t[i];
  tmedio=tmedio+tesperai;
 }
System.out.println ("TIEMPO MEDIO DE ESPERA EN mseg.="+tmedio/n);
}
}