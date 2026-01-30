package metodos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.Circuito;
import utilidades.*;

public class GestionCircuitos {
	public static void menuCircuitos(File fichCircuitos) {
		ArrayList<Circuito> aCircuitos = new ArrayList<Circuito>();
		int opciones;
		
		//LEEMOS TODOS LOS DATOS QUE TENGAMOS EN EL FICHERO
		if(fichCircuitos.exists()) {
			leerCircuitos(fichCircuitos, aCircuitos);
		}
		
		do {
			System.out.println("\n===== Gestión de Circuitos ====="
							 + "\n1.- Añadir circuitos."
							 + "\n2.- Modificar circuitos."
							 + "\n3.- Eliminar circuitos."
							 + "\n4.- Mostrar circuitos."
							 + "\n5.- Salir.");
			opciones = Utilidades.leerInt(1, 5);
			switch(opciones) {
			case 1:
				anadirCircuitos(aCircuitos);
				break;
			case 2:
				modificarCircuitos(aCircuitos);
				break;
			case 3:
				eliminarCircuitos(aCircuitos);
				break;
			case 4:
				mostrarCircuitos(aCircuitos);
				break;
			case 5:
				//GUARDAMOS TODOS LOS CAMBIOS REALIZADOS
				guardarCircuitos(fichCircuitos, aCircuitos);
				System.out.println("Volviendo al Menu Principal...");
				break;
			}
		}while(opciones != 5);
	}
	
	//ESCRITURA DE DATOS DEL FICHERO EN UN ARRAYLIST
	public static void leerCircuitos(File fichCircuitos, ArrayList<Circuito> aCircuitos) {
		ObjectInputStream ois=null;
		boolean finArchivo=false;
		
		try {
			ois=new ObjectInputStream(new FileInputStream(fichCircuitos));
			while(!finArchivo) {
				try {
					Circuito c = (Circuito) ois.readObject();
					aCircuitos.add(c);
				}catch(EOFException e) {
					finArchivo=true;
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
	        System.out.println("No se encontró el fichero.");
	    } catch (ClassNotFoundException e) {
	        System.out.println("La clase Persona no es válida.");
	    } catch (IOException e) {
	        System.out.println("Error leyendo el fichero.");
	    }
	}
	
	//ESCRITURA DE DATOS DEL ARRAYLIST EN UN FICHERO
	public static void guardarCircuitos(File fichCircuitos, ArrayList<Circuito> aCircuitos) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichCircuitos));
			for(Circuito c : aCircuitos) {
				oos.writeObject(c);
			}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	SE PIDE UN NOMBRE, COMPROBANDO LA CANTIDAD DE CARACTERES Y LUEGO SI YA EXISTE
	(EN CASO DE QUE EXISTE LE PREGUNTAMOS SI QUIERE INTRODUCIR MÁS CIRCUITOS)
	PEDIMOS EL RESTO DE DATOS, CREAMOS EL CIRCUITO
	LE MANTENEMOS EN EL BUCLE HASTA QUE EL USUARIO LO DECIDA
	*/
	public static void anadirCircuitos(ArrayList<Circuito> aCircuitos) {
		String nombreCarrera, codigo, continuar;
		int numeroVueltas, longitud;
		
		do {
			System.out.println("Introduce el nombre del circuito: ");
			nombreCarrera=Utilidades.introducirCadena();
			while(nombreCarrera.length() < 3) {
				System.out.println("El nombre del circuito debe contener mínimo 3 carácteres. Introduce otro nombre.");
				nombreCarrera = Utilidades.introducirCadena();
			}
			if(buscarNombre(aCircuitos, nombreCarrera) == -1) {
				codigo = crearCodigo(aCircuitos);
				System.out.println("Introduce el número de vueltas del circuito: ");
				numeroVueltas = Utilidades.leerInt(1,Integer.MAX_VALUE);
				System.out.println("Introduce la longitud del circuito: ");
				longitud = Utilidades.leerInt(1,Integer.MAX_VALUE);
				
				Circuito c = new Circuito(codigo, nombreCarrera, numeroVueltas, longitud);
				aCircuitos.add(c);
				System.out.println("Circuito añadido correctamente.");
			}else {
				System.out.println("El nombre del circuito ya existe.");
			}
			System.out.println("\n¿Quiéres añadir más circuitos?");
			continuar = Utilidades.introducirCadena("SI","NO");
		}while(continuar.equalsIgnoreCase("SI"));
	}
	
	public static String crearCodigo(ArrayList<Circuito> aCircuitos) {
		String codigo = "001";
		int nuevoCod = 0;
		boolean fin = false;
		
		//COMPARAMOS EL CODIGO ACTUAL CON EL QUE SE SUPONE QUE DEBERIA SER
		//EN CASO DE QUE SEA DISTINTO TERMINAMOS LA EJECUCION Y LO GUARDAMOS
		if(!aCircuitos.isEmpty()) {
			for(int i=0; i < aCircuitos.size() && !fin; i++) {
				if(Integer.parseInt(aCircuitos.get(i).getCodigoCircuito().substring(4)) != Integer.parseInt(codigo)) {
					fin = true;
				}
				nuevoCod = Integer.parseInt(aCircuitos.get(i).getCodigoCircuito().substring(4))+1;
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
	
	public static int buscarNombre(ArrayList<Circuito> aCircuitos, String nombre) {
		int posicion=-1;
		
		for(int i=0; i < aCircuitos.size() && posicion == -1; i++) {
			if(aCircuitos.get(i).getNombreCircuito().equalsIgnoreCase(nombre)) {
				posicion = i;
			}
		}
		return posicion;
	}
	
	public static void modificarCircuitos(ArrayList<Circuito> aCircuitos) {
		
	}
	
	public static void eliminarCircuitos(ArrayList<Circuito> aCircuitos) {
		String nombreCarrera;
		int posicion;
		
		System.out.println("Introduce el nombre del circuito");
		nombreCarrera = Utilidades.introducirCadena();
		posicion = buscarNombre(aCircuitos, nombreCarrera);
		if(posicion != -1) {
			aCircuitos.remove(posicion);
			System.out.println("Circuito eliminado correctamente.");
		}else {
			System.out.println("El nombre de circuito no esta registrada.");
		}
		
	}

	public static void mostrarCircuitos(ArrayList<Circuito> aCircuitos) {
		if(!aCircuitos.isEmpty()) {
			for(Circuito c : aCircuitos) {
				System.out.println("\n" + c);
			}
		}else {
			System.out.println("No hay circuitos registrados.");
		}
	}
}