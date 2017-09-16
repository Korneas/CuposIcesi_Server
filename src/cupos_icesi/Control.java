package cupos_icesi;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import serial.Validacion;

public class Control extends Observable implements Runnable {
	private Socket s;
	private Observer boss;
	private boolean life = true;
	private int id;

	public Control(Socket s, Observer boss, int id) {
		this.s = s;
		this.boss = boss;
		this.id = id;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (life) {
			try {
				recibir();
				Thread.sleep(100);
			} catch (IOException e) {
				// Si se desconecta el cliente se envia la notifiacion al
				// Observer de la flata de conexion
				System.out.println("Problema con cliente " + id);
				setChanged();
				boss.update(this, "finConexion");
				life = false;
				clearChanged();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para enviar Objetos a el respectivo cliente
	 * 
	 * @param o
	 * @throws IOException
	 */
	public void enviar(Object o) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject(o);
		System.out.println("Servidor: Se envio: " + o.getClass());
	}

	/**
	 * Metodo para enviar todos los post como actualizacion del Home
	 * 
	 * @throws IOException
	 */
	public void actualizar() throws IOException {
		// ArrayList<Post> post_all = DatabaseManager.getInstance().getPosts();
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject(" ");
		System.out.println("Servidor: Se actualizo el home");
	}

	/**
	 * Metodo para clasificar el Object recibido y decidir que acciones tomar
	 * con sus respectivos valores como su adicion al .xml y el confirmar
	 * inormacion enviada por el usuario
	 * 
	 * @param o
	 */
	private void clasificar(Object o) {
		if (o instanceof Validacion) {
			Validacion val = (Validacion) o;
			if (val.getCarrier() == 1) {
				System.out.println("Me llego un mensaje de pasajero");
			} else if (val.getCarrier() == 2) {
				System.out.println("Me llego un mensaje de conductor");
			}
		}
	}

	/**
	 * Metodo para recibir objetos enviados por el cliente
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void recibir() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		Object recibido = in.readObject();
		clasificar(recibido);
		System.out.println("Cliente: Llego" + recibido.getClass());
		boss.update(null, recibido);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}