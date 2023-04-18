package expresionesRegulares.TDA;

public class Prueba {
	public static void main(String[] args) {
		AFD automata = new AFD("file.txt");
		System.out.println(automata.getEstados());
	}
}
