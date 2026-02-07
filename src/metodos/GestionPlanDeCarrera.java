package metodos;

import java.time.LocalTime;

import clases.*;
import excepciones.AbandonoException;
import utilidades.Utilidades;


public class GestionPlanDeCarrera {

	public static void cambiarRuedas(PlanDeCarrera planDeCarrera) {
		boolean tipoCorrecto=false;
		String tipoRueda;
		int tiempoCambioNeumaticos;
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
		tiempoCambioNeumaticos = (int) (2.5*planDeCarrera.getMecanico().getFactorCambioNeumaticos());
		if(planDeCarrera.getPiloto().getTiempos().containsKey(planDeCarrera.getCarera().getCodigoCarrera())) {
			planDeCarrera.getPiloto().getTiempos().replace(planDeCarrera.getCarera().getCodigoCarrera(),  planDeCarrera.getPiloto().getTiempos().get(planDeCarrera.getCarera().getCodigoCarrera()).plusSeconds(tiempoCambioNeumaticos));
		}
		System.out.println("Neumaticos reemplazados con exito.");
		planDeCarrera.setTipoRueda(tipo);
		planDeCarrera.setDesgaste(0);

	}

	public static void repostar(PlanDeCarrera planDeCarrera) {
		float gasolina;
		int tiempoRepostar;
		System.out.println(String.format("¿Cuantos litors de gasolina quieres repostar(Maximo %fL)?", 110-planDeCarrera.getLitrosGasolina()));
		gasolina=Utilidades.leerFloat(1, 110-planDeCarrera.getLitrosGasolina());
		planDeCarrera.setLitrosGasolina(gasolina+planDeCarrera.getLitrosGasolina());
		tiempoRepostar= (int) (12*gasolina*planDeCarrera.getMecanico().getFactorRepostaje());
		if(planDeCarrera.getPiloto().getTiempos().containsKey(planDeCarrera.getCarera().getCodigoCarrera())) {
			planDeCarrera.getPiloto().getTiempos().replace(planDeCarrera.getCarera().getCodigoCarrera(),  planDeCarrera.getPiloto().getTiempos().get(planDeCarrera.getCarera().getCodigoCarrera()).plusSeconds(tiempoRepostar));
		}
		System.out.println("Se ha repostado con exito.");
	}

	public static void desgaste(PlanDeCarrera planDeCarrera) throws AbandonoException {
		float desgaste=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito()*0.9))));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito()*0.65))));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito()*0.35))));
		}else {
			desgaste= (float) (1/(1+Math.exp(-((798+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito()*0.15))));
		}

		planDeCarrera.setDesgaste(planDeCarrera.getDesgaste()+desgaste/100);
		if(planDeCarrera.getDesgaste()<=0) {
			throw new AbandonoException(String.format("Se han roto los neumaticos del piloto %s y no puede continuar", planDeCarrera.getPiloto().getNombre()));
		}

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

	public static void consumoDeGasolina(PlanDeCarrera planDeCarrera) throws AbandonoException {
		float consumo=0;
		if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("blando")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.014+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("medio")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.012+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else if(planDeCarrera.getTipoRueda().toString().equalsIgnoreCase("duro")) {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.01+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}else {
			consumo = (float) ((0.5*1.2*1.1*Math.pow(planDeCarrera.getVelocidadMax(),3)+0.022+(768+planDeCarrera.getLitrosGasolina()*0.7)*9.81*planDeCarrera.getVelocidadMax()*planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito())/(0.5*43*Math.pow(10, 6)));
		}
		planDeCarrera.setLitrosGasolina(planDeCarrera.getLitrosGasolina()-consumo/410);
		if(planDeCarrera.getLitrosGasolina()<=0) {
			throw new AbandonoException(String.format("El piloto %s se ha quedado sin gasolina y ha tenido que abandonar la carrera", planDeCarrera.getPiloto().getNombre()));
		}
	}

	public static void probabilidaChoque(PlanDeCarrera planDeCarrera) {
		planDeCarrera.setProbChoque((float) (1-(Math.exp(-(0.002*(planDeCarrera.getVelocidadMax()/(768+planDeCarrera.getLitrosGasolina()*0.7*7)+0.8*Math.pow(planDeCarrera.getDesgaste(),1.5)))))));
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
		if(planDeCarrera.getPiloto().getTiempos().containsKey(planDeCarrera.getPiloto().getCodigo())) {
			System.out.println("Tipo de conducion actualizad con exito");
		}
		planDeCarrera.setTipoDeConducion(tipo);
		planDeCarrera.setDesgaste(0);
	}

	public static void calcularTiempoVuelta(PlanDeCarrera planDeCarrera) {
		float t;
		int minutos, segundos;
		LocalTime tiempo;
		t = (planDeCarrera.getCarera().getCircuitoCarrera().getLongitudCircuito()*100/(planDeCarrera.getVelocidadMax()*60));
		minutos = (int) (t/30);
		segundos = (int) (t-minutos);
		tiempo = LocalTime.of(0, minutos, segundos);
		if(planDeCarrera.getPiloto().getTiempos().containsKey(planDeCarrera.getCarera().getCodigoCarrera())) {
			planDeCarrera.getPiloto().getTiempos().replace(planDeCarrera.getCarera().getCodigoCarrera(),  planDeCarrera.getPiloto().getTiempos().get(planDeCarrera.getCarera().getCodigoCarrera()).plusMinutes(minutos).plusSeconds(segundos));
		}else {
			planDeCarrera.getPiloto().getTiempos().put(planDeCarrera.getCarera().getCodigoCarrera(), tiempo);
		}

	}
}


