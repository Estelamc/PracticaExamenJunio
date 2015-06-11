package practicaExamen.conversor;

/**
 * Calcula la conversi&oacute;n de temperaturas 
 * (de Celsius a Fahrenheit y de Fahrenheit a Celsius).
 * 
 * @author Estela Mu&ntilde;oz Cord&oacute;n.
 *
 */

public class Temperatura {
	
	/**
	 * Convierte a grados Fahrenheit.
	 * 
	 * @param celsius Grados Celsius.
	 * @return Los grados Fahrenheit que corresponden a esa temperatura.
	 */
	public static double aFahrenheit(double celsius) {
		return (celsius*1.8)+32;
	}
	
	/**
	 * Convierte a grados Celsius.
	 * 
	 * @param fahrenheit Grados Fahrenheit.
	 * @return Los grados Celsius que corresponden a esa temperatura.
	 */
	public static double aCelsius(double fahrenheit) {
		return (fahrenheit-32)/1.8;
	}
	
}
