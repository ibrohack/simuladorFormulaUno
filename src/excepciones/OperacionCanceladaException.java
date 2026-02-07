package excepciones;

public class OperacionCanceladaException extends Exception {
    private static final long serialVersionUID = 1L;

    public OperacionCanceladaException(String mensaje) {
        super(mensaje);
    }
}
