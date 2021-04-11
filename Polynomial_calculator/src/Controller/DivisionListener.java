package Controller;
import Model.*;
import View.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

class DivisionListener implements ActionListener {
	private Model model;
    private View  view;
    private Operations op;
    
    public DivisionListener(Model model,View view,Operations op) {
    	this.model = model;
    	this.view = view;
    	this.op = op;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Polynomial userInput;
       
            try {
				userInput = new Polynomial(view.getInput());
				op.divideBy(userInput);
	            view.setTotal(op.getValue());
	            view.setRest(op.getRest());
			}catch(MyException ex) {
		    	Component frame = null;
				JOptionPane.showMessageDialog(frame, "Invalid operation");
				view.resetInput();
		     } 
            catch (Exception e1) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Invalid polinom");
				view.resetInput();
			}
            
            
	}

}
