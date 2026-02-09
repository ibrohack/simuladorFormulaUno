package clases;

import java.io.Serializable;

public class Escuderia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Piloto[] piloto;
	Mecanico mecanico;
	String codigoEscuderia;
	String nombreEscuderia;
	
	public Escuderia(String codigo, String nombre) {
		super();
		this.piloto = new Piloto[2];
		this.codigoEscuderia = nombre.toUpperCase().substring(0,5) + "-" + codigo;
		this.nombreEscuderia = nombre;
	}
	
	public Escuderia(Piloto[] piloto, Mecanico mecanico, String codigo, String nombre) {
		super();
		this.piloto = piloto;
		this.mecanico = mecanico;
		this.codigoEscuderia = nombre.toUpperCase().substring(0,5) + "-" + codigo;
		this.nombreEscuderia = nombre;
	}

	public Piloto[] getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto[] piloto) {
		this.piloto = piloto;
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
		String mensaje="Escudería: " + nombreEscuderia
				+ "\nCódigo: " + codigoEscuderia
				+ "\nJefe mecánico: " + this.mecanico.getNombre();
		if(piloto != null) {
			mensaje.concat("\nPilotos: " + piloto);
		}else {
			mensaje.concat("\nPilotos: Sin asignar");
		}
		return mensaje;
	}
}