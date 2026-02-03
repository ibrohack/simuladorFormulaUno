package clases;

import java.io.Serializable;

public class Circuito implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCircuito;
	private String nombreCircuito;
	private int numeroVueltas;
	private float longitudCircuito;
	
	public Circuito(String codigoCircuito, String nombreCircuito, int numeroVuletas, float longitudCircuito) {
		super();
		this.nombreCircuito = nombreCircuito;
		this.codigoCircuito = nombreCircuito.toUpperCase().substring(0,3) + "-" + codigoCircuito;
		this.numeroVueltas = numeroVuletas;
		this.longitudCircuito = longitudCircuito;
	}

	public String getCodigoCircuito() {
		return codigoCircuito;
	}

	public void setCodigoCircuito(String codigoCircuito) {
		this.codigoCircuito = codigoCircuito;
	}

	public String getNombreCircuito() {
		return nombreCircuito;
	}

	public void setNombreCircuito(String nombreCircuito) {
		this.nombreCircuito = nombreCircuito;
	}

	public int getNumeroVuletas() {
		return numeroVueltas;
	}

	public void setNumeroVuletas(int numeroVuletas) {
		this.numeroVueltas = numeroVuletas;
	}

	public float getLongitudCircuito() {
		return longitudCircuito;
	}

	public void setLongitudCircuito(float longitudCircuito) {
		this.longitudCircuito = longitudCircuito;
	}

	@Override
	public String toString() {
		return "Circuito [codigoCircuito=" + codigoCircuito + ", nombreCircuito=" + nombreCircuito + ", numeroVuletas="
				+ numeroVueltas + ", longitudCircuito=" + longitudCircuito + "]";
	}
}
