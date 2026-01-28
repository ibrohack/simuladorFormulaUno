package clases;

import java.util.Map;

public class Carrera {
	private Circuito circuitoCarrera;
	private Map<String, Coche> coches;
	
	public Carrera(Circuito circuitoCarrera, Map<String, Coche> coches) {
		super();
		this.circuitoCarrera = circuitoCarrera;
		this.coches = coches;
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

	@Override
	public String toString() {
		return "Carrera [circuitoCarrera=" + circuitoCarrera + ", coches=" + coches + "]";
	}
	
}
