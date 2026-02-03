package metodos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import clases.Piloto;
import utilidades.Utilidades;

public class GestionPilotos {
	private static Map<String, Piloto> pilotos = new HashMap<>();
	private static final String RUTA_FICHERO = "C:\\pilotos.dat";

	public void GestionPilotos() {
		cargarDatos();

		int opcion;
		do {
			mostrarMenu();
			opcion = Utilidades.leerInt("Seleccione una opción: ", 1, 4);
			switch (opcion) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 0:

				System.out.println("Fin del programa.");
				break;
			}
			guardarDatos();
		} while (opcion != 0);
	}

	private static void mostrarMenu() {
		System.out.println("\n--- GESTIÓN DE PILOTOS ---");
		System.out.println("1. Introducir un nuevo Piloto");
		System.out.println("2. Modificar Piloto");
		System.out.println("3. Eliminar Piloto");
		System.out.println("4. Mostrar pilotos");

		System.out.println("0. Salir");
	}

	@SuppressWarnings("unchecked")
	private static void cargarDatos() {
		File archivo = new File(RUTA_FICHERO);
		if (!archivo.exists()) {

			return;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
			// Leemos el objeto. Debería ser el Map.
			Object obj = ois.readObject();
			if (obj instanceof Map) {
				pilotos = (Map<String, Piloto>) obj;
			}
		} catch (EOFException e) {
			// Fichero vacío
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al cargar datos: " + e.getMessage());
		}
	}

	private static void guardarDatos() {
		File archivo = new File(RUTA_FICHERO);
		
		if (archivo.getParentFile() != null) {
			archivo.getParentFile().mkdirs();
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
			oos.writeObject(pilotos);
			System.out.println("Datos guardados correctamente en " + RUTA_FICHERO);
		} catch (IOException e) {
			System.out.println("Error al guardar datos: " + e.getMessage());
		}
	}
}
