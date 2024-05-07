package alg77777777.t5;
/*
 * Created on 30-nov-2006
 *
 */

/**
 * Implementación del problema del Play-off de baloncesto con programación dinámica
 * Calcula las probabilidades que tiene un equipo de ganar el play-off, 
 * teniendo en cuenta los partidos disputados.
 * 
 * @author Juan Ramón Pérez Pérez - Teoría de la Programación
 */
public class PlayOff 
{
	private double probabilidadGanarA;
	
	public PlayOff(double probabilidadGanarA)
	{
		this.probabilidadGanarA= probabilidadGanarA;
	}
	
	public double probabilidadDv(int i,int j)
	{
		if (i==0) 
			return 1.0;
		else 
			if (j==0) 
				return 0.0;
			else 
				return probabilidadGanarA * probabilidadDv(i-1,j) 
					+ (1-probabilidadGanarA) * probabilidadDv(i,j-1);				
	}
	
	private void mostrarTabla(double[][] a)
	{
		for (int i= 0; i<a.length; i++)
		{
			for (int j= 0; j<a[i].length; j++)
				System.out.print(a[i][j]+", ");
			System.out.println();
		}
		System.out.println();		
	}
	public double probabilidadPd(int i,int j)
	{
		double[][] a= new double[i+1][j+1];	// necesitamos índices desde 0 hasta i 
		
		for (int v= 1; v<j+1; v++)	// Rellenamos casos trivales
		{
			a[0][v]= 1.0;
		}
		for (int u= 1; u<i+1; u++)
		{
			a[u][0]= 0.0;
		}
				
		for (int u= 1; u<i+1; u++)	// Rellenamos el resto de las celdas
			for (int v= 1; v<j+1; v++)
			{
				a[u][v]= probabilidadGanarA * a[u-1][v]	
							+ (1-probabilidadGanarA) * a[u][v-1];
			}
		mostrarTabla(a);
		return a[i][j];
	}

	public static void main(String[] args) 
	{
		double probabilidadGanarA= 0.7;
		PlayOff po;
		double ganarPlayoff;
		int faltanAparaGanar, faltanBparaGanar;
		
		System.out.println("Equipo A:  Real Madrid");
		System.out.println("Probabilidad de ganar un partido: "+probabilidadGanarA);
		System.out.println("Equipo B:  Valencia Basket");
		System.out.println("Probabilidad de ganar un partido: "+(1-probabilidadGanarA));
		po= new PlayOff(probabilidadGanarA);
		System.out.println("Play off al mejor de 3");
		faltanAparaGanar= 3;
		faltanBparaGanar= 1;
		//ganarPlayoff= po.probabilidadDv(faltanAparaGanar,faltanBparaGanar);
		ganarPlayoff= po.probabilidadPd(faltanAparaGanar,faltanBparaGanar);
		System.out.println("Probabilidades de que el Real Madrid gane: "+ganarPlayoff);
		
	}
}
