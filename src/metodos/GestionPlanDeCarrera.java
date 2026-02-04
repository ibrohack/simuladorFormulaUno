package metodos;

import clases.*;
import utilidades.Utilidades;


public class GestionPlanDeCarrera {

	public static void cambiarRuedas(PlanDeCarrera planDeCarrera) {
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

	public static void repostar(PlanDeCarrera planDeCarrera) {
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

	public static void desgaste(PlanDeCarrera planDeCarrera) {
		float desgaste=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.145))));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.1))));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.075))));
		}else {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCircuito().getLongitudCircuito()*0.055))));
		}
		planDeCarrera.setDesgaste(desgaste);
	}

	public static void velocidadMaxima(PlanDeCarrera planDeCarrera) {
		float velocidad=0;
		if(planDeCarrera.getTipoDeConducion().toString().equalsIgnoreCase("agresivo")) {
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				velocidad = (float) (320-(320*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				velocidad = (float) (320-(320*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				velocidad = (float) (320-(320*0.02)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				velocidad = (float) (320-(320*0.0089)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}else if(planDeCarrera.getTipoDeConducion().toString().equalsIgnoreCase("Normal")){
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				velocidad = (float) (300-(300*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				velocidad = (float) (300-(300*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				velocidad = (float) (300-(300*0.02)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				velocidad = (float) (300-(300*0.0089)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}else {
			if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
				velocidad = (float) (280-(280*0.05)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
				velocidad = (float) (280-(280*0.03)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
				velocidad = (float) (280-(280*0.0213)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}else {
				velocidad = (float) (280-(280*0.00895)-(768+planDeCarrera.getLitrosGasolina())*0.00981);
			}
		}
		planDeCarrera.setVelocidadMax(velocidad);
	}

	public static void consumoDeGasolina(PlanDeCarrera planDeCarrera) {
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

	public static void probabilidaChoque(PlanDeCarrera planDeCarrera) {
		planDeCarrera.setProbChoque((float) (1-Math.exp(-(0.002*(Math.pow(planDeCarrera.getVelocidadMax(),2)/(768+planDeCarrera.getLitrosGasolina()*0.7)+0.8*Math.pow(planDeCarrera.getDesgaste(),1.5))))));
	}

	public static void cambiarTipoDeConducion(PlanDeCarrera planDeCarrera) {
		boolean tipoCorrecto=false;
		String tipoConducion;
		TipoDeConducion tipo=null;
		do {
			System.out.println("Introduce el tipo de conducion que quieres usar (Agresivo, Neutro o Cauto)");
			tipoConducion = Utilidades.introducirCadena().toUpperCase();
			tipoCorrecto = true;
			try{
				tipo = TipoDeConducion.valueOf(tipoConducion);
			}catch(IllegalArgumentException e) {
				System.out.println("El tipo de conducion introducido no existe.\nIntentalo de nuevo.");
				tipoCorrecto = false;
			}
		}while(!tipoCorrecto);
		System.out.println("Tipo de conducion actualizad con exito");
		planDeCarrera.setTipoDeConducion(tipo);
		planDeCarrera.setDesgaste(0);
	}
}


