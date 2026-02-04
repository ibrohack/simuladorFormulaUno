package main;

import utilidades.*;

import java.io.File;

import metodos.*;

public class Main {

	public static int menu() {
		System.out.println("\n===== MENU ====="
						 + "\n1.- Gestionar Escuderia"
						 + "\n2.- Gestionar Circuitos"
						 + "\n3.- Iniciar Carrera"
						 + "\n0.- Salir");
		return Utilidades.leerInt(0,3);
	}
	
	public static void main(String[] args) {
		File fichCircuitos = new File("circuitos.dat");
		File fichEscuderia = new File("escuderia.dat");
		File fichPilotos = new File("pilotos.dat");
		int opcion=0;
		
		do {
			opcion=menu();
			switch(opcion) {
			case 1:
				GestionEscuderia.menuEscuderia(fichEscuderia);
				break;
			case 2:
				GestionCircuitos.menuCircuitos(fichCircuitos);
				break;
			case 3:
				GestionCarrera.simulacionCarrera(fichCircuitos, fichPilotos);
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			}
		}while(opcion!=0);
	}
}