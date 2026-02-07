package clases;

import java.io.Serializable;

public abstract class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String codigo;
	protected String nombre;

	public abstract void visualizar();

	public Persona(String nombre, int numero, String prefijo) {
		super();
		this.nombre = nombre;
		this.codigo = generarCodigo(prefijo, numero);
	}

	private String generarCodigo(String prefijo, int numero) {
		return prefijo + numero;
	}

	public Persona() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Persona [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
