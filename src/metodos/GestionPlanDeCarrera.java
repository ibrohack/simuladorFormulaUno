package metodos;

import clases.PlanDeCarrera;
import clases.TipoRueda;
import utilidades.Utilidades;


public class GestionPlanDeCarrera {

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

	public void desgaste(PlanDeCarrera planDeCarrera) {
		float desgaste=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.145)/100;
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.1)/100;
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.075)/100;
		}else {
			desgaste= (float) ((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.055)/100;
		}
		planDeCarrera.setDesgaste(desgaste);
	}

	public void velozidadMaxima(PlanDeCarrera planDeCarrera) {
		float v=0;
		if(planDeCarrera.getTipoDeConducion().toString().equalsIgnoreCase("agresivo")) {
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				v = (float) (320-(320*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				v = (float) (320-(320*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				v = (float) (320-(320*0.02)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				v = (float) (320-(320*0.0089)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}else if(planDeCarrera.getTipoDeConducion().toString().equalsIgnoreCase("Normal")){
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				v = (float) (300-(300*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				v = (float) (300-(300*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				v = (float) (300-(300*0.02)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				v = (float) (300-(300*0.0089)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}else {
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				v = (float) (280-(280*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				v = (float) (280-(280*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				v = (float) (280-(280*0.02)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				v = (float) (280-(280*0.0089)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}
		planDeCarrera.setVelocidadMax(v);
	}

	public void consumoDeGasolina(PlanDeCarrera planDeCarrera) {
		float consumo=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.014+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCircuito().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.012+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCircuito().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.01+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCircuito().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.022+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCircuito().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}
		planDeCarrera.setLitrosGasolina(planDeCarrera.getLitrosGasolina()-consumo);
	}

	
}

