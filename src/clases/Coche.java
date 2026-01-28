package clases;

public class Coche {
	private String codigo;
	private TipoRueda tipoRueda;
	private float desgaste;
	private float litrosGasolina;
	private float velocidadMax;
	private float probChoque;
	private int vueltasParaPit;
		
	public Coche(String codigo, TipoRueda tipoRueda, float desgaste, float litrosGasolina, float velocidadMax, float probChoque, int vueltasParaPit) {
		super();
		this.codigo = codigo;
		this.tipoRueda = tipoRueda;
		this.desgaste = desgaste;
		this.litrosGasolina = litrosGasolina;
		this.velocidadMax = velocidadMax;
		this.probChoque = probChoque;
		this.vueltasParaPit = vueltasParaPit;
	}
	
	public Coche() {
		super();
	}

	public final String getCodiogo() {
		return codigo;
	}
	
	public final void setCodiogo(String codiogo) {
		codigo = codiogo;
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
		return "Coche [Codiogo=" + codigo + ", tipoRueda=" + tipoRueda + ", desgaste=" + desgaste + ", litrosGasolina="
				+ litrosGasolina + ", velocidadMax=" + velocidadMax + ", probChoque=" + probChoque + ", vueltasParaPit="
				+ vueltasParaPit + "]";
	}
	
	
}
