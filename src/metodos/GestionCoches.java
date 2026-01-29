package metodos;

import java.io.*;

import utilidades.Utilidades;

public class GestionCoches {

	public void menuCoches(File fich) {
		int opciones;
		do {
			System.out.println("1.	Añadir coches.\n"
					+ "2.	Eliminar coches.\n"
					+ "3.	Mostrar coches.\n"
					+ "4.	Salir.");
			opciones = Utilidades.leerInt(1, 4);
		}while(opciones != 4);
		
		
	}
	
	public void añadirCoche(File fich) {
		
	}
	
}
