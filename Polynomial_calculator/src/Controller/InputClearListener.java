package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.View;

class InputClearListener implements ActionListener {
    private View  view;
    
    public InputClearListener(View view) {
    	this.view = view;
    }
	
	public void actionPerformed(ActionEvent e) {
        view.resetInput();
    }
}

