package metodos;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import clases.*;
import excepciones.AbandonoException;
import utilidades.Utilidades;

public class GestionCarrera {

	public static void simulacionCarrera(File fichCircuito, File fichPilotos, File fichMarcador ) {
		Carrera carrera = new Carrera();
		int opciones;
		boolean cambiosHechos=false;
		Mecanico mecanico = null;
		ArrayList <String> pilotosFuera = new ArrayList <String>();
		HashMap<String, Piloto> pilotos = new HashMap<String, Piloto>();
		Circuito circuito = elegirCircuito(fichCircuito);
		if(circuito!=null) {
			carrera.setCircuitoCarrera(circuito);
			carrera.setCodigoCarrera();
		}
		pilotos = cargarPilotos(pilotos, fichPilotos);

		if(!pilotos.isEmpty() || circuito!=null) {
			for(int i=0; i<=circuito.getNumeroVuletas(); i++) {
				if(i==0) {
					for(Piloto piloto: pilotos.values()) {
						System.out.println("\n***** CONFIGURACIÓN INICIAL DE " + piloto.getNombre() + " *****");
						PlanDeCarrera planDeCarrera = new PlanDeCarrera();
						planDeCarrera.setPiloto(piloto);
						mecanico = cargarMecanico(planDeCarrera);
						planDeCarrera.setMecanico(mecanico);
						planDeCarrera.setCarera(carrera);;
						GestionPlanDeCarrera.cambiarRuedas(planDeCarrera);
						GestionPlanDeCarrera.repostar(planDeCarrera);
						GestionPlanDeCarrera.cambiarTipoDeConducion(planDeCarrera);
						System.out.println("¿Cuántas vueltas quieres dar con esta configuración? (El máximo número de vueltas son " + carrera.getCircuitoCarrera().getNumeroVuletas()/2 +")");
						planDeCarrera.setVueltasParaPit(Utilidades.leerInt(1, carrera.getCircuitoCarrera().getNumeroVuletas()/2));
						GestionPlanDeCarrera.velocidadMaxima(planDeCarrera);
						GestionPlanDeCarrera.probabilidaChoque(planDeCarrera);
						mecanico = cargarMecanico(planDeCarrera);
						planDeCarrera.setMecanico(mecanico);
						carrera.getCoches().put(piloto.getCodigo(), planDeCarrera);
						System.out.println("Configuración inicial selecionada con éxito.");
					}
				}else {
					for(PlanDeCarrera planDeCarrera: carrera.getCoches().values()) {
						if(!pilotosFuera.contains(planDeCarrera.getPiloto().getCodigo())) {
							try {
								calcularChoque(planDeCarrera);
							} catch (AbandonoException e) {
								System.out.println(e.getMessage());
								pilotosFuera.add(planDeCarrera.getPiloto().getCodigo());
							}
							try {
								GestionPlanDeCarrera.desgaste(planDeCarrera);
							} catch (AbandonoException e) {
								System.out.println(e.getMessage());
								pilotosFuera.add(planDeCarrera.getPiloto().getCodigo());
							}
							try {
								GestionPlanDeCarrera.consumoDeGasolina(planDeCarrera);
							} catch (AbandonoException e) {
								System.out.println(e.getMessage());
								pilotosFuera.add(planDeCarrera.getPiloto().getCodigo());
							}
							GestionPlanDeCarrera.velocidadMaxima(planDeCarrera);
							GestionPlanDeCarrera.probabilidaChoque(planDeCarrera);
							GestionPlanDeCarrera.calcularTiempoVuelta(planDeCarrera);
							if(i == planDeCarrera.getVueltasParaPit() && !pilotosFuera.contains(planDeCarrera.getPiloto().getCodigo()) && planDeCarrera.getVueltasParaPit()<planDeCarrera.getCarera().getCircuitoCarrera().getNumeroVuletas()) {
								System.out.println(String.format("\n----→ El piloto %s esta entrando al pit ←----", planDeCarrera.getPiloto().getNombre()));
								System.out.println(String.format("\nTienes %f litros de gasolina restantes en el depósito.\n"
										+ "El desgaste de los neumáticos es %f%%.", planDeCarrera.getLitrosGasolina(), planDeCarrera.getDesgaste()*100));
								cambiosHechos=false;
								do {
									opciones = menu();
									switch(opciones) {
									case 0:
										if(!cambiosHechos) {
											System.out.println("No puedes salir del pit sin cambiar nada.");
										}else {
											System.out.println("¿Cuántas vueltas quieres dar con esta configuración? (El máximo número de vueltas son " + (carrera.getCircuitoCarrera().getNumeroVuletas()- i) +")");
											planDeCarrera.setVueltasParaPit(i+Utilidades.leerInt(1, carrera.getCircuitoCarrera().getNumeroVuletas()-i));
											System.out.println("Reanudando carrera...");
										}
										break;

									case 1:
										GestionPlanDeCarrera.repostar(planDeCarrera);
										cambiosHechos=true;
										break;

									case 2:
										GestionPlanDeCarrera.cambiarRuedas(planDeCarrera);
										cambiosHechos=true;
										break;

									case 3:
										GestionPlanDeCarrera.cambiarTipoDeConducion(planDeCarrera);
										cambiosHechos=true;
										break;
									}
								}while(opciones != 0 || !cambiosHechos);
							}
						}
					}
				}

			}
			actualizarPilotos(fichPilotos, pilotos);
			escribirGanador(fichPilotos, carrera, fichMarcador, pilotosFuera);
			System.out.println("Carrera finalizada.");
		}else {
			System.out.println("No hay pilotos.");
		}
	}

	public static int menu() {
		int opcion;

		System.out.println("\n¿Que quieres hacer en el pit?\n"
				+ "0.- Salir\n"
				+ "1.- Repostar.\n"
				+ "2.- Cambiar de neumáticos.\n"
				+ "3.- Cambiar tipo de condución.");
		opcion=Utilidades.leerInt(0, 3);
		return opcion;
	}

	public static void actualizarPilotos(File fichPilotos, HashMap<String, Piloto> pilotos) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichPilotos));
			oos.writeObject(pilotos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Circuito elegirCircuito(File fichCircuitos) {
		String nombreCircuito;
		Circuito circuito = null;
		boolean finArchivo=false, encontrado=false;
		ArrayList<Circuito> circuitos = null;
		ObjectInputStream ois;
		if(fichCircuitos.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichCircuitos));
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
			if(!circuitos.isEmpty()) {
				do {
					System.out.println("\n---- SELECCIÓN DE CIRCUITOS -----");
					for(Circuito cr: circuitos) {
						System.out.println(String.format("Circuito %s número de vueltas %d", cr.getNombreCircuito(), cr.getNumeroVuletas()));
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
				System.out.println("No hay circuitos.");
			}
		}
		return circuito;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Piloto> cargarPilotos(HashMap<String, Piloto> pilotos, File fichP) {
		ObjectInputStream ois;
		boolean finArchivo=false;
		if(fichP.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichP));
				while(!finArchivo) {
					try {
						Object obj = ois.readObject();
						if(obj instanceof Map) {
							pilotos = (HashMap<String, Piloto>) obj;
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


	public static Mecanico cargarMecanico(PlanDeCarrera planDeCarrera) {
		Mecanico mecanico = planDeCarrera.getPiloto().getEscuderia().getMecanico();		
		return mecanico;
	}

	public static void calcularChoque(PlanDeCarrera planDeCarrera) throws AbandonoException {
		Random r = new Random();
		if(r.nextDouble()<=planDeCarrera.getProbChoque()) {
			throw new AbandonoException(String.format("El piloto %s se ha chocado y no puede continuar en la carrera.", planDeCarrera.getPiloto().getNombre()));
		}
	}

	public static void escribirGanador(File fichP, Carrera c, File fichMarcador, ArrayList<String> pilotosFuera) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		HashMap<String, Piloto> pilotos = new HashMap<String, Piloto>();
		Piloto piloto = null;
		LocalTime tmax = LocalTime.of(10, 0, 0);
		pilotos = cargarPilotos(pilotos, fichP);
		for(Piloto p: pilotos.values()) {
			for(String codigoCarrera: p.getTiempos().keySet()) {
				if(codigoCarrera.equalsIgnoreCase(c.getCodigoCarrera()) && p.getTiempos().get(codigoCarrera).isBefore(tmax) && !pilotosFuera.contains(p.getCodigo())) {
					tmax = p.getTiempos().get(codigoCarrera);
					piloto = p;
				}
			}
		}
		if(piloto!=null){
			try {
				fw = new FileWriter(fichMarcador, true);
				bw = new BufferedWriter(fw);
				bw.write(String.format("El ganador de la carrera %s es %s con un tiempo de %s", c.getCodigoCarrera(), piloto.getNombre(), piloto.getTiempos().get(c.getCodigoCarrera()) ));
				bw.newLine();

				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}