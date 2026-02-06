package metodos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import clases.Mecanico;
import clases.Escuderia;
import utilidades.Utilidades;
import java.util.ArrayList;

public class GestionMecanicos {

    private static final String RUTA_FICHERO = "mecanicos.dat";

    public static void GestionarMecanicos() {
        Map<String, Mecanico> mecanicos = CargarDatos.cargarMecanicos(RUTA_FICHERO);

        int opcion;
        do {
            mostrarMenu();
            opcion = Utilidades.leerInt("Seleccione una opción: ", 0, 4);
            switch (opcion) {
                case 1:
                    introducirMecanico(mecanicos);
                    break;
                case 2:
                    modificarMecanico(mecanicos);
                    break;
                case 3:
                    eliminarMecanico(mecanicos);
                    break;
                case 4:
                    mostrarMecanicos(mecanicos);
                    break;
                case 5:
                    verInformacionMecanico(mecanicos);
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
            }
            guardarDatos(mecanicos);
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== GESTIÓN DE MECANICOS =====");
        System.out.println("1. Introducir un nuevo Mecanico");
        System.out.println("2. Modificar Mecanico");
        System.out.println("3. Eliminar Mecanico");
        System.out.println("4. Mostrar Mecanicos");
        System.out.println("5. Ver información de Mecánico y Asignar a Escudería");

        System.out.println("0. Salir");
    }

    private static void introducirMecanico(Map<String, Mecanico> mecanicos) {
        char continuar;
        do {
            System.out.println("\n===== ALTA DE MECANICO =====");
            System.out.println("Introduce el Nombre del Mecanico:");
            String nombre = Utilidades.introducirCadena();

            int numero = calcularSiguienteNumero(mecanicos);
            Mecanico nuevoMecanico = new Mecanico(nombre, numero);

            mecanicos.put(nuevoMecanico.getCodigo(), nuevoMecanico);
            System.out.println("Mecanico creado con código: " + nuevoMecanico.getCodigo());

            System.out.println("¿Desea introducir otro mecanico? (S/N):");
            continuar = Utilidades.leerChar('S', 'N');
        } while (continuar == 'S');
    }

    private static void modificarMecanico(Map<String, Mecanico> mecanicos) {
        System.out.println("\n===== MODIFICAR MECANICO =====");
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

    private static void eliminarMecanico(Map<String, Mecanico> mecanicos) {
        System.out.println("\n===== ELIMINAR MECANICO =====");
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

    private static void mostrarMecanicos(Map<String, Mecanico> mecanicos) {
        System.out.println("\n===== LISTA DE MECANICOS =====");
        if (mecanicos.isEmpty()) {
            System.out.println("No hay mecanicos registrados.");
        } else {
            for (Mecanico mecanico : mecanicos.values()) {
                System.out.println(mecanico);
            }
        }
    }

    private static int calcularSiguienteNumero(Map<String, Mecanico> mecanicos) {
        int max = 0;
        for (Mecanico m : mecanicos.values()) {
            String codigo = m.getCodigo();
            if (codigo.length() > 3) {
                try {
                    int num = Integer.parseInt(codigo.substring(3));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // Ignore
                }
            }
        }
        return max + 1;
    }

    private static void guardarDatos(Map<String, Mecanico> mecanicos) {
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

    private static void verInformacionMecanico(Map<String, Mecanico> mecanicos) {
        System.out.println("\n===== VER INFORMACIÓN DE MECANICO =====");
        System.out.println("Introduce el Codigo de mecanico:");
        String codigo = Utilidades.introducirCadena();

        if (mecanicos.containsKey(codigo)) {
            Mecanico mecanico = mecanicos.get(codigo);
            System.out.println(mecanico);

            System.out.println("¿Desea agregar el mecanico a una escudería? (S/N):");
            if (Utilidades.leerChar('S', 'N') == 'S') {
                File ficheroEscuderias = new File("escuderias.dat");
                ArrayList<Escuderia> escuderias = new ArrayList<>();
                escuderias = CargarDatos.cargarEscuderia(ficheroEscuderias);

                boolean alreadyAssigned = false;
                for (int i = 0; i < escuderias.size() && !alreadyAssigned; i++) {
                    Escuderia e = escuderias.get(i);
                    if (e.getMecanico() != null && e.getMecanico().getCodigo().equals(mecanico.getCodigo())) {
                        System.out.println("Error: El mecánico ya pertenece a la escudería " + e.getNombreEscuderia());
                        alreadyAssigned = true;
                    }
                }

                if (!alreadyAssigned) {
                    GestionEscuderia.mostrarEscuderia(escuderias);

                    System.out.println("Introduce el Código de la Escudería:");
                    String codigoEscuderia = Utilidades.introducirCadena();

                    Escuderia escuderiaSeleccionada = null;
                    boolean found = false;
                    for (int i = 0; i < escuderias.size() && !found; i++) {
                        Escuderia e = escuderias.get(i);
                        if (e.getCodigoEscuderia().equalsIgnoreCase(codigoEscuderia)) {
                            escuderiaSeleccionada = e;
                            found = true;
                        }
                    }

                    if (escuderiaSeleccionada != null) {
                        boolean overwrite = true;
                        if (escuderiaSeleccionada.getMecanico() != null) {
                            System.out.println("La escudería ya tiene un mecánico asignado: "
                                    + escuderiaSeleccionada.getMecanico().getNombre());
                            System.out.println("¿Desea sobrescribirlo? (S/N):");
                            if (Utilidades.leerChar('S', 'N') == 'N') {
                                overwrite = false;
                                System.out.println("Asignación cancelada.");
                            }
                        }

                        if (overwrite) {
                            escuderiaSeleccionada.setMecanico(mecanico);
                            GestionEscuderia.guardarEscuderia(ficheroEscuderias, escuderias);
                            System.out.println("Mecánico asignado correctamente a la escudería "
                                    + escuderiaSeleccionada.getNombreEscuderia());
                        }
                    } else {
                        System.out.println("Error: No se encontró una escudería con ese código.");
                    }
                }
            }
        } else {
            System.out.println("Error: No existe ningún mecanico con el código " + codigo);
        }
    }
}
