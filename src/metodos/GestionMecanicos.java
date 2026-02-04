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
            opcion = Utilidades.leerInt("Seleccione una opción: ", 0, 4);
            switch (opcion) {
                case 1:
                    introducirMecanico();
                    break;
                case 2:
                    modificarMecanico();
                    break;
                case 3:
                    eliminarMecanico();
                    break;
                case 4:
                    mostrarMecanicos();
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
                System.out.println("Introduce el Nombre del Mecanico:");
                String nombre = Utilidades.introducirCadena();

                Mecanico nuevoMecanico = new Mecanico(codigo, nombre);

                mecanicos.put(codigo, nuevoMecanico);
            }

            System.out.println("¿Desea introducir otro mecanico? (S/N):");
            continuar = Utilidades.leerChar('S', 'N');
        } while (continuar == 'S');
    }

    private static void modificarMecanico() {
        System.out.println("\n--- MODIFICAR MECANICO ---");
        System.out.println("Introduce el Codigo de mecanico a modificar:");
        String codigo = Utilidades.introducirCadena();

        if (mecanicos.containsKey(codigo)) {
            Mecanico mecanico = mecanicos.get(codigo);
            System.out.println("Datos actuales del mecanico: " + mecanico);

            System.out.println("Introduce el nuevo Nombre del Mecanico:");
            String nuevoNombre = Utilidades.introducirCadena();

            mecanico.setNombre(nuevoNombre);
            System.out.println("Mecanico modificado correctamente.");
        } else {
            System.out.println("Error: No existe ningún mecanico con el código " + codigo);
        }
    }

    private static void eliminarMecanico() {
        System.out.println("\n--- ELIMINAR MECANICO ---");
        System.out.println("Introduce el Codigo de mecanico a eliminar:");
        String codigo = Utilidades.introducirCadena();

        if (mecanicos.containsKey(codigo)) {
            System.out.println(
                    "¿Está seguro de que desea eliminar al mecanico " + mecanicos.get(codigo).getNombre() + "?");
            if (Utilidades.leerBoolean()) {
                mecanicos.remove(codigo);
                System.out.println("Mecanico eliminado correctamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Error: No existe ningún mecanico con el código " + codigo);
        }
    }

    private static void mostrarMecanicos() {
        System.out.println("\n--- LISTA DE MECANICOS ---");
        if (mecanicos.isEmpty()) {
            System.out.println("No hay mecanicos registrados.");
        } else {
            for (Mecanico mecanico : mecanicos.values()) {
                System.out.println(mecanico);
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
