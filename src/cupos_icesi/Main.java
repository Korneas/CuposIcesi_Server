package cupos_icesi;

import processing.core.PApplet;

public class Main extends PApplet{
	
	private Logica app;
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		PApplet.main("cupos_icesi.Main");
	}
	
	@Override
	public void settings(){
		size(300,300);
	}
	
	@Override
	public void setup(){
		app = new Logica(this);
	}
	
	@Override
	public void draw(){
		background(0);
		app.ejecutar();
	}

}
