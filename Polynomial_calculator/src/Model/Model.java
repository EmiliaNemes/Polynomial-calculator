package Model;

import java.math.BigInteger;

public class Model {
	private Polynomial total;

	//Constructor
	public Model() {
        reset();
    }
	
	public void reset() {
        total = new Polynomial();
    }
	
}
