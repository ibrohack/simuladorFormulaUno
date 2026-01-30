package clases;

import java.io.Serializable;

public class PlanDeCarrera implements Serializable {

	private static final long serialVersionUID = 1L;
	private Piloto piloto;
	private Circuito circuito;
	private TipoRueda tipoRueda;
	private float desgaste;
	private float litrosGasolina;
	private float velocidadMax;
	private float probChoque;
	private int vueltasParaPit;
	private TipoDeConducion tipoDeConducion;	
	

	public PlanDeCarrera(Piloto piloto, Circuito circuito, TipoRueda tipoRueda, float desgaste, float litrosGasolina,
			float velocidadMax, float probChoque, int vueltasParaPit, TipoDeConducion tipoDeConducion) {
		super();
		this.piloto = piloto;
		this.circuito = circuito;
		this.tipoRueda = tipoRueda;
		this.desgaste = desgaste;
		this.litrosGasolina = litrosGasolina;
		this.velocidadMax = velocidadMax;
		this.probChoque = probChoque;
		this.vueltasParaPit = vueltasParaPit;
		this.tipoDeConducion = tipoDeConducion;
	}



	public PlanDeCarrera() {
		super();
	}
	
	
	
	public final TipoDeConducion getTipoDeConducion() {
		return tipoDeConducion;
	}



	public final void setTipoDeConducion(TipoDeConducion tipoDeConducion) {
		this.tipoDeConducion = tipoDeConducion;
	}



	public final Circuito getCircuito() {
		return circuito;
	}

	public final void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}
	
	public final Piloto getPiloto() {
		return piloto;
	}

	public final void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public final TipoRueda getTipoRueda() {
		return tipoRueda;
	}
	
	public final void setTipoRueda(TipoRueda tipoRueda) {
		this.tipoRueda = tipoRueda;
	}
	
	public final float getDesgaste() {
		return desgaste;
	}
	
	public final void setDesgaste(float desgaste) {
		this.desgaste = desgaste;
	}
	
	public final float getLitrosGasolina() {
		return litrosGasolina;
	}
	
	public final void setLitrosGasolina(float litrosGasolina) {
		this.litrosGasolina = litrosGasolina;
	}
	
	public final float getVelocidadMax() {
		return velocidadMax;
	}
	
	public final void setVelocidadMax(float velocidadMax) {
		this.velocidadMax = velocidadMax;
	}
	
	public final float getProbChoque() {
		return probChoque;
	}
	
	public final void setProbChoque(float probChoque) {
		this.probChoque = probChoque;
	}
	
	public final int getVueltasParaPit() {
		return vueltasParaPit;
	}
	
	public final void setVueltasParaPit(int vueltasParaPit) {
		this.vueltasParaPit = vueltasParaPit;
	}


	@Override
	public String toString() {
		return "Coche [tipoRueda=" + tipoRueda + ", desgaste=" + desgaste + ", litrosGasolina="
				+ litrosGasolina + ", velocidadMax=" + velocidadMax + ", probChoque=" + probChoque + ", vueltasParaPit="
				+ vueltasParaPit + "]";
	}
	
	
}
