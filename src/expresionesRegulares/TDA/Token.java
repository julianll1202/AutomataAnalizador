
package expresionesRegulares.TDA;

public class Token {
	// Los tokens se componen de un nombre y un patrón.
	private AFD patron;
	private String nombre;

	// El usuario define el patrón y el nombre.
	public Token(String archivo, String nombre) {
		this.patron = new AFD(archivo);
		this.nombre = nombre;
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
		if (patron.pertenece(cadena)) {
			return this.nombre;
		} else {
			throw new Exception("La cadena no cumple con el patron");
		}
	}

	// Imprime la información de los datos de un token.
	public String toString() {
		return "Token [Nombre = " + nombre + "]";
	}
}
