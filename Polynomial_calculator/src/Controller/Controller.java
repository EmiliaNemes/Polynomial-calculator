package Controller;
import Model.*;
import View.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controller {
	
	 private Model model;
	 private View  view;
	 private Operations operations;
	    
	 /** Constructor */
	 Controller(Model model, View view,Operations operations) throws Exception{
		 this.model = model;
	     this.view  = view;
	     this.operations = operations;
	       
	     try {
	     view.addAdditionListener(new AdditionListener(model,view,operations));
	     view.addSubtractionListener(new SubtractionListener(model,view,operations));
	     view.addMultiplyListener(new MultiplyListener(model,view,operations));
	     view.addDivisionListener(new DivisionListener(model,view,operations));
	     view.addDerivationListener(new DerivationListener(model,view,operations));
	     view.addIntegrationListener(new IntegrationListener(model,view,operations));
	     view.addInputClearListener(new InputClearListener(view));
	     view.addClearListener(new ClearListener(model,view,operations));
	     } 
	     catch (Exception e) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "Invalid polinom.");
			view.resetInput();
		}
	 }
}
