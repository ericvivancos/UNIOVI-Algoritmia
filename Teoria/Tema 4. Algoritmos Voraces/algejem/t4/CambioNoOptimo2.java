//PROBLEMA 2:CAMBIO NO ÓPTIMO DE UNA CANTIDAD c DADA
// SE SUPONE QUE LAS MONEDAS DISPONIBLES  NO CUMPLEN
// QUE SON VALORES MÚLTIPLOS, COMO SÍ LO CUMPLE LAS
// DEL SISTEMA OFICIAL 



/* Este programa resuelve el problema mediante 
el devorador visto en clase: "dar siempre la 
moneda de mayor valor que sea de menor o igual a 
la cantidad que queda por devolver"  */

public class CambioNoOptimo2
{
static int c;      // cantidad a devolver
static int[]moneda;// valor monedas
static int[]cambio;// cambio dado

public static void main (String arg [] )
{

c=Integer.parseInt(arg[0]);
moneda=new int[8];
moneda[0]=1;moneda[1]=4;moneda[2]=5;moneda[3]=12;
moneda[4]=20;moneda[5]=50;moneda[6]=100;moneda[7]=200;

cambio=new int[8];
for (int i=0;i<=7;i++) cambio[i]=0;

int nmon=CambioOptimo.devoradorTrivial (c,moneda,cambio);

System.out.println ("CANTIDAD A DEVOLVER="+c);
System.out.println ("EL NUMERO DE MONEDAS ES "+nmon);
System.out.println ("SON LAS SIGUIENTES:");
for (int i=7;i>=0;i--)
 if (cambio[i]>0)
  {
   System.out.print(cambio[i]+" MONEDAS DE ");
   System.out.println(moneda[i]+" CENTIMOS");
  }
System.out.println();
System.out.println("CAMBIO NO SIEMPRE OPTIMO");
System.out.println("p.e. PARA c=15 TENDRIA QUE DAR 3 MONEDAS de 5 CENTIMOS");
} 
}  // FIN 