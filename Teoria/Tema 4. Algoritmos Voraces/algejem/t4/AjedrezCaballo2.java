// PROBLEMA 11 : SALTO DEL CABALLO DE AJEDREZ
// INTENTA CALCULAR POR DEVORADOR LA FORMA DE
// RECORRER EL TABLERO, PERO FRACASA. ANTE ESO,
// SE PUEDE DECIR QUE NO ES NI DEVORADOR, AL NO  
// ENCONTRAR SOLUCIÓN



public class AjedrezCaballo2

{
static int n;     // tamaño tablero 
static int[][]tab;// tablero de ajedrez 
static int[]a;    // desplazamientos x del caballo
static int[]b;    // desplazamientos y del caballo
static boolean seEncontro;// si se encuentra solución 

public static void main (String arg[])
{
n= Integer.parseInt(arg[0]);
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

seEncontro=devorador2 (2,salidax,saliday);

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
System.out.println("NO SIEMPRE ENCUENTRA SOLUCION");
System.out.println("p.e. PARA n=5, SE SALE de (0,0) Y LA ENCUENTRA");
System.out.println("p.e. PARA n=5, SE SALE de (2,0) Y NO LA ENCUENTRA");
System.out.println("SE PUEDE DECIR QUE NO ES DEVORADOR");
}  //de main


static boolean devorador2 (int salto,int x,int y)
/* Mira para cada uno de los 8 sitios a donde 
puede saltar el caballo, a cuantos sitios puede
asimismo saltar, para escoger al final el sitio 
desde el que se pueda saltar a menos sitios. 
La idea de que cuanto primero se visite mejor */
{
 boolean puedo=true;
 int[]nSaltos=new int[8];

 while (salto<=n*n && puedo)
 {
  for (int i=0;i<=7;i++) nSaltos[i]=-1;

  for (int k=0;k<=7;k++)
  { 
   int u=x+a[k];
   int v=y+b[k];
   if(u>=0 && u<=n-1 && v>=0 &&
      v<=n-1 && tab[u][v]==0)
     nSaltos[k]=calcularSaltos(u,v); 
  }
  int kBueno=indiceMinimo(nSaltos);

  if (kBueno==-1)
    puedo=false;
  else
   {
    x=x+a[kBueno];
    y=y+b[kBueno];
    tab[x][y]=salto;
    salto++;
   }
 } // de while
 return puedo;
} 


static int calcularSaltos(int u,int v)
/* calcula el número da saltos posibles desde (u,v)*/
{
 int cont=0;
 for (int s=0;s<=7;s++)
  {int u1=u+a[s];
   int v1=v+b[s];
   if(u1>=0 && u1<=n-1 && v1>=0 &&
      v1<=n-1 && tab[u1][v1]==0)
        cont++;
  }
 return cont;
}


static int indiceMinimo(int[]v)
/* calcula donde está el mínimo >=0
   si son todos -1, retorna -1 */
{
 int indice=-1;
 int minimo=1000; 
 for (int s=0;s<=7;s++)
  if (v[s]>=0 && v[s]<minimo)
    {
     indice=s;
     minimo=v[s];
    }
 return indice;
}

} // de clase