//PROBLEMA 6: MINIMIZAR ESPACIO LIBRE EN UN DISCO CON FICHEROS
//CARGA NO ÓPTIMA (no minimiza el espacio libre)



/* Este programa resuelve el problema mediante el devorador:
"mientras quepan, cargar de mayor a menor" */

public class FicherosDiscoNoOptimo
{
static int capacidad; //capacidad disco en KBytes
static int[]tam;     // tamaño de los ficheros en KBytes

public static void main (String arg [] )
{
capacidad=Integer.parseInt(arg[0]);

int n=10; // número ficheros 

tam=new int[n];
// SE SUPONE YA ORDENADOS CRECIENTEMENTE POR TAMAÑO
// SI NO LO ESTUVIERAN, HABRÍA QUE ORDENARLOS
tam[0]=50;tam[1]=100;tam[2]=200;tam[3]=350;tam[4]=370;
tam[5]=450;tam[6]=500;tam[7]=700;tam[8]=800;tam[9]=5000;

devorador(capacidad,tam);
} 

static void devorador (int capacidad,int[]tam)
// es de complejidad lineal con el número ficheros 
{
System.out.println ("CAPACIDAD DEL DISCO EN KBytes= "+capacidad);
int n=tam.length;
int disponible=capacidad;
int cont=n-1;

while (cont>=0) 
 {
  if (tam[cont]<=disponible)
  {
   System.out.println ("CARGA EL FICHERO"+cont+" DE "+tam[cont]+" KBytes");
   disponible=disponible-tam[cont];
  } 
  cont--;
 }
System.out.println ("NUMERO DE KBytes LIBRES= "+disponible);
System.out.println ("NO OPTIMIZA EL NUMERO DE FICHEROS CARGADOS");
System.out.println ("p.e. PARA capacidad=1200 KBytes CARGO [800,370]");
System.out.println ("    SERIA MEJOR p.e [700,500] ,          DISCO LLENO "); 
System.out.println ("    SERIA MEJOR p.e [500,350,200,100,50] DISCO LLENO ");
}
}
