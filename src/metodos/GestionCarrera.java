package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import clases.*;
import excepciones.AbandonoException;
import utilidades.Utilidades;

public class GestionCarrera {

	public static void simulacionCarrera(File fichCircuito, File fichPilotos, File fichEscuderia) {
		Carrera carrera = new Carrera();
		float gasolina;
		int opciones;
		Mecanico mecanico = null;
		ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
		Circuito circuito = elgirCircuito(fichCircuito);
		carrera.setCircuitoCarrera(circuito);
		carrera.setCodigoCarrera();
		cargarPilotos(pilotos, fichPilotos);
		if(fichCircuito.exists() && fichPilotos.exists()) {
			for(int i=0; i<=circuito.getNumeroVuletas(); i++) {
				if(i==0) {
					for(Piloto piloto: pilotos) {
						System.out.println("***********Selecion de la configuracion inicial**********");
						PlanDeCarrera planDeCarrera = new PlanDeCarrera();
						planDeCarrera.setPiloto(piloto);
						planDeCarrera.setCircuito(circuito);
						GestionPlanDeCarrera.cambiarRuedas(planDeCarrera);
						GestionPlanDeCarrera.repostar(planDeCarrera);
						GestionPlanDeCarrera.cambiarTipoDeConducion(planDeCarrera);
						System.out.println("¿Cuantas vueltas quieres dar con esta configuracion? (El maxima numero de vueltas son " + carrera.getCircuitoCarrera().getNumeroVuletas() +")");
						planDeCarrera.setVueltasParaPit(carrera.getCircuitoCarrera().getNumeroVuletas());
						GestionPlanDeCarrera.velocidadMaxima(planDeCarrera);
						GestionPlanDeCarrera.probabilidaChoque(planDeCarrera);
						mecanico = cargarMecanico(planDeCarrera, fichEscuderia);
						planDeCarrera.setMecanico(mecanico);
						carrera.getCoches().put(piloto.getCodigo(), planDeCarrera);
						System.out.println("Configuracion inicial selecionada con exito.");
					}
				}else {
					for(PlanDeCarrera p: carrera.getCoches().values()) {
						try {
							calcularChoque(p);
							GestionPlanDeCarrera.desgaste(p);
							GestionPlanDeCarrera.consumoDeGasolina(p);
							GestionPlanDeCarrera.velocidadMaxima(p);
							GestionPlanDeCarrera.probabilidaChoque(p);
							if(i== p.getVueltasParaPit()) {
								System.out.println(String.format("Tienes %f litoros de gasolina restantes en el deposito.\n"
										+ "El desgaste de los neumaticos es %f%%", p.getLitrosGasolina(), p.getDesgaste()*100));
								opciones = menu();
								do {
									switch(opciones) {
									case 0:
										
										break;

									case 1:
										GestionPlanDeCarrera.repostar(p);
										
										break;

									case 2:
										GestionPlanDeCarrera.cambiarRuedas(p);
										break;

									case 3:
										GestionPlanDeCarrera.cambiarTipoDeConducion(p);
										break;
									}
								}while(opciones != 0);
							}
						} catch (AbandonoException e) {
							System.out.println(e.getMessage());
							carrera.getCoches().remove(p.getPiloto().getCodigo());
						}
					}
				}
			}
		}else {
			System.out.println("No hay pilotos o circuitos");
		}
	}

	public static int menu() {
		int opcion;
		System.out.println("Que quieres hacer en el pit.\n"
				+ "0.	Salir\n"
				+ "1.	Repostar.\n"
				+ "2.	Cambiar de neumaticos.\n"
				+ "3.	Cambiar tipo de conducion.");
		opcion=Utilidades.leerInt(0, 3);
		return opcion;
	}

	@SuppressWarnings("unchecked")
	public static Circuito elgirCircuito(File fichC) {
		String nombreCircuito;
		Circuito circuito = null;
		boolean finArchivo=false, encontrado=false;
		ArrayList<Circuito> circuitos = null;
		ObjectInputStream ois;
		if(fichC.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichC));
				while(!finArchivo) {
					try {
						Object obj = ois.readObject();
						if(obj instanceof ArrayList) {
							circuitos = (ArrayList<Circuito>) obj;
						}
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
		}else {
			System.out.println("No hay circuitos");
		}
		return circuito;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Piloto> cargarPilotos(ArrayList<Piloto> pilotos, File fichP) {
		ObjectInputStream ois;
		boolean finArchivo=false;
		if(fichP.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichP));
				while(!finArchivo) {
					try {
						Object obj = ois.readObject();
						if(obj instanceof ArrayList) {
							pilotos = (ArrayList<Piloto>) obj;
						}
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
		return pilotos;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Escuderia> cargarEscuderias(File fichEscuderia, ArrayList<Escuderia> escuderias) {
		ObjectInputStream ois;
		boolean finArchivo=false;
		if(fichEscuderia.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichEscuderia));
				while(!finArchivo) {
					try {
						Object obj = ois.readObject();
						if(obj instanceof ArrayList) {
							escuderias = (ArrayList<Escuderia>) obj;
						}
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
		return escuderias;
	}

	public static Mecanico cargarMecanico(PlanDeCarrera planDeCarrera, File fichE) {
		Mecanico mecanico = null;
		boolean encontrado = false;
		ArrayList<Escuderia> escuderias = new ArrayList<Escuderia>();
		escuderias = cargarEscuderias(fichE, escuderias);
		for(int i=0; i<escuderias.size() && !encontrado; ) {
			if(planDeCarrera.getPiloto().getCodEscuderia().equalsIgnoreCase(escuderias.get(i).getCodigo())) {
				mecanico = escuderias.get(i).getMecanico();
				encontrado = true;
			}
		}
		return mecanico;
	}
	
	public static void calcularChoque(PlanDeCarrera planDeCarrera) throws AbandonoException {
		Random r = new Random();
		if(r.nextDouble()<planDeCarrera.getProbChoque()) {
			throw new AbandonoException(String.format("El piloto %s se ha chocado", planDeCarrera.getPiloto().getNombre()));
		}
	}


}


