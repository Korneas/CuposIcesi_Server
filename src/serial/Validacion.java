package serial;

import java.io.Serializable;

public class Validacion implements Serializable{

	private boolean check;
	private int carrier;
	private String type;
	
	public Validacion(boolean check, int carrier,String type) {
		super();
		this.check = check;
		this.carrier = carrier;
		this.type = type;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getCarrier() {
		return carrier;
	}

	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
