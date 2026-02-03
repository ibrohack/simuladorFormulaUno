package clases;

import java.util.TreeMap;

public class Mecanico extends Persona{
	private String escuderia;
	private double factorRepostaje;
	private double factorCambioNeumaticos;
	private TreeMap<String, Integer>puntos;
	
	public Mecanico(String codigo, String nombre, String escuderia, double factorRepostaje,
			double factorCambioNeumaticos, TreeMap<String, Integer> puntos) {
		super(codigo, nombre);
		this.escuderia = escuderia;
		this.factorRepostaje = factorRepostaje;
		this.factorCambioNeumaticos = factorCambioNeumaticos;
		this.puntos = puntos;
	}

	public String getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}

	public double getFactorRepostaje() {
		return factorRepostaje;
	}

	public void setFactorRepostaje(double factorRepostaje) {
		this.factorRepostaje = factorRepostaje;
	}

	public double getFactorCambioNeumaticos() {
		return factorCambioNeumaticos;
	}

	public void setFactorCambioNeumaticos(double factorCambioNeumaticos) {
		this.factorCambioNeumaticos = factorCambioNeumaticos;
	}

	public TreeMap<String, Integer> getPuntos() {
		return puntos;
	}

	public void setPuntos(TreeMap<String, Integer> puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Mecanico [escuderia=" + escuderia + ", factorRepostaje=" + factorRepostaje + ", factorCambioNeumaticos="
				+ factorCambioNeumaticos + ", puntos=" + puntos + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	
	
}
