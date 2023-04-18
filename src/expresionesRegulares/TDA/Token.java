
package expresionesRegulares.TDA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
	// Los tokens se componen de un nombre y un patrón.
	private String er;
	private String nombre;

	// El usuario define el patrón y el nombre.
	public Token(String er, String nombre) {
		this.er = er;
		this.nombre = nombre;
	}

	public String getER() {
		return er;
	}

	public void setER(String er) {
		this.er = er;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	 * Se valida una cadena. Si coincide con el patrón del objeto Token, regresa su
	 * nombre, si no, regresa una excepción.
	 */
	public String validar(String cadena) throws Exception {
		Pattern pattern = Pattern.compile(getER());
		Matcher matcher = pattern.matcher(cadena);
		if (matcher.matches()) {
			return nombre;
		} else {
			throw new Exception("La cadena no corresponde a la cadena.");
		}
	}

	// Imprime la información de los datos de un token.
	public String toString() {
		return "Token [Nombre = " + nombre + ", Patrón = " + er + "]";
	}
}
