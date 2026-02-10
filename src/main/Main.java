package main;

import utilidades.*;
import clases.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import metodos.*;

public class Main {

	public static int menu() {
		System.out.println("\n===== MENU ====="
				+ "\n1.- Gestionar Escuderia"
				+ "\n2.- Gestionar Pilotos"
				+ "\n3.- Gestionar Mecanicos"
				+ "\n4.- Gestionar Circuitos"
				+ "\n5.- Iniciar Carrera"
				+ "\n6.- Mostrar marcador"
				+ "\n0.- Salir");
		return Utilidades.leerInt(0, 6);
	}

	public static void main(String[] args) {
		fillData();
		File fichCircuitos = new File("circuitos.dat");
		File fichEscuderia = new File("escuderias.dat");
		File fichPilotos = new File("pilotos.dat");
		File fichMarcador = new File("marcador.txt");
		int opcion = 0;

		do {
			opcion = menu();
			switch (opcion) {
				case 1:
					GestionEscuderia.menuEscuderia(fichEscuderia);
					break;
				case 2:
					GestionPilotos.GestionarPilotos();
					break;
				case 3:
					GestionMecanicos.GestionarMecanicos();
					break;
				case 4:
					GestionCircuitos.menuCircuitos(fichCircuitos);
					break;
				case 5:
					GestionCarrera.simulacionCarrera(fichCircuitos, fichPilotos, fichMarcador);
					break;
				case 6:
					LecturaMarcador.leerMarcador(fichMarcador);
					break;
				case 0:
					System.out.println("Saliendo...");
					break;
			}

		} while (opcion != 0);
	}

	public static void fillData() {
		File fichCircuitos = new File("circuitos.dat");
		File fichEscuderia = new File("escuderias.dat");
		File fichPilotos = new File("pilotos.dat");
		File fichMecanicos = new File("mecanicos.dat");

		if (!fichEscuderia.exists() && !fichPilotos.exists() && !fichCircuitos.exists()) {
			// 1. Crear Circuitos
			ArrayList<Circuito> circuitos = new ArrayList<>();
			circuitos.add(new Circuito("001", "Bahrain International Circuit", 57, 5412));
			circuitos.add(new Circuito("002", "Jeddah Corniche Circuit", 50, 6174));
			circuitos.add(new Circuito("003", "Albert Park Circuit", 58, 5278));
			circuitos.add(new Circuito("004", "Baku City Circuit", 51, 6003));
			circuitos.add(new Circuito("005", "Miami International Autodrome", 57, 5412));

			GestionCircuitos.guardarCircuitos(fichCircuitos, circuitos);

			// 2. Crear Pilotos y Escuderias
			ArrayList<Escuderia> escuderias = new ArrayList<>();
			Map<String, Piloto> todosLosPilotos = new HashMap<>();
			Map<String, Mecanico> todosLosMecanicos = new HashMap<>();

			// Red Bull Racing
			Piloto p1 = new Piloto("Max Verstappen", 1);
			Piloto p2 = new Piloto("Sergio Perez", 2);
			Mecanico m1 = new Mecanico("Lee Stevenson", 1);
			Escuderia rb = new Escuderia(new Piloto[] { p1, p2 }, m1, "001", "Red Bull Racing");
			p1.setEscuderia(rb);
			p2.setEscuderia(rb);
			escuderias.add(rb);
			todosLosPilotos.put(p1.getCodigo(), p1);
			todosLosPilotos.put(p2.getCodigo(), p2);
			todosLosMecanicos.put(m1.getCodigo(), m1);

			// Ferrari
			Piloto p3 = new Piloto("Charles Leclerc", 3);
			Piloto p4 = new Piloto("Carlos Sainz", 4);
			Mecanico m2 = new Mecanico("Diego Ioverno", 2);
			Escuderia fer = new Escuderia(new Piloto[] { p3, p4 }, m2, "002", "Ferrari");
			p3.setEscuderia(fer);
			p4.setEscuderia(fer);
			escuderias.add(fer);
			todosLosPilotos.put(p3.getCodigo(), p3);
			todosLosPilotos.put(p4.getCodigo(), p4);
			todosLosMecanicos.put(m2.getCodigo(), m2);

			// Mercedes
			Piloto p5 = new Piloto("Lewis Hamilton", 5);
			Piloto p6 = new Piloto("George Russell", 6);
			Mecanico m3 = new Mecanico("Ron Meadows", 3);
			Escuderia mer = new Escuderia(new Piloto[] { p5, p6 }, m3, "003", "Mercedes");
			p5.setEscuderia(mer);
			p6.setEscuderia(mer);
			escuderias.add(mer);
			todosLosPilotos.put(p5.getCodigo(), p5);
			todosLosPilotos.put(p6.getCodigo(), p6);
			todosLosMecanicos.put(m3.getCodigo(), m3);

			// McLaren
			Piloto p7 = new Piloto("Lando Norris", 7);
			Piloto p8 = new Piloto("Oscar Piastri", 8);
			Mecanico m4 = new Mecanico("Andrea Stella", 4);
			Escuderia mcl = new Escuderia(new Piloto[] { p7, p8 }, m4, "004", "McLaren");
			p7.setEscuderia(mcl);
			p8.setEscuderia(mcl);
			escuderias.add(mcl);
			todosLosPilotos.put(p7.getCodigo(), p7);
			todosLosPilotos.put(p8.getCodigo(), p8);
			todosLosMecanicos.put(m4.getCodigo(), m4);

			// Aston Martin
			Piloto p9 = new Piloto("Fernando Alonso", 9);
			Piloto p10 = new Piloto("Lance Stroll", 10);
			Mecanico m5 = new Mecanico("Andy Stevenson", 5);
			Escuderia ast = new Escuderia(new Piloto[] { null, null }, null, "005", "Aston Martin");
			p9.setEscuderia(ast);
			p10.setEscuderia(ast);
			escuderias.add(ast);
			todosLosPilotos.put(p9.getCodigo(), p9);
			todosLosPilotos.put(p10.getCodigo(), p10);
			todosLosMecanicos.put(m5.getCodigo(), m5);

			GestionEscuderia.guardarEscuderia(fichEscuderia, escuderias);

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichPilotos))) {
				oos.writeObject(todosLosPilotos);
			} catch (IOException e) {
				System.out.println("Error al guardar datos iniciales de pilotos: " + e.getMessage());
			}

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichMecanicos))) {
				oos.writeObject(todosLosMecanicos);
			} catch (IOException e) {
				System.out.println("Error al guardar datos iniciales de mecanicos: " + e.getMessage());
			}

			System.out.println("Datos iniciales cargados correctamente.");
		}
	}
}