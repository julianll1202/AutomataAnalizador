
package expresionesRegulares.TDA;

// StackUnderFlow es una excepción creada manualmente con el propósito de identificar errores de listas vacías.
public class StackUnderFlow extends Exception {
	private static final long serialVersionUID = 1L;

	public StackUnderFlow(String e) {
		super(e);
	}
}
