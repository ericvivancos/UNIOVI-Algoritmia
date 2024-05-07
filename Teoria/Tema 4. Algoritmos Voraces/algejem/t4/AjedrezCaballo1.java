// PROBLEMA 11 : SALTO DEL CABALLO DE AJEDREZ
// INTENTA CALCULAR POR DEVORADOR LA FORMA DE
// RECORRER EL TABLERO, PERO FRACASA. ANTE ESO,
// SE PUEDE DECIR QUE NO ES NI DEVORADOR, AL NO  
// ENCONTRAR SOLUCIÓN



public class AjedrezCaballo1

{
static int n;     // tamaño tablero 
static int[][]tab;// tablero de ajedrez 
static int[]a;    // desplazamientos x del caballo
static int[]b;    // desplazamientos y del caballo
static boolean seEncontro; // si se encuentra solución 

public static void main (String arg[])
{
n=Integer.parseInt(arg[0]);
tab=new int[n][n];
a= new int [8];
b= new int [8];
a[0]=1;b[0]=2;a[1]=2;b[1]=1;a[2]=2;b[2]=-1;
a[3]=1;b[3]=-2;a[4]=-1;b[4]=-2;a[5]=-2;b[5]=-1;
a[6]=-2;b[6]=1;a[7]=-1;b[7]=2;

int salidax=Integer.parseInt(arg[1]);
int saliday=Integer.parseInt(arg[2]);

for (int i=0;i<n;i++)
  for (int j=0;j<n;j++)
    tab[i][j]= 0;
tab[salidax][saliday]=1;  // posicion inicial del caballo 
seEncontro=devorador1 (2,salidax,saliday);
System.out.println (seEncontro);
for (int i=0;i<n;i++)
 { 
  for (int j=0;j<n;j++)
   {
    if (tab[i][j]<10)  System.out.print(" "+tab[i][j]+"*");
    else System.out.print(tab[i][j]+"*");
   }
  System.out.println();
 }
System.out.println();
System.out.println("NUNCA ENCUENTRA SOLUCION");
System.out.println("SALIENDO DESDE DONDE SEA");
System.out.println("PUEDE DECIRSE QUE NO ES DEVORADOR");
}  //de main


static boolean devorador1 (int salto,int x,int y)
/*Salta al primer sitio que encuentra
  que puede saltar */
{
 boolean puedo=true;
 while (salto<=n*n && puedo)
 {
  boolean salir=false;
  int k=0;
  while (k<=7&&!salir)
  { 
   int u=x+a[k];
   int v=y+b[k];
   if(u>=0 && u<=n-1 && v>=0 &&
      v<=n-1 && tab[u][v]==0)
    {    
     x=u;
     y=v;
     tab[x][y]=salto;
     salto++;
     salir=true; 
    }
   k++;
  }
  if (!salir) puedo=false;
 } 
 return puedo;
} 

} // de clase