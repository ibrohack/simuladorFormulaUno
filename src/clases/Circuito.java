package clases;

public class Circuito {
	private String codigoCircuito;
	private String nombreCircuito;
	private int numeroVuletas;
	private int longitudCircuito;
	
	public Circuito(String nombreCircuito, int numeroVuletas, int longitudCircuito) {
		super();
		this.nombreCircuito = nombreCircuito;
		this.codigoCircuito = nombreCircuito.substring(0,3);
		this.numeroVuletas = numeroVuletas;
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
		return numeroVuletas;
	}

	public void setNumeroVuletas(int numeroVuletas) {
		this.numeroVuletas = numeroVuletas;
	}

	public int getLongitudCircuito() {
		return longitudCircuito;
	}

	public void setLongitudCircuito(int longitudCircuito) {
		this.longitudCircuito = longitudCircuito;
	}

	@Override
	public String toString() {
		return "Circuito [codigoCircuito=" + codigoCircuito + ", nombreCircuito=" + nombreCircuito + ", numeroVuletas="
				+ numeroVuletas + ", longitudCircuito=" + longitudCircuito + "]";
	}
}
