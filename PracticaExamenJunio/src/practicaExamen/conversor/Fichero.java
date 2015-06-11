package practicaExamen.conversor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Gestiona el uso de ficheros (los recupera y los guarda).
 * 
 * @author Estela Mu&ntilde;oz Cord&oacute;n.
 * 
 */
public class Fichero {
	/**
	 * Nombre del fichero
	 */
	private static final String fichero = "temperatura.obj";

	/**
	 * Guarda en un fichero la temperatura.
	 * 
	 * @param celsius Temperatura en Celsius a guardar.
	 * 
	 * @throws IOException Error por fallo o interrupci&oacute;n de entreda o salida de datos.
	 */
	public static void guardar(double celsius) throws IOException {
		try (DataOutputStream salida = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(fichero)))) {
			salida.writeDouble(celsius);
		}
	}

	/**
	 * Abre un fichero que contiene la temperatura.
	 * 
	 * @return Las temperaturas.
	 * 
	 * @throws IOException Error por fallo o interrupci&oacute;n de entreda o salida de datos.
	 */
	public static double abrir() throws IOException {
		try (DataInputStream entrada = new DataInputStream(
				new BufferedInputStream(new FileInputStream(fichero)))) {
			return entrada.readDouble();
		}
	}
}