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
		this.estados = Integer.parseInt(archivo.caracteresEnLinea()[0]);
		llenarEstadosAceptacion();
		llenarTabla();
	}

	private void llenarAlfabeto() {
//		Cambiar por metodo leer y hacer salto por comas
		String[] caracteres = archivo.caracteresEnLinea();
		this.alfabeto = new char[caracteres.length];
		for (int i = 0; i < caracteres.length; i++) {
			alfabeto[i] = caracteres[i].charAt(0);
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

	private void llenarTabla() {
		tablaTransicion = new int[estados][alfabeto.length];
		String[] estadosCaracteres;
		for (int r = 0; r < estados; r++) {
			estadosCaracteres = archivo.caracteresEnLinea();
			for (int c = 0; c < alfabeto.length; c++) {
				tablaTransicion[r][c] = Integer.parseInt(estadosCaracteres[c]);
			}

		}
	}
}
