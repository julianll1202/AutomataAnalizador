
package expresionesRegulares.TDA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lista {
	// Una lista aunque esté vacía se conforma de una raíz.
	private Nodo raiz;

	// Cuando se crea una nueva lista, la raíz debe ser nula.
	public Lista() {
		raiz = null;
	}

	// Retorna un valor booleano en función si la raíz apunta a un nodo o no.
	public boolean vacio() {
		return raiz == null;
	}

	// Inserta un nuevo nodo a la lista.
	public void insertar(Token T) {
		Nodo nuevo = new Nodo(T); // Se crea un nuevo nodo cuyo dato es un objeto Token.

		if (vacio()) {
			raiz = nuevo; // Si la lista está vacía, la raíz apunta al nuevo nodo.
		} else {
			Nodo aux = raiz; // Nodo auxiliar para recorrer la lista.
			while (aux.getSiguiente() != null) { // Bucle para llegar al final de la lista.
				aux = aux.getSiguiente();
			}
			aux.setSiguiente(nuevo); // Inserta el nodo nuevo al final de la lista.
		}
	}

	// Elimina todos los nodos de la lista.
	public void vaciar() throws StackUnderFlow {
		if (!vacio()) {
			raiz = null; // Si la lista no está vacía la raíz apunta a nulo.
		} else {
			// Si no hay elementos en la lista, retorna una excepción.
			throw new StackUnderFlow("No existen nodos dentro de la lista.");
		}
	}

	// Verifica que un lexema pertenezca a un patrón dentro de la lista.
	public String validarLexema(String lexema) {
		String token = "ERROR";

		if (!vacio()) {
			Nodo aux = raiz;
			/*
			 * El bucle termina cuando se llega al final de la lista ó si se encontró un
			 * patrón con el que corresponde el lexema.
			 */
			do {
				try {
					token = aux.validar(lexema); // Intenta validar un lexema con un patrón.
				} catch (Exception e) { // Si no corresponde con el patrón retorna una excepción.
				}
				aux = aux.getSiguiente(); // Avanza un elemento en la lista.
			} while (aux != null && token == "ERROR");
		}
		return token; // Puede retornar el nombre del token o un mensaje de error.
	}

	// Llena la lista de tokens de un archivo de texto.
	public void llenar(Archivo archivo) {
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo.getDireccion()));
			String linea; // Almacena las líneas del archivo.

			while ((linea = entrada.readLine()) != null) { // Lee hasta la última línea.
				// Cada elemento separado por un espacio se almacena en un arreglo.
				String[] token = linea.split("\\s+");
				// Se obtiene el nombre y patrón desde el arreglo.
				String nombre = token[0];
				String er = token[1];

				Token nuevo = new Token(er, nombre);
				insertar(nuevo);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Recorre toda la lista para imprimir cada elemento de la lista.
	public void imprimir() {
		Nodo aux = raiz;

		while (aux != null) {
			System.out.println(aux.getDato().toString());
			aux = aux.getSiguiente();
		}
	}
}
