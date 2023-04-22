
package expresionesRegulares.app;

import expresionesRegulares.TDA.Archivo;
import expresionesRegulares.TDA.Lista;

public class ValidarPrograma {

	public static void main(String[] args) {
		/*
		 * Se crean nuevos objetos Archivo de los archivos de texto del programa y la
		 * lista de tokens.
		 */
		Archivo codigo = new Archivo("programa.txt");
		Archivo tokensArchivo = new Archivo("tokens.txt");

		// String[] textoPrograma = programa.leer(); // Se ingresa el contenido del
		// programa en un arreglo.

		// Se crea una nueva lista y se llena con la lista de tokens del archivo.
		Lista tokens = new Lista();
		tokens.llenar(tokensArchivo);

		// Imprime los nombres de los tokens de cada lexema del programa.
//		for (int i = 0; i < textoPrograma.length; i++) {
//			System.out.println(tokens.validarLexema(textoPrograma[i]));
//		}
		String[] palabras;
		do {
			palabras = codigo.palabrasLinea();
//            Si llegamos al final del archivo se termina el ciclos
			if (palabras[0] == "No hay nada mas") {
				break;
			}
//            Para cada palabra dentro de una linea se llama al metodo validarLexema()
//            que se encarga de recorrer la lista de Tokens en busaueda de un token del cual la palabra
//            forme parte de sus lexemas
			for (int i = 0; i < palabras.length; i++) {
				String token = tokens.validarLexema(palabras[i]);
				System.out.println(token);

			}

		} while (palabras[0] != "No hay nada mas");
	}
}
