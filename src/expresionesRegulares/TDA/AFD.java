package expresionesRegulares.TDA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AFD {
	private Archivo archivo;
	private char[] alfabeto;
	private int estados;
	private int[] estadosFinales;
	private int[][] tablaTransicion;

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public char[] getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(char[] alfabeto) {
		this.alfabeto = alfabeto;
	}

	public int getEstados() {
		return estados;
	}

	public void setEstados(int estados) {
		this.estados = estados;
	}

	public int[] getEstadosFinales() {
		return estadosFinales;
	}

	public void setEstadosFinales(int[] estadosFinales) {
		this.estadosFinales = estadosFinales;
	}

	public int[][] getTablaTransicion() {
		return tablaTransicion;
	}

	public void setTablaTransicion(int[][] tablaTransicion) {
		this.tablaTransicion = tablaTransicion;
	}

	public AFD(String arch) {
		this.archivo = new Archivo(arch);
		llenarAlfabeto();
		llenarEstados();
		llenarEstadosAceptacion();
		try {
			llenarTabla();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void llenarEstados() {
		this.estados = Integer.parseInt(archivo.caracteresEnLinea()[0]);
		try {
			validarEstados();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void llenarAlfabeto() {
//		Cambiar por metodo leer y hacer salto por comas
		String[] caracteres = archivo.caracteresEnLinea();
		this.alfabeto = new char[caracteres.length];
		for (int i = 0; i < caracteres.length; i++) {
			alfabeto[i] = caracteres[i].charAt(0);
		}
		try {
			validarAlfabeto();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void validarAlfabeto() throws Exception {
		Pattern patron = Pattern.compile("^[a-z|A-Z|0-9]|.$");
		String s;
		for (int i = 0; i < alfabeto.length; i++) {
			s = Character.toString(alfabeto[i]);
			Matcher m = patron.matcher(s);
			if (!m.matches()) {
				throw new Exception("Automata mal definido, el alfabeto solo debe contener caracteres");
			}
			for (int j = 0; j < alfabeto.length; j++) {
				if (i != j) {
					if (alfabeto[i] == alfabeto[j]) {
						throw new Exception("Automata mal definicio, el alfabeto contiene caracteres repetidos");
					}
				} else {
					continue;
				}

			}
		}
	}

	public void validarEstados() throws Exception {
		Pattern patron = Pattern.compile("^[0-9]+$");
		String s = Integer.toString(estados);
		Matcher m = patron.matcher(s);
		if (!m.matches()) {
			throw new Exception("Automata mal definido, Q debe ser un numero entero");
		}
		if (estados < 2) {
			throw new Exception("Automata mal definido, Q debe ser mayor que 2 ");
		}
	}

	private void llenarEstadosAceptacion() {
		String[] estados = archivo.caracteresEnLinea();
		this.estadosFinales = new int[estados.length];
		for (int i = 0; i < estados.length; i++) {
			estadosFinales[i] = Integer.parseInt(estados[i]);
		}
		try {
			validarEstadosAceptacion();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void validarEstadosAceptacion() throws Exception {
		Pattern patron = Pattern.compile("^[0-9]+$");
		String s;
		for (int i = 0; i < estadosFinales.length; i++) {
			s = Integer.toString(estadosFinales[i]);
			Matcher m = patron.matcher(s);
			if (!m.matches()) {
				throw new Exception("Automata mal definido, los estados finales deben ser representados con enteros");
			}
			if (estadosFinales[i] >= estados) {
				throw new Exception("Automata mal definido, solo existen " + estados + " estados");
			}
			if (estadosFinales[i] < 0) {
				throw new Exception("Automata mal definido, los estados no pueden ser negativos");
			}
		}
	}

	private void llenarTabla() throws Exception {
		tablaTransicion = new int[estados][alfabeto.length];
		String[] estadosCaracteres;
		Pattern patron = Pattern.compile("^[0-9]+$");
		Matcher m;
		int r = 0;
		do {
			estadosCaracteres = archivo.caracteresEnLinea();
			if (estadosCaracteres.length > alfabeto.length) {
				throw new Exception(
						"Automata mal definido, la tabla de transicion contiene mas columnas que caracteres en el alfabeto");
			}
			for (int c = 0; c < alfabeto.length; c++) {
				if (estadosCaracteres[0] == "No hay nada mas") {
					break;
				}
				m = patron.matcher(estadosCaracteres[c]);
				if (m.matches()) {
					tablaTransicion[r][c] = Integer.parseInt(estadosCaracteres[c]);
				} else {
					throw new Exception("Automata mal definido, los estados deben ser representados por enteros");
				}
			}
			if (r > estados) {
				throw new Exception(
						"Automata mal definido, la tabla de transicion contiene mas estados que lo especificado por la quintupla");
			}
			r++;
		} while (estadosCaracteres[0] != "No hay nada mas");
	}

	public boolean pertenece(String cadena) throws Exception {
		boolean aceptada = false;
		int estado = 0;
		for (int c = 0; c < cadena.length(); c++) {
			int i;
			for (i = 0; i < alfabeto.length; i++) {
				if (cadena.charAt(c) == alfabeto[i]) {
					break;
				}
				if (i == (alfabeto.length - 1)) {
					throw new Exception("Error, el simbolo " + cadena.charAt(c) + " no pertenece al alfabeto");
				}
			}
			estado = tablaTransicion[estado][i];
		}
		for (int j = 0; j < estadosFinales.length; j++) {
			if (estado == estadosFinales[j]) {
				aceptada = true;
			}
		}
		return aceptada;
	}
}
