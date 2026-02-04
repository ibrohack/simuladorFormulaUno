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

	public static void GestionarPilotos() {
		cargarDatos();

		int opcion;
		do {
			mostrarMenu();
			mostrarMenu();
			opcion = Utilidades.leerInt("Seleccione una opción: ", 0, 4);
			switch (opcion) {
				case 1:
					introducirPiloto();
					break;
				case 2:
					modificarPiloto();
					break;
				case 3:
					eliminarPiloto();
					break;
				case 4:
					mostrarPilotos();
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

	private static void introducirPiloto() {
		char continuar;
		do {
			System.out.println("\n--- ALTA DE PILOTO ---");
			System.out.println("Introduce el Codigo de piloto:");
			String codigo = Utilidades.introducirCadena();

			if (pilotos.containsKey(codigo)) {
				System.out.println("Error: El piloto con Codigo " + codigo + " ya existe.");
				System.out.println(pilotos.get(codigo));
			} else {
				System.out.println("Introduce el Nombre del Piloto:");
				String nombre = Utilidades.introducirCadena();

				Piloto nuevoPiloto = new Piloto(codigo, nombre);

				pilotos.put(codigo, nuevoPiloto);
			}

			System.out.println("¿Desea introducir otro piloto? (S/N):");
			continuar = Utilidades.leerChar('S', 'N');
		} while (continuar == 'S');
	}

	private static void modificarPiloto() {
		System.out.println("\n--- MODIFICAR PILOTO ---");
		System.out.println("Introduce el Codigo de piloto a modificar:");
		String codigo = Utilidades.introducirCadena();

		if (pilotos.containsKey(codigo)) {
			Piloto piloto = pilotos.get(codigo);
			System.out.println("Datos actuales del piloto: " + piloto);

			System.out.println("Introduce el nuevo Nombre del Piloto:");
			String nuevoNombre = Utilidades.introducirCadena();

			piloto.setNombre(nuevoNombre);
			System.out.println("Piloto modificado correctamente.");
		} else {
			System.out.println("Error: No existe ningún piloto con el código " + codigo);
		}
	}

	private static void eliminarPiloto() {
		System.out.println("\n--- ELIMINAR PILOTO ---");
		System.out.println("Introduce el Codigo de piloto a eliminar:");
		String codigo = Utilidades.introducirCadena();

		if (pilotos.containsKey(codigo)) {
			System.out.println("¿Está seguro de que desea eliminar al piloto " + pilotos.get(codigo).getNombre() + "?");
			if (Utilidades.leerBoolean()) {
				pilotos.remove(codigo);
				System.out.println("Piloto eliminado correctamente.");
			} else {
				System.out.println("Eliminación cancelada.");
			}
		} else {
			System.out.println("Error: No existe ningún piloto con el código " + codigo);
		}
	}

	private static void mostrarPilotos() {
		System.out.println("\n--- LISTA DE PILOTOS ---");
		if (pilotos.isEmpty()) {
			System.out.println("No hay pilotos registrados.");
		} else {
			for (Piloto piloto : pilotos.values()) {
				System.out.println(piloto);
			}
		}
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
