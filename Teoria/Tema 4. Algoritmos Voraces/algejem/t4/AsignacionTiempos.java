

import java.util.Random;

public class AsignacionTiempos
{
static int [][]c;
static int[]sol;


public static void main (String arg [] )
{
long t1,t2;

for (int n=100;n<=1000000;n*=2)
{
c = new int[n][n] ;
sol=new int[n];

Random r=new Random();
for (int i=0;i<n;i++)
  for (int j=0;j<n;j++)
   c[i][j]=r.nextInt(99)+1;

t1=System.currentTimeMillis();
for (int nVeces=1;nVeces<=1000;nVeces++)
 {  
  Asignacion.devorador1(c,sol);
  Asignacion.devorador2(c,sol);
 }
t2=System.currentTimeMillis();

System.out.println("n="+n+"*TDosDevoradores="+(t2-t1)+" MICROS");
}  // de for
}  // fin de main
}  // fin de la clase
