package metodos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import clases.*;

public class CargarDatos {
	@SuppressWarnings("unchecked")
	public static ArrayList<Escuderia> cargarEscuderia(File fichEscuderia){
		ArrayList<Escuderia> aEscuderia = new ArrayList<Escuderia>();
		ObjectInputStream ois=null;
		
		try {
			ois=new ObjectInputStream(new FileInputStream(fichEscuderia));
			Object obj = ois.readObject();
			if(obj instanceof ArrayList) {
				aEscuderia=(ArrayList<Escuderia>) obj;
			}
			ois.close();
		} catch (FileNotFoundException e) {
	        System.out.println("No se encontró el fichero.");
	    } catch (ClassNotFoundException e) {
	        System.out.println("La clase Persona no es válida.");
	    } catch (IOException e) {
	        System.out.println("Error leyendo el fichero.");
	    }
		return aEscuderia;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Circuito> cargarCircuitos(File fichCircuitos){
		ArrayList<Circuito> aCircuitos = new ArrayList<Circuito>();
		ObjectInputStream ois=null;
		
		try {
			ois=new ObjectInputStream(new FileInputStream(fichCircuitos));
			Object obj = ois.readObject();
			if(obj instanceof ArrayList) {
				aCircuitos=(ArrayList<Circuito>) obj;
			}
			ois.close();
		} catch (FileNotFoundException e) {
	        System.out.println("No se encontró el fichero.");
	    } catch (ClassNotFoundException e) {
	        System.out.println("La clase Persona no es válida.");
	    } catch (IOException e) {
	        System.out.println("Error leyendo el fichero.");
	    }
		return aCircuitos;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Piloto> cargarPilotos(String RUTA_FICHERO){
		Map<String, Piloto> pilotos = new HashMap<>();
		File archivo = new File(RUTA_FICHERO);
		if (!archivo.exists()) {
			return pilotos;
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
		return pilotos;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Mecanico> cargarMecanicos(String RUTA_FICHERO){
		 Map<String, Mecanico> mecanicos = new HashMap<>();
	        File archivo = new File(RUTA_FICHERO);
	        if (!archivo.exists()) {
	            return mecanicos;
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
	        return mecanicos;
	}
}