package metodos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.*;
import utilidades.*;

public class GestionEscuderia {
	public static void menuEscuderia(File fichEscuderia) {
		ArrayList<Escuderia> aEscuderia = new ArrayList<Escuderia>();
		int opciones;

		// LEEMOS TODOS LOS DATOS QUE TENGAMOS EN EL FICHERO
		if (fichEscuderia.exists()) {
			aEscuderia = CargarDatos.cargarEscuderia(fichEscuderia);
		}

		do {
			opciones = menu();
			switch (opciones) {
				case 1:
					anadirEscuderia(aEscuderia);
					break;
				case 2:
					modificarEscuderia(aEscuderia);
					break;
				case 3:
					eliminarEscuderia(aEscuderia);
					break;
				case 4:
					mostrarEscuderia(aEscuderia);
					break;
				case 0:
					System.out.println("Volviendo al Menú Principal...");
					break;
			}
			// GUARDAMOS TODOS LOS CAMBIOS REALIZADOS
			guardarEscuderia(fichEscuderia, aEscuderia);
		} while (opciones != 0);
	}

	public static int menu() {
		System.out.println("\n=====	GESTIÓN DE ESCUDERÍAS ====="
				+ "\n1.- Añadir escuderías."
				+ "\n2.- Modificar escuderías."
				+ "\n3.- Eliminar escuderías."
				+ "\n4.- Mostrar escuderías."
				+ "\n0.- Salir.");
		return Utilidades.leerInt(0, 4);
	}

	public static void guardarEscuderia(File fichEscuderia, ArrayList<Escuderia> aEscuderia) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichEscuderia));
			oos.writeObject(aEscuderia);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void anadirEscuderia(ArrayList<Escuderia> aEscuderia) {
		String nombreEscuderia, codigo, continuar;

		do {
			System.out.println("\n===== CREACIÓN DE ESCUDERÍA =====");
			System.out.println("Introduce el nombre de la escudería: ");
			nombreEscuderia = Utilidades.introducirCadena();
			while (nombreEscuderia.length() < 5) {
				System.out
						.println("El nombre de la escudería debe contener mínimo 5 carácteres. Introduce otro nombre.");
				nombreEscuderia = Utilidades.introducirCadena();
			}
			if (buscarNombre(aEscuderia, nombreEscuderia) == -1) {
				codigo = crearCodigo(aEscuderia);

				Escuderia e = new Escuderia(codigo, nombreEscuderia);
				aEscuderia.add(e);
				System.out.println("Escudería añadida correctamente.");
			} else {
				System.out.println("El nombre de la escudería ya existe.");
			}
			System.out.println("\n¿Quiéres añadir más escuderías?");
			continuar = Utilidades.introducirCadena("SI", "NO");
		} while (continuar.equalsIgnoreCase("SI"));
	}

	public static String crearCodigo(ArrayList<Escuderia> aEscuderia) {
		String codigo = "001";
		int nuevoCod = 1;

		// Buscamos el codigo maximo
		if (!aEscuderia.isEmpty()) {
			for (int i = 0; i < aEscuderia.size(); i++) {
				if (Integer.parseInt(aEscuderia.get(i).getCodigoEscuderia().substring(6)) > nuevoCod) {
					nuevoCod = Integer.parseInt(aEscuderia.get(i).getCodigoEscuderia().substring(6));
				}
			}

			// Formateamos el codigo
			codigo = String.format("%03d", nuevoCod + 1);
		}
		return codigo;
	}

	public static int buscarNombre(ArrayList<Escuderia> aEscuderia, String nombre) {
		int posicion = -1;

		for (int i = 0; i < aEscuderia.size() && posicion == -1; i++) {
			if (aEscuderia.get(i).getNombreEscuderia().equalsIgnoreCase(nombre)) {
				posicion = i;
			}
		}
		return posicion;
	}

	public static void modificarEscuderia(ArrayList<Escuderia> aEscuderia) {
		String nombreEscuderia, nNombre = "";
		int posicion;
		boolean correcto = false;

		System.out.println("\n===== MODIFICAR ESCUDERÍA =====");
		System.out.println("Introduce el nombre de la escudería: ");
		nombreEscuderia = Utilidades.introducirCadena();
		posicion = buscarNombre(aEscuderia, nombreEscuderia);
		if (posicion != -1) {
			System.out.println("Introduce el nuevo nombre." + " (Nombre actual: "
					+ aEscuderia.get(posicion).getNombreEscuderia() + ")");
			while (!correcto) {
				correcto = true;
				nNombre = Utilidades.introducirCadena();
				if (nNombre.length() < 5) {
					System.out.println(
							"El nombre de la escudería debe contener mínimo 5 carácteres. Introduce otro nombre.");
					correcto = false;
				} else if (buscarNombre(aEscuderia, nNombre) != -1) {
					System.out.println("El nombre de la escudería ya está registrado. Introduce otro nombre.");
					correcto = false;
				}
			}
			aEscuderia.get(posicion).setNombreEscuderia(nNombre);
			aEscuderia.get(posicion).setCodigoEscuderia(nNombre.substring(0, 5).toUpperCase() + "-"
					+ aEscuderia.get(posicion).getCodigoEscuderia().substring(6));
			System.out.println("Cambios realizados correctamente.");
		} else {
			System.out.println("El nombre de la escudería no esta registrada.");
		}
	}

	public static void eliminarEscuderia(ArrayList<Escuderia> aEscuderia) {
		String nombreEscuderia;
		int posicion;

		System.out.println("\n===== ELIMINAR ESCUDERÍA =====");
		System.out.println("Introduce el nombre de la escudería: ");
		nombreEscuderia = Utilidades.introducirCadena();
		posicion = buscarNombre(aEscuderia, nombreEscuderia);
		if (posicion != -1) {
			aEscuderia.remove(posicion);
			System.out.println("Escudería eliminada correctamente.");
		} else {
			System.out.println("El nombre de la escudería no esta registrada.");
		}
	}

	public static void mostrarEscuderia(ArrayList<Escuderia> aEscuderia) {
		if (!aEscuderia.isEmpty()) {
			System.out.println("\n===== LISTA DE ESCUDERÍAS =====");
			for (Escuderia e : aEscuderia) {
				System.out.println("\n" + e);
			}
		} else {
			System.out.println("No hay escuderías registrados.");
		}
	}
}