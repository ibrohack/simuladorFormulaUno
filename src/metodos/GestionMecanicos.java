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

import clases.Mecanico;
import utilidades.Utilidades;

public class GestionMecanicos {
    private static Map<String, Mecanico> mecanicos = new HashMap<>();
    private static final String RUTA_FICHERO = "C:\\mecanicos.dat";

    public static void GestionarMecanicos() {
        cargarDatos();

        int opcion;
        do {
            mostrarMenu();
            opcion = Utilidades.leerInt("Seleccione una opción: ", 0, 3);
            switch (opcion) {
                case 1:
                    introducirMecanico();
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
        System.out.println("\n--- GESTIÓN DE MECANICOS ---");
        System.out.println("1. Introducir un nuevo Mecanico");
        System.out.println("2. Modificar Mecanico");
        System.out.println("3. Eliminar Mecanico");
        System.out.println("4. Mostrar Mecanicos");

        System.out.println("0. Salir");
    }

    private static void introducirMecanico() {
        char continuar;
        do {
            System.out.println("\n--- ALTA DE MECANICO ---");
            System.out.println("Introduce el Codigo de mecanico:");
            String codigo = Utilidades.introducirCadena();

            if (mecanicos.containsKey(codigo)) {
                System.out.println("Error: El mecanico con Codigo " + codigo + " ya existe.");
                System.out.println(mecanicos.get(codigo));
            } else {
                System.out.println("Introduce el Nombre del Piloto:");
                String nombre = Utilidades.introducirCadena();

                Mecanico nuevoMecanico = new Mecanico(codigo, nombre);

                mecanicos.put(codigo, nuevoMecanico);
            }

            System.out.println("¿Desea introducir otro piloto? (S/N):");
            continuar = Utilidades.leerChar('S', 'N');
        } while (continuar == 'S');
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
                mecanicos = (Map<String, Mecanico>) obj;
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
            oos.writeObject(mecanicos);
            System.out.println("Datos guardados correctamente en " + RUTA_FICHERO);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}
