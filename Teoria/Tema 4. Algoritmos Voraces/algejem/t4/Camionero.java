//PROBLEMA 9: CAMIONERO CON PRISA
//SOLUCIóN ÓPTIMA (minimiza número de repostajes)



/* Este programa resuelve el problema mediante el devorador:
"parar donde esté el depósito más vacío" */

public class Camionero
{
static int deposito;        // kms. con depósito lleno
static String []gasolinera; // nombre gasolinera
static int[]distancia;      // distancia Kms. desde anterior gasolinera


public static void main (String arg [] )
{
deposito=Integer.parseInt(arg[0]);
System.out.println ("Kms. SE PUEDE ANDAR CON DEPOSITO LLENO= "+deposito);

int n=10; // número gasolineras 

gasolinera=new String[n];
for (int i=0;i<n;i++) 
 gasolinera[i]="CIUDAD "+i;

distancia =new int[n];

// DISTANCIAS DESDE ANTERIOR GASOLINERA
distancia[0]=0;distancia[1]=100;distancia[2]=50;distancia[3]=70;
distancia[4]=100;distancia[5]=40;distancia[6]=80;distancia[7]=110;
distancia[8]=180;distancia[9]=30;

if (deposito<maximo(distancia))
  System.out.println ("VETE PENSANDO EN UN DEPOSITO MAYOR") ;
else
{
int parones=devorador(); 
System.out.println ("NUMERO DE PARADAS= "+parones);
System.out.println ("MINIMIZA SIEMPRE EL NUMERO DE PARADAS");
}
} 

static int devorador ()
// es de complejidad lineal con el número gasolineras 
{
int n=gasolinera.length;
int quedan=deposito;
int nparada=0;

for (int i=1;i<n;i++)
 {
  quedan=quedan-distancia[i];
  if (quedan<0)
   { 
    nparada++;
    System.out.println ("PARADA "+nparada+ " EN "+ gasolinera[i-1]);
    quedan=deposito-distancia[i];
   }
 }
return nparada;
}

static int maximo(int[]v)
{
int m=v[0];
for (int i=1;i<v.length;i++)
 if (v[i]>m) m=v[i];
return m;
}
}
