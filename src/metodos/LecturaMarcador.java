package metodos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LecturaMarcador {
	public static void leerMarcador(File fichMarcador) {
		if(fichMarcador.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			String linea;
			
			try {
				fr = new FileReader(fichMarcador);
				br = new BufferedReader(fr);
				
				while((linea=br.readLine()) != null) {
					System.out.println(linea);
				}
				
				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//PARA PROBAR LA ESCRITURA EN ARCHIVO DE TEXTO
	public static void escribirMarcador(File fichMarcador) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(fichMarcador);
			bw = new BufferedWriter(fw);
			
			bw.write("Esto es una prueba.");
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}