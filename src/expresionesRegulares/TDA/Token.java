
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
		boolean aceptada = false;
		int estado = 0;
		for (int c = 0; c < cadena.length(); c++) {
			int i;
			for (i = 0; i < patron.getAlfabeto().length; i++) {
				if (cadena.charAt(c) == patron.getAlfabeto()[i]) {
					break;
				}
			}
			estado = patron.getTablaTransicion()[estado][i];
		}
		for (int j = 0; j < patron.getEstadosFinales().length; j++) {
			if (estado == patron.getEstadosFinales()[j]) {
				aceptada = true;
			}
		}
		if (aceptada) {
			return this.nombre;
		} else {
			throw new Exception("Palabra rechazada");
		}
	}

	// Imprime la información de los datos de un token.
	public String toString() {
		return "Token [Nombre = " + nombre + "]";
	}
}
