//PROBLEMA 5: MAXIMIZAR NÚMERO DE FICHEROS EN UN DISCO
//CARGA ÓPTIMA (maximiza el número de ficheros cargados)



/* Este programa resuelve el problema mediante el devorador:
"mientras quepan, cargar de menor a mayor" */

public class FicherosDiscoOptimo
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
int cont=0;

while (cont<n && tam[cont]<=disponible)
 {
  System.out.println ("CARGA EL FICHERO="+tam[cont]+" KBytes");
  disponible=disponible-tam[cont];
  cont++;
 }
System.out.println ("NUMERO DE FICHEROS CARGADOS= "+cont);
System.out.println ("OPTIMIZA SIEMPRE EL NUMERO DE FICHEROS CARGADOS");
}

}
