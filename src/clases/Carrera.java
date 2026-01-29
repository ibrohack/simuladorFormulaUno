package clases;

import java.util.Map;

public class Carrera {
	private Circuito circuitoCarrera;
	private Map<String, Coche> coches;
	private String codigoCarrera;
	
	
	public Carrera(Circuito circuitoCarrera, Map<String, Coche> coches, String codigoCarrera) {
		super();
		this.circuitoCarrera = circuitoCarrera;
		this.coches = coches;
		this.codigoCarrera = codigoCarrera;
	}

	public Carrera() {
		super();
	}

	public Circuito getCircuitoCarrera() {
		return circuitoCarrera;
	}

	public void setCircuitoCarrera(Circuito circuitoCarrera) {
		this.circuitoCarrera = circuitoCarrera;
	}

	public Map<String, Coche> getCoches() {
		return coches;
	}

	public void setCoches(Map<String, Coche> coches) {
		this.coches = coches;
	}
	
	public final String getCodigoCarrera() {
		return codigoCarrera;
	}

	public final void setCodigoCarrera(String codigoCarrera) {
		this.codigoCarrera = codigoCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [circuitoCarrera=" + circuitoCarrera + ", coches=" + coches + "]";
	}
	
}
