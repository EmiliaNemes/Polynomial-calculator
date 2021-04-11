package Controller;
import Model.*;
import View.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AdditionListener implements ActionListener{
	private Model model;
    private View  view;
    private Operations op;
    
    public AdditionListener(Model model,View view,Operations op) {
    	this.model = model;
    	this.view = view;
    	this.op = op;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Polynomial userInput;
       
            try {
				userInput = new Polynomial(view.getInput());
				op.addTo(userInput);
	            view.setTotal(op.getValue());
			} catch (Exception e1) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Invalid polinom.");
				view.resetInput();
			}
            
            
	}

}
