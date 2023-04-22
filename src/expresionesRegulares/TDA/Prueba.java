package expresionesRegulares.TDA;

public class Prueba {
	public static void main(String[] args) {
		AFD automata = new AFD("automatas/tknVarRfc.txt");
		System.out.println(automata.getAlfabeto()[0]);
		Lista lista = new Lista();
		Archivo tokens = new Archivo("tokens.txt");
		lista.llenar(tokens);
//		lista.imprimir();
		System.out.println(lista.validarLexema("\" me llamo Alexis\""));
		// Token inicio = new Token("automatas/inicio.txt", "inicio");
//		Nodo inicio = new Nodo("automatas/inicio.txt", "inicio");
//		try {
//			System.out.println(inicio.validar("inici"));
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
}
