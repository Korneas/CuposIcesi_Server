package cupos_icesi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import processing.data.XML;

public class DatabaseManager {

	private XML data;
	private File fl;
	private static DatabaseManager ref;

	/**
	 * Constructor del DatabaseManager con el que se carga el archivo .xml y si este no existe entonces se crea
	 * @param ruta
	 */
	public DatabaseManager(String ruta) {
		fl = new File(ruta);

		if (fl.exists() && fl.isFile()) {
			System.out.println("Archivo .xml cargado");
			try {
				data = new XML(fl);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Achivo .xml creado");
			try {
				data = XML.parse("<information></information>");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para el uso del patro Singleton
	 * @return
	 */
	public static DatabaseManager getInstance() {
		if (ref == null) {
			ref = new DatabaseManager("data/info.xml");
		}
		return ref;
	}

	/**
	 * Metodo para agregar objetos tantos al ArrayList de estos como a el archivo .xml
	 * @param o
	 */
	public synchronized void agregar(Object o) {
		
	}

	public void guardar() {
		data.save(fl);
	}

}