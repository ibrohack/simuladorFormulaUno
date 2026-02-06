package metodos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import clases.Piloto;
import clases.Escuderia;
import utilidades.Utilidades;
import java.util.ArrayList;

public class GestionPilotos {

	private static final String RUTA_FICHERO = "pilotos.dat";

	public static void GestionarPilotos() {
		Map<String, Piloto> pilotos = CargarDatos.cargarPilotos(RUTA_FICHERO);

		int opcion;
		do {
			opcion = mostrarMenu();
			switch (opcion) {
				case 1:
					introducirPiloto(pilotos);
					guardarDatos(pilotos);
					break;
				case 2:
					modificarPiloto(pilotos);
					guardarDatos(pilotos);
					break;
				case 3:
					eliminarPiloto(pilotos);
					guardarDatos(pilotos);
					break;
				case 4:
					mostrarPilotos(pilotos);
					guardarDatos(pilotos);
					break;
				case 5:
					verInformacionPiloto(pilotos);
					guardarDatos(pilotos);
					break;
				case 0:
					guardarDatos(pilotos);
					System.out.println("Fin del programa.");
					break;
			}
		} while (opcion != 0);
	}

	private static int mostrarMenu() {
		System.out.println("\n===== GESTIÓN DE PILOTOS =====");
		System.out.println("1.- Introducir un nuevo Piloto");
		System.out.println("2.- Modificar Piloto");
		System.out.println("3.- Eliminar Piloto");
		System.out.println("4.- Mostrar pilotos");
		System.out.println("5.- Ver información de Piloto y Asignar a Escudería");

		System.out.println("0.- Salir");
		return Utilidades.leerInt("Seleccione una opción: ", 0, 5);
	}

	private static void introducirPiloto(Map<String, Piloto> pilotos) {
		char continuar;
		do {
			System.out.println("\n===== ALTA DE PILOTO =====");
			System.out.println("Introduce el Nombre del Piloto:");
			String nombre = Utilidades.introducirCadena();

			int numero = calcularSiguienteNumero(pilotos);
			Piloto nuevoPiloto = new Piloto(nombre, numero);

			pilotos.put(nuevoPiloto.getCodigo(), nuevoPiloto);
			System.out.println("Piloto creado con código: " + nuevoPiloto.getCodigo());

			System.out.println("¿Desea introducir otro piloto? (S/N):");
			continuar = Utilidades.leerChar('S', 'N');
		} while (continuar == 'S');
	}

	private static void modificarPiloto(Map<String, Piloto> pilotos) {
		System.out.println("\n===== MODIFICAR PILOTO =====");
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

	private static void eliminarPiloto(Map<String, Piloto> pilotos) {
		System.out.println("\n===== ELIMINAR PILOTO =====");
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

	private static void mostrarPilotos(Map<String, Piloto> pilotos) {
		System.out.println("\n===== LISTA DE PILOTOS =====");
		if (pilotos.isEmpty()) {
			System.out.println("No hay pilotos registrados.");
		} else {
			for (Piloto piloto : pilotos.values()) {
				System.out.println(piloto);
			}
		}
	}

	private static int calcularSiguienteNumero(Map<String, Piloto> pilotos) {
		int max = 0;
		for (Piloto p : pilotos.values()) {
			String codigo = p.getCodigo();
			if (codigo.length() > 3) {
				try {
					int num = Integer.parseInt(codigo.substring(3));
					if (num > max) {
						max = num;
					}
				} catch (NumberFormatException e) {
					// Ignore invalid formats
				}
			}
		}
		return max + 1;
	}

	private static void guardarDatos(Map<String, Piloto> pilotos) {
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

	private static void verInformacionPiloto(Map<String, Piloto> pilotos) {
		System.out.println("\n===== VER INFORMACIÓN DE PILOTO =====");
		System.out.println("Introduce el Codigo de piloto:");
		String codigo = Utilidades.introducirCadena();

		if (pilotos.containsKey(codigo)) {
			Piloto piloto = pilotos.get(codigo);
			System.out.println(piloto);

			System.out.println("¿Desea agregar el piloto a una escudería? (S/N):");
			if (Utilidades.leerChar('S', 'N') == 'S') {
				File ficheroEscuderias = new File("escuderias.dat");
				ArrayList<Escuderia> escuderias = new ArrayList<>();
				escuderias = CargarDatos.cargarEscuderia(ficheroEscuderias);

				boolean alreadyAssigned = false;
				for (int i = 0; i < escuderias.size() && !alreadyAssigned; i++) {
					Escuderia e = escuderias.get(i);
					Piloto[] pilotosArr = e.getPiloto();
					for (int j = 0; j < pilotosArr.length && !alreadyAssigned; j++) {
						if (pilotosArr[j] != null && pilotosArr[j].getCodigo().equals(piloto.getCodigo())) {
							System.out
									.println("Error: El piloto ya pertenece a la escudería " + e.getNombreEscuderia());
							alreadyAssigned = true;
						}
					}
				}

				if (!alreadyAssigned) {
					GestionEscuderia.mostrarEscuderia(escuderias);

					System.out.println("Introduce el Código de la Escudería:");
					String codigoEscuderia = Utilidades.introducirCadena();

					Escuderia escuderiaSeleccionada = null;
					boolean escuderiaEncontrada = false;
					for (int i = 0; i < escuderias.size() && !escuderiaEncontrada; i++) {
						Escuderia e = escuderias.get(i);
						if (e.getCodigoEscuderia().equalsIgnoreCase(codigoEscuderia)) {
							escuderiaSeleccionada = e;
							escuderiaEncontrada = true;
						}
					}

					if (escuderiaSeleccionada != null) {
						Piloto[] pilotosEscuderia = escuderiaSeleccionada.getPiloto();
						boolean asignado = false;
						for (int i = 0; i < pilotosEscuderia.length && !asignado; i++) {
							if (pilotosEscuderia[i] == null) {
								pilotosEscuderia[i] = piloto;
								piloto.setEscuderia(escuderiaSeleccionada);
								asignado = true;
								System.out.println("Piloto añadido correctamente a la escudería "
										+ escuderiaSeleccionada.getNombreEscuderia());
							}
						}

						if (!asignado) {
							System.out.println("Error: La escudería ya tiene el máximo de 2 pilotos.");
						} else {
							GestionEscuderia.guardarEscuderia(ficheroEscuderias, escuderias);
						}
					} else {
						System.out.println("Error: No se encontró una escudería con ese código.");
					}
				}
			}
		} else {
			System.out.println("Error: No existe ningún piloto con el código " + codigo);
		}
	}
}
