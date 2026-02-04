package metodos;

import java.io.*;
import java.util.ArrayList;

import clases.*;
import utilidades.Utilidades;

public class GestionCarrera {

	public static Circuito elgirCircuito(File fichC) {
		String nombreCircuito;
		Circuito circuito = null;
		boolean finArchivo=false, encontrado=false;
		ArrayList<Circuito> circuitos = null;
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichC));
			while(!finArchivo) {
				try {
					circuitos = (ArrayList<Circuito>) ois.readObject();
				}catch(EOFException e) {
					finArchivo=true;
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {
			System.out.println("Eleige uno de los sigientes circuitos");
			for(Circuito cr: circuitos) {
				System.out.println(String.format("Circuito %s numero de vueltas %d", cr.getNombreCircuito(), cr.getNumeroVuletas()));
			}
			nombreCircuito = Utilidades.introducirCadena();
			for(int i=0; i<circuitos.size() && !encontrado; i++) {
				if(circuitos.get(i).getNombreCircuito().equalsIgnoreCase(nombreCircuito)) {
					circuito = circuitos.get(i);
					encontrado=true;
				}
			}
			if(!encontrado) {
				System.out.println("No existe ese circuito.");
			}
		}while(!encontrado);
		return circuito;
	}

	public static void cargarPilotos(ArrayList<Piloto> p, File fichP) {
		ObjectInputStream ois;
		boolean finArchivo=false;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichP));
			while(!finArchivo) {
				try {
					p = (ArrayList<Piloto>) ois.readObject();
				}catch(EOFException e) {
					finArchivo=true;
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void simulacionCarrera(File fichCircuito, File fichPilotos) {
		Carrera cr = new Carrera();
		float gasolina;
		cr.setCircuitoCarrera(null);
		ArrayList<Piloto> ps = new ArrayList<Piloto>();
		Circuito c = elgirCircuito(fichCircuito);
		cr.setCircuitoCarrera(c);
		cr.setCodigoCarrera();
		cargarPilotos(ps, fichPilotos);
		for(int i=0; i<=c.getNumeroVuletas(); i++) {
			if(i==0) {
				for(Piloto p: ps) {
					System.out.println("***********Selecion de la configuracion inicial**********");
					PlanDeCarrera pc = new PlanDeCarrera();
					pc.setPiloto(p);
					pc.setCircuito(c);
					GestionPlanDeCarrera.cambiarRuedas(pc);
					System.out.println("¿Cuantos litors de gasolina quieres repostar(Maximo 110L)?");
					gasolina=Utilidades.leerFloat(0, 110);
					pc.setLitrosGasolina(gasolina);
					GestionPlanDeCarrera.cambiarTipoDeConducion(pc);
					System.out.println("¿Cuantas vueltas quieres dar con esta configuracion? (El maxima numero de vueltas son " + cr.getCircuitoCarrera().getNumeroVuletas() +")");
					pc.setVueltasParaPit(cr.getCircuitoCarrera().getNumeroVuletas());
					GestionPlanDeCarrera.velocidadMaxima(pc);
					GestionPlanDeCarrera.probabilidaChoque(pc);
					cr.getCoches().put(p.getCodigo(), pc);
					System.out.println("Configuracion inicial selecionada con exito.");
				}
			}else {
				for(Piloto p: ps) {
					
				}
			}
		}
	}

}
