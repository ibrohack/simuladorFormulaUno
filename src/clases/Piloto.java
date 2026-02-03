package clases;

import java.time.LocalTime;
import java.util.TreeMap;

public class Piloto extends Persona {
	private TreeMap<String, LocalTime>tiempos;

	public Piloto(String codigo, String nombre, TreeMap<String, LocalTime> tiempos) {
		super(codigo, nombre);
		this.tiempos = tiempos;
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
