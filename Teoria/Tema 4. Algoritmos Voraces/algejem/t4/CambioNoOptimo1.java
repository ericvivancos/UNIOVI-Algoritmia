//PROBLEMA 2:CAMBIO NO ÓPTIMO DE UNA CANTIDAD c DADA
//SE SUPONE QUE LAS MONEDAS DISPONIBLES SON LAS OFICIALES,
//PERO SE NOS ACABÓ ALGUNA (p.e. LAS DE 5 Y 10 CENTIMOS)



/* Este programa resuelve el problema mediante 
el devorador visto en clase: "dar siempre la 
moneda de mayor valor que sea de menor o igual a 
la cantidad que queda por devolver"  */

public class CambioNoOptimo1
{
static int c;      // cantidad a devolver
static int[]moneda;// valor monedas
static int[]cambio;// cambio dado

public static void main (String arg [] )
{
c=Integer.parseInt(arg[0]);
moneda=new int[6];
moneda[0]=1;moneda[1]=2;moneda[2]=20;
moneda[3]=50;moneda[4]=100;moneda[5]=200;

cambio=new int[6];
for (int i=0;i<=5;i++) cambio[i]=0;

int nmon=CambioOptimo.devoradorTrivial (c,moneda,cambio);

System.out.println ("CANTIDAD A DEVOLVER="+c);
System.out.println ("EL NUMERO DE MONEDAS ES "+nmon);
System.out.println ("SON LAS SIGUIENTES:");
for (int i=5;i>=0;i--)
 if (cambio[i]>0)
  {
  System.out.print (cambio[i]+" MONEDAS DE ");
  System.out.println(moneda[i]+" CENTIMOS");
  }
System.out.println();
System.out.println("CAMBIO NO SIEMPRE OPTIMO");
System.out.println("p.e. PARA c=60 TENDRIA QUE DAR 3 MONEDAS de 20 CENTIMOS");
} 
} // FIN 