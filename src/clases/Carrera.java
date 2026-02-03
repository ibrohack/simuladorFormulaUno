package clases;

import java.util.HashMap;
import java.util.Map;

public class Carrera {
	private Circuito circuitoCarrera;
	private Map<String, PlanDeCarrera> coches;
	private String codigoCarrera;
	static int numeroCarrera=0;
	
	public Carrera(Circuito circuitoCarrera, Map<String, PlanDeCarrera> coches, String codigoCarrera) {
		super();
		this.circuitoCarrera = circuitoCarrera;
		this.coches = coches;
		this.codigoCarrera = codigoCarrera;
		numeroCarrera++;
	}

	public Carrera() {
		super();
		coches = new HashMap<String, PlanDeCarrera>();
		numeroCarrera++;
	}

	public Circuito getCircuitoCarrera() {
		return circuitoCarrera;
	}

	public void setCircuitoCarrera(Circuito circuitoCarrera) {
		this.circuitoCarrera = circuitoCarrera;
	}

	public Map<String, PlanDeCarrera> getCoches() {
		return coches;
	}

	public void setCoches(Map<String, PlanDeCarrera> coches) {
		this.coches = coches;
	}
	
	public final String getCodigoCarrera() {
		return codigoCarrera;
	}

	public final void setCodigoCarrera() {
		this.codigoCarrera = String.join("-", circuitoCarrera.getCodigoCircuito(), Integer.toString(numeroCarrera));
	}

	@Override
	public String toString() {
		return "Carrera [circuitoCarrera=" + circuitoCarrera + ", coches=" + coches + "]";
	}
	
}
