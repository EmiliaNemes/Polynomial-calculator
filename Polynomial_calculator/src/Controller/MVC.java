package Controller;
import Model.*;
import View.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MVC {

	private static ArrayList<Monomial> monoms;

	public static void main(String[] args) throws Exception {
		
		 Model model = new Model(); 
		 View view = new View(model); 
		 Operations operations = new Operations();
		 Controller controller = new Controller(model, view,operations);
		 
		 view.setVisible(true);
	}

}
