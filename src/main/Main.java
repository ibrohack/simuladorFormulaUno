package main;

import utilidades.Utilidades;

public class Main {

	public static int menu() {
		System.out.println("\n===== MENU ====="
						 + "\n1.- Gestionar Pilotos"
						 + "\n2.- Gestionar Coches"
						 + "\n3.- Gestionar Circuitos"
						 + "\n4.- Iniciar Carrera"
						 + "\n5.- Salir");
		return Utilidades.leerInt(1,5);
	}
	
	public static void main(String[] args) {
		int opcion=0;
		
		do {
			opcion=menu();
			switch(opcion) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				System.out.println("Saliendo...");
				break;
			}
		}while(opcion!=5);
	}
}