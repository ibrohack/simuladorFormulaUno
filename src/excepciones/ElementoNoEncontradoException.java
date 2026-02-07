package excepciones;

public class ElementoNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;

    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
