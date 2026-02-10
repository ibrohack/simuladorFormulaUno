package clases;

import java.io.Serializable;

public class Escuderia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Piloto[] pilotos;
	private Mecanico mecanico;
	private String codigoEscuderia;
	private String nombreEscuderia;

	public Escuderia(String codigo, String nombre) {
		super();
		this.pilotos = new Piloto[2];
		this.codigoEscuderia = nombre.toUpperCase().substring(0, 5) + "-" + codigo;
		this.nombreEscuderia = nombre;
	}

	public Escuderia(Piloto[] pilotos, Mecanico mecanico, String codigo, String nombre) {
		super();
		this.pilotos = pilotos;
		this.mecanico = mecanico;
		this.codigoEscuderia = nombre.toUpperCase().substring(0, 5) + "-" + codigo;
		this.nombreEscuderia = nombre;
	}

	public Piloto[] getPilotos() {
		return pilotos;
	}

	public void setPilotos(Piloto[] pilotos) {
		this.pilotos = pilotos;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public String getCodigoEscuderia() {
		return codigoEscuderia;
	}

	public void setCodigoEscuderia(String codigo) {
		this.codigoEscuderia = codigo;
	}

	public String getNombreEscuderia() {
		return nombreEscuderia;
	}

	public void setNombreEscuderia(String nombre) {
		this.nombreEscuderia = nombre;
	}

	@Override
	public String toString() {
		String mensaje = "Escudería: " + nombreEscuderia
				+ "\nCódigo: " + codigoEscuderia;
		if (mecanico != null) {
			mensaje = mensaje.concat("\nJefe mecánico: " + this.mecanico.getNombre());
		} else {
			mensaje = mensaje.concat("\nMecanico: Sin asignar");
		}
		for (int i = 0; i < pilotos.length; i++) {
			if (pilotos[i] != null) {
				mensaje = mensaje.concat("\nPiloto: " + pilotos[i].getNombre());
			}
		}

		return mensaje;
	}
}