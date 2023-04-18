package expresionesRegulares.TDA;

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

	private void llenarEstadosAceptacion() {
		String[] estados = archivo.caracteresEnLinea();
		this.estadosFinales = new int[estados.length];
		for (int i = 0; i < estados.length; i++) {
			estadosFinales[i] = Integer.parseInt(estados[i]);
		}
	}

	private void llenarTabla() {

		String[] estadosCaracteres;
		for (int r = 0; r < estados; r++) {
			estadosCaracteres = archivo.caracteresEnLinea();
			for (int c = 0; c < alfabeto.length; c++) {
				tablaTransicion[r][c] = Integer.parseInt(estadosCaracteres[c]);
			}

		}
	}
}
