
package expresionesRegulares.TDA;

public class Nodo {
	// Un nodo se compone de un dato y un acceso al nodo siguiente.
	private Token dato;
	private Nodo siguiente;

	// Se puede crear un nodo solamente con un objeto Token.
	public Nodo(Token dato) {
		this.dato = dato;
		this.siguiente = null;
	}

	/*
	 * También se puede crear un nodo con un nuevo token ingresando el patrón y el
	 * nombre.
	 */
	public Nodo(String archivo, String nombre) {
		dato = new Token(archivo, nombre);
		this.siguiente = null;
	}

	public Token getDato() {
		return dato;
	}

	public void setDato(Token dato) {
		this.dato = dato;
	}

	public Nodo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}

	/*
	 * Retorna el método validar de la clase Token. Facilita la validación desde un
	 * nodo.
	 */
	public String validar(String cadena) throws Exception {
		return getDato().validar(cadena);
	}
}
