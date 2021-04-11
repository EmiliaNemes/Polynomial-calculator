package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;
import View.View;

class ClearListener implements ActionListener {
	private Model model;
    private View  view;
    private Operations op;
    
    public ClearListener(Model model,View view,Operations op) {
    	this.model = model;
    	this.view = view;
    	this.op = op;
    }
	
	public void actionPerformed(ActionEvent e) {
        model.reset();
        view.resetTotal();
        op.reset();
    }
}
