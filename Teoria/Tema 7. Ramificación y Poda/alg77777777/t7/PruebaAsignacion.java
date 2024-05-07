// PROBLEMA 1: ASIGNACION n TRABAJADORES-n TAREAS
// RESUELTO POR RAMIFICA Y PODA

package algestudiante.p7;

import algestudiante.util.RamificaYPoda;

/**
 * Clase principal para el problema de Asignación de agentes - tareas
 * Herada de RamificaYPoda 
 */
class PruebaAsignacion 
{	

	public static void main(String[] args) 
	{
		System.out.println("Problema de Agentes - tareas /// Ramificación y poda");
		
		// Creamos una instancia para resolver el problema
		RamificaYPoda problemaAsigna = new RamificaYPoda(false); 

		// Ejecutamos el método que va recorriendo el espacio de soluciones con ramifica y poda
		problemaAsigna.ramificaYPoda(new EstadoAsigna()); 
		
		// Solución final
		System.out.println("Solución óptima del problema $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(problemaAsigna.getMejorSolucion());

		if (RamificaYPoda.TRAZA)
			problemaAsigna.mostrarTrazaSolucion(); 
	}

}

