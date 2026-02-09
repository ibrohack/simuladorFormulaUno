package clases;

import java.util.TreeMap;

public class Mecanico extends Persona {
	private double factorRepostaje;
	private double factorCambioNeumaticos;
	private TreeMap<String, Integer> puntos;

	public Mecanico(String nombre, int numero, String escuderia, double factorRepostaje,
			double factorCambioNeumaticos, TreeMap<String, Integer> puntos) {
		super(nombre, numero, "MEC");
		this.factorRepostaje = factorRepostaje;
		this.factorCambioNeumaticos = factorCambioNeumaticos;
		this.puntos = puntos;
	}

	public Mecanico(String nombre, int numero) {
		super(nombre, numero, "MEC");
		this.factorRepostaje = 1.0;
		this.factorCambioNeumaticos = 1.0;
		this.puntos = new TreeMap<>();
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
		return "Mecanico [factorRepostaje=" + factorRepostaje + ", factorCambioNeumaticos="
				+ factorCambioNeumaticos + ", puntos=" + puntos + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	@Override
	public void visualizar() {
		System.out.println("\nMecanico: " + this.nombre);
		System.out.println("Codigo: " + this.codigo);
		System.out.println("Factor Repostaje: " + this.factorRepostaje);
		System.out.println("Factor Cambio Neumaticos: " + this.factorCambioNeumaticos);
		System.out.println("Puntos de habilidad: " + this.puntos);
	}
}