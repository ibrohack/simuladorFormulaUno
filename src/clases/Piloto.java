package clases;

import java.time.LocalTime;
import java.util.TreeMap;

public class Piloto {
	private String codigoPiloto;
	private String nombre;
	private TreeMap<String, LocalTime>tiempos;
	public final String getCodigoPiloto() {
		return codigoPiloto;
	}
	public final void setCodigoPiloto(String codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
	}
	public final String getNombre() {
		return nombre;
	}
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public final TreeMap<String, LocalTime> getTiempos() {
		return tiempos;
	}
	public final void setTiempos(TreeMap<String, LocalTime> tiempos) {
		this.tiempos = tiempos;
	}
	public Piloto(String codigoPiloto, String nombre, TreeMap<String, LocalTime> tiempos) {
		super();
		this.codigoPiloto = codigoPiloto;
		this.nombre = nombre;
		this.tiempos = tiempos;
	}
	public Piloto() {
		super();
	}
	@Override
	public String toString() {
		return "Piloto [codigoPiloto=" + codigoPiloto + ", nombre=" + nombre + ", tiempos=" + tiempos + "]";
	}
	
	
	
}
