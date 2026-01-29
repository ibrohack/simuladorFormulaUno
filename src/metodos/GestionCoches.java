package metodos;

import java.io.*;
import java.util.ArrayList;


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
	
	public void repostar(PlanDeCarrera pc) {
		float gasolina;
		boolean repostar;
		System.out.println(String.format("El coche tiene %f litros de gasolina", pc.getLitrosGasolina()));
		System.out.println("¿Quieres repostar?");
		repostar= Utilidades.leerBoolean();
		if(repostar) {
			System.out.println("¿Cuantos litors de gasolina quieres repostar(Maximo 110L)?");
			gasolina=Utilidades.leerFloat(0, 110);
			pc.setLitrosGasolina(gasolina+pc.getLitrosGasolina());
			System.out.println("Se ha repostado con exito.");
		}
	}

}

