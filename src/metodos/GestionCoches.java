package metodos;




import clases.PlanDeCarrera;
import clases.TipoRueda;
import utilidades.Utilidades;

public class GestionCoches {

	public void cambiarRuedas(PlanDeCarrera planDeCarrera) {
		boolean tipoCorrecto=false;
		String tipoRueda;
		TipoRueda tipo=null;
		do {
			System.out.println("Introduce el tipo de rueda que quieres poner (Blando,Medio,Duro o Lluvia)");
			tipoRueda = Utilidades.introducirCadena().toUpperCase();
			tipoCorrecto = true;
			try{
				tipo = TipoRueda.valueOf(tipoRueda);
			}catch(IllegalArgumentException e) {
				System.out.println("El tipo de rueda introducido no existe.\nIntentalo de nuevo.");
				tipoCorrecto = false;
			}
		}while(!tipoCorrecto);
		System.out.println("Neumaticos reemplazados con exito.");
		planDeCarrera.setTipoRueda(tipo);
		planDeCarrera.setDesgaste(0);
	}
	
	public void repostar(PlanDeCarrera planDeCarrera) {
		float gasolina;
		boolean repostar;
		System.out.println(String.format("El coche tiene %f litros de gasolina", planDeCarrera.getLitrosGasolina()));
		System.out.println("¿Quieres repostar?");
		repostar= Utilidades.leerBoolean();
		if(repostar) {
			System.out.println("¿Cuantos litors de gasolina quieres repostar(Maximo 110L)?");
			gasolina=Utilidades.leerFloat(0, 110);
			planDeCarrera.setLitrosGasolina(gasolina+planDeCarrera.getLitrosGasolina());
			System.out.println("Se ha repostado con exito.");
		}
	}

	public void desgaste(PlanDeCarrera planDeCarrera, float distancia) {
		float desgaste=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*distancia*0.145)/1000;
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*distancia*0.1)/1000;
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*distancia*0.075)/1000;
		}else {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*distancia*0.055)/1000;
		}
		planDeCarrera.setDesgaste(desgaste);
	}
	
}

