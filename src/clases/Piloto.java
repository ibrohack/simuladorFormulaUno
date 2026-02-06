package clases;

import java.time.LocalTime;
import java.util.TreeMap;

public class Piloto extends Persona {
	private TreeMap<String, LocalTime> tiempos;

	public Piloto() {
		super();
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

	@Override
	public String toString() {
		return "Piloto [tiempos=" + tiempos + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
