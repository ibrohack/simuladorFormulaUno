package clases;

import java.time.LocalTime;
import java.util.TreeMap;

public class Piloto extends Persona {
	private Escuderia escuderia;
	private TreeMap<String, LocalTime> tiempos;

	public Piloto() {
		super();
		this.tiempos = new TreeMap<String, LocalTime>();
	}

	public Piloto(String nombre, int numero) {
		super(nombre, numero, "PIL");
		this.tiempos = new TreeMap<String, LocalTime>();
	}

	public TreeMap<String, LocalTime> getTiempos() {
		return tiempos;
	}

	public void setTiempos(TreeMap<String, LocalTime> tiempos) {
		this.tiempos = tiempos;
	}

	public Escuderia getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(Escuderia escuderia) {
		this.escuderia = escuderia;
	}

	@Override
	public String toString() {
		return "Piloto [tiempos=" + tiempos + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	@Override
	public void visualizar() {
		System.out.println("Piloto: " + this.nombre);
		System.out.println("Codigo: " + this.codigo);
		if (this.escuderia != null) {
			System.out.println("Escuderia: " + this.escuderia.getNombreEscuderia());
		} else {
			System.out.println("Escuderia: Sin asignar");
		}
		System.out.println("Tiempos: " + this.tiempos);
	}

}
