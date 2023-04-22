
package expresionesRegulares.TDA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
	private String direccion;
	private BufferedReader bffrRd;

	// Un objeto Archivo se crea con la dirección del archivo de texto.
	public Archivo(String direccion) {
		this.direccion = direccion;
		try {
			this.bffrRd = new BufferedReader(new FileReader(direccion));
		} catch (IOException e) {
			System.out.println("Archivo no existe");
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;

	}

	// Lee el contenido de un archivo e ingresa el contenido en un arreglo.
	public String[] leer() {
		String[] programa = new String[9999];
		int numTokens = 0;
		String linea;

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(direccion));
			while ((linea = entrada.readLine()) != null) { // Lee hasta la última línea del archivo de texto.
				/*
				 * Elimina los espacios al inicio y final si lalínea contiene espacios al
				 * inicio.
				 */
				if (linea.startsWith(" ") | linea.startsWith("\t")) {
					linea = linea.trim();
				}
				if (linea.isEmpty()) { // Ignora las líneas vacías.
					continue;
				}
				if (linea.startsWith("#")) { // Ingora los comentarios.
					continue;
				}
				String[] partes = linea.split("\\s+"); // Separa cada elemento de la línea por espacios.
				for (int i = 0; i < partes.length; i++) { // Recorre el arreglo.
					/*
					 * Si detecta que un elemento es una cadena de más de una palabra se crea un
					 * objeto de la clase StringBuilder para formar un elemento con la cadena
					 * completa.
					 */
					if (partes[i].startsWith("\"") && partes[i].endsWith("\"") == false) {
						StringBuilder palabra = new StringBuilder();
						/*
						 * Ingresa cada elemento al objeto de StringBuilder hasta que encuentre el
						 * cierre de la cadena.
						 */
						while (partes[i].endsWith("\"") != true) {
							palabra.append(partes[i] + " ");
							i++;
						}
						palabra.append(partes[i]); // Ingresa el último elemento.
						programa[numTokens] = palabra.toString();
						numTokens++;
					} else { // Si no se encuentra un elemento cadena, se agrega al arreglo normalmente.
						programa[numTokens] = partes[i];
						numTokens++;
					}
				}
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Se crea un arreglo con el número exacto de tokens.
		String[] programaC = new String[numTokens];
		for (int i = 0; i < programaC.length; i++) { // Se ingresan los elementos en el nuevo arreglo.
			programaC[i] = programa[i];
		}
		return programaC;
	}

	public String[] caracteresEnLinea() {
		try {
			String[] caracteres;
			String linea = bffrRd.readLine();
			if (linea != null) {
				linea = linea.trim();
				if (linea.equals(",")) {
					caracteres = linea.split(" ");
				} else {
					caracteres = linea.split("[,]");
				}
				return caracteres;
			} else {
//              En caso de que hayamos llegado al final del archivo, se cierra el buffer
				bffrRd.close();
				String[] mensaje = { "No hay nada mas" };
				return mensaje;
			}
		} catch (IOException ioe) {
			String[] mensaje = { "Hubo un error de entrada y salida" };
			return mensaje;
		}
	}

	public String[] checarCadena(String[] palabras) {
//      Estas variables marcan el inicio y fin del string, respectivamente
		int iniciaCad = -1;
		int finCad = 0;
		String cadena = "";
		/*
		 * Para cada palabra dentro de la linea de texto se checa si empieza con
		 * comillas dobles pero no termina con ellas, o si es el caracter de comillas
		 * dobles
		 */
		for (int i = 0; i < palabras.length; i++) {
			if ((palabras[i].startsWith("\"") && !palabras[i].endsWith("\"")) || palabras[i].equals("\"")) {
				iniciaCad = i;
//              Si cumple alguna de las condiciones se utiliza la variable cadena para llenarla con el string
				cadena += palabras[i] + " ";
//              El proceso de agregar palabras a cadena se repite hasta que lleguemos a otra comilla o se acabe el string
				for (int j = iniciaCad + 1; j < palabras.length; j++) {
					cadena += palabras[j] + " ";
					finCad = j;
					if (palabras[j].endsWith("\"")) {
						break;
					}
				}
				break;

			}
		}
		if (iniciaCad < 0) {
			return palabras;
		}
		cadena = cadena.trim();
//      Se crea un nuevo arreglo en el que se agregaran los elementos del arreglo anterior, excepto
//      los que forman parte de la cadena
		String[] nuevoPal = new String[palabras.length - (finCad - iniciaCad)];
		for (int p = 0; p < nuevoPal.length; p++) {
			if (p == iniciaCad) {
				nuevoPal[p] = cadena;
			} else {
				if (p < iniciaCad) {
					nuevoPal[p] = palabras[p];
				} else {
					finCad++;
					nuevoPal[p] = palabras[finCad];

				}
			}

		}
		return nuevoPal;
	}

	public String[] checarLinea(String linea) {
		String[] pal = linea.split(" ");
		int cont = 0;
//      Llama al metodo checarCadena() hasta que ya no haya mas strings incompletos
		do {
			cont = 0;
			pal = checarCadena(pal);
			for (int i = 0; i < pal.length; i++) {
				if ((pal[i].startsWith("\"") && !pal[i].endsWith("\"")) || pal[i].equals("\"")) {
					cont++;
				}
			}
		} while (cont > 0);
		return pal;
	}

//    Lee una linea de texto de un archivo
	public String[] palabrasLinea() {
		try {
			String[] lineaPalabras;
			String linea = bffrRd.readLine();
			if (linea != null) {
				linea = linea.trim();
//              Si la linea es un comentario o una linea vacia, se las salta
				if (linea.startsWith("#") || linea.equals("") || linea.equals(" ")) {
					return palabrasLinea();
				}
				return checarLinea(linea);

			} else {
//              En caso de que hayamos llegado al final del archivo, se cierra el buffer
				bffrRd.close();
				String[] mensaje = { "No hay nada mas" };
				return mensaje;
			}

		} catch (IOException ioe) {
			String[] mensaje = { "Hubo un error de entrada y salida" };
			return mensaje;
		}
	}
}
