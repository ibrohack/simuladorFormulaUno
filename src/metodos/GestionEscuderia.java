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
		
		//LEEMOS TODOS LOS DATOS QUE TENGAMOS EN EL FICHERO
		if(fichEscuderia.exists()) {
			aEscuderia=CargarDatos.cargarEscuderia(fichEscuderia);		
		}
		
		do {
			opciones = menu();
			switch(opciones) {
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
			case 5:
				GestionPilotos.GestionarPilotos();
				break;
			case 6:
				GestionMecanicos.GestionarMecanicos();
				break;
			case 0:
				//GUARDAMOS TODOS LOS CAMBIOS REALIZADOS
				guardarEscuderia(fichEscuderia, aEscuderia);
				System.out.println("Volviendo al Menu Principal...");
				break;
			}
		}while(opciones != 0);
	}
	
	public static int menu() {
		System.out.println("\n===== Gestión de Escuderias ====="
				 + "\n1.- Añadir escuderias."
				 + "\n2.- Modificar escuderias."
				 + "\n3.- Eliminar escuderias."
				 + "\n4.- Mostrar escuderias."
				 + "\n5.- Gestionar Pilotos"
				 + "\n6.- Gestionar Mecanicos"
				 + "\n0.- Salir.");
		return Utilidades.leerInt(0, 6);
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
			System.out.println("Introduce el nombre de la escuderia: ");
			nombreEscuderia=Utilidades.introducirCadena();
			while(nombreEscuderia.length() < 5) {
				System.out.println("El nombre de la escuderia debe contener mínimo 5 carácteres. Introduce otro nombre.");
				nombreEscuderia = Utilidades.introducirCadena();
			}
			if(buscarNombre(aEscuderia, nombreEscuderia)==-1) {
				codigo = crearCodigo(aEscuderia);
				
				Escuderia e = new Escuderia(codigo, nombreEscuderia);
				aEscuderia.add(e);
				System.out.println("Escuderia añadida correctamente.");
			}
			else {
				System.out.println("El nombre de la escuderia ya existe.");
			}
			System.out.println("\n¿Quiéres añadir más circuitos?");
			continuar = Utilidades.introducirCadena("SI","NO");
		}while(continuar.equalsIgnoreCase("SI"));
	}
	
	public static String crearCodigo(ArrayList<Escuderia> aEscuderia) {
		String codigo = "001";
		int nuevoCod = 1;
		boolean fin = false;
		
		//COMPARAMOS EL CODIGO ACTUAL CON EL QUE SE SUPONE QUE DEBERIA SER
		//EN CASO DE QUE SEA DISTINTO TERMINAMOS LA EJECUCION Y LO GUARDAMOS
		if(!aEscuderia.isEmpty()) {
			for(int i=0; i < aEscuderia.size() && !fin; i++) {
				if(Integer.parseInt(aEscuderia.get(i).getCodigoEscuderia().substring(6)) != nuevoCod) {
					fin = true;
				}
				if(!fin) {
					nuevoCod = Integer.parseInt(aEscuderia.get(i).getCodigoEscuderia().substring(6))+1;
				}
			}
			
			//PARA QUE EL CODIGO CONTENGA 3 DIGITOS TENEMOS EN CUENTA LA LO LONGITUD DEL MISMO
			if(String.valueOf(nuevoCod).length()==1) {
				codigo = "00" + String.valueOf(nuevoCod);
			}else if(String.valueOf(nuevoCod).length()==2) {
				codigo = "0" + String.valueOf(nuevoCod);
			}
		}
		return codigo;
	}
	
	public static int buscarNombre(ArrayList<Escuderia> aEscuderia, String nombre) {
		int posicion=-1;
		
		for(int i=0; i < aEscuderia.size() && posicion == -1; i++) {
			if(aEscuderia.get(i).getNombreEscuderia().equalsIgnoreCase(nombre)) {
				posicion = i;
			}
		}
		return posicion;
	}
	
	public static void modificarEscuderia(ArrayList<Escuderia> aEscuderia) {
		String nombreEscuderia, nNombre="";
		int posicion;
		boolean correcto=false;
		
		System.out.println("Introduce el nombre de la escuderia: ");
		nombreEscuderia = Utilidades.introducirCadena();
		posicion = buscarNombre(aEscuderia, nombreEscuderia);
		if(posicion != -1) {
			System.out.println("Introduce el nuevo nombre." + " (Nombre actual: " + aEscuderia.get(posicion).getNombreEscuderia() + ")");
			while(!correcto) {
				correcto=true;
				nNombre=Utilidades.introducirCadena();
				if(nNombre.length() < 5) {
					System.out.println("El nombre de la escuderia debe contener mínimo 5 carácteres. Introduce otro nombre.");
					correcto=false;
				}else if(buscarNombre(aEscuderia, nNombre) != -1) {
					System.out.println("El nombre de la escuderia ya está registrado. Introduce otro nombre.");
					correcto=false;
				}
			}
			aEscuderia.get(posicion).setNombreEscuderia(nNombre);
			aEscuderia.get(posicion).setCodigoEscuderia(nNombre.substring(0,5).toUpperCase() + "-" + aEscuderia.get(posicion).getCodigoEscuderia().substring(6));
			System.out.println("Cambios realizados correctamente.");
		}else {
			System.out.println("El nombre de la escuderia no esta registrada.");
		}
	}
	
	public static void eliminarEscuderia(ArrayList<Escuderia> aEscuderia) {
		String nombreEscuderia;
		int posicion;
		
		System.out.println("Introduce el nombre del circuito");
		nombreEscuderia = Utilidades.introducirCadena();
		posicion = buscarNombre(aEscuderia, nombreEscuderia);
		if(posicion != -1) {
			aEscuderia.remove(posicion);
			System.out.println("Escuderia eliminada correctamente.");
		}else {
			System.out.println("El nombre de la escuderia no esta registrada.");
		}
	}
	
	public static void mostrarEscuderia(ArrayList<Escuderia> aEscuderia) {
		if(!aEscuderia.isEmpty()) {
			for(Escuderia e : aEscuderia) {
				System.out.println("\n" + e);
			}
		}else {
			System.out.println("No hay circuitos registrados.");
		}
	}
}