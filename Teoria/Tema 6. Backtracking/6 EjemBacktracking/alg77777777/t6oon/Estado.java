package alg77777777.t6oon;

public interface Estado
{
	/**
	 * Devuelve true si este estado es una solución al problema
	 */
	public abstract boolean esSolucion();
	
	/**
	 * Devuelve el siguiente hijo válido del estado
	 * @return devuelve una referencia al siguiente hijo válido, null si no hay
	 */
	public abstract Estado nextHijo();
	
	/**
	 * Devuelve true si quedan hijos *posibles* (puede que no quede ninguno válido)
	 * @return true- si quedan hijos posibles
	 */
	public abstract boolean hasNextHijos();
}