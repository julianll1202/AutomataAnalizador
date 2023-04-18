
package expresionesRegulares.app;

import expresionesRegulares.TDA.Archivo;
import expresionesRegulares.TDA.Lista;

public class ValidarPrograma {

	public static void main(String[] args) {
		/*
		 * Se crean nuevos objetos Archivo de los archivos de texto del programa y la
		 * lista de tokens.
		 */
		Archivo programa = new Archivo("E:\\Lenguajes y Autómatas I\\Escritura y lectura de archivos\\programa.txt");
		Archivo tokensArchivo = new Archivo("E:\\Lenguajes y Autómatas I\\Escritura y lectura de archivos\\tokens.txt");

		String[] textoPrograma = programa.leer(); // Se ingresa el contenido del programa en un arreglo.

		// Se crea una nueva lista y se llena con la lista de tokens del archivo.
		Lista tokens = new Lista();
		tokens.llenar(tokensArchivo);

		// Imprime los nombres de los tokens de cada lexema del programa.
		for (int i = 0; i < textoPrograma.length; i++) {
			System.out.println(tokens.validarLexema(textoPrograma[i]));
		}
	}
}
