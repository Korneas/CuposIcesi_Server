package cupos_icesi;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer{
	
	private PApplet app;
	private Servidor server;
	
	public Logica(PApplet app){
		this.app = app;
		
		server = new Servidor();
		new Thread(server).start();
		server.addObserver(this);
		
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void ejecutar(){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
