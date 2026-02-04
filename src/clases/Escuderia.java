package clases;

import java.util.Arrays;

public class Escuderia {
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

	public String getCodigo() {
		return codigoEscuderia;
	}

	public void setCodigo(String codigo) {
		this.codigoEscuderia = codigo;
	}

	public String getNombre() {
		return nombreEscuderia;
	}

	public void setNombre(String nombre) {
		this.nombreEscuderia = nombre;
	}

	@Override
	public String toString() {
		return "Escuderia [piloto=" + Arrays.toString(piloto) + ", mecanico=" + mecanico + ", codigo=" + codigoEscuderia
				+ ", nombre=" + nombreEscuderia + "]";
	}
}