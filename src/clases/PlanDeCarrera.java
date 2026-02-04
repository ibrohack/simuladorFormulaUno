package clases;

import java.io.Serializable;

public class PlanDeCarrera implements Serializable {

	private static final long serialVersionUID = 1L;
	private Piloto piloto;
	private Carrera carera;
	private TipoRueda tipoRueda;
	private float desgaste;
	private float litrosGasolina;
	private float velocidadMax;
	private float probChoque;
	private int vueltasParaPit;
	private TipoDeConducion tipoDeConducion;	
	private Mecanico mecanico;

	public PlanDeCarrera(Piloto piloto, Carrera carera, TipoRueda tipoRueda, float desgaste, float litrosGasolina,
			float velocidadMax, float probChoque, int vueltasParaPit, TipoDeConducion tipoDeConducion,
			Mecanico mecanico) {
		super();
		this.piloto = piloto;
		this.carera = carera;
		this.tipoRueda = tipoRueda;
		this.desgaste = desgaste;
		this.litrosGasolina = litrosGasolina;
		this.velocidadMax = velocidadMax;
		this.probChoque = probChoque;
		this.vueltasParaPit = vueltasParaPit;
		this.tipoDeConducion = tipoDeConducion;
		this.mecanico = mecanico;
	}

	public PlanDeCarrera() {
		super();
		litrosGasolina=0;
		desgaste = 0;
		probChoque = 0;
	}

	public final Mecanico getMecanico() {
		return mecanico;
	}

	public final void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public final TipoDeConducion getTipoDeConducion() {
		return tipoDeConducion;
	}



	public final void setTipoDeConducion(TipoDeConducion tipoDeConducion) {
		this.tipoDeConducion = tipoDeConducion;
	}
	
	public final Carrera getCarera() {
		return carera;
	}

	public final void setCarera(Carrera carera) {
		this.carera = carera;
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
