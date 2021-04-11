package View;
import Model.*;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
//import java.awt.event.*;

public class View extends JFrame{

	private JTextField inputTextField  = new JTextField(20);
	private JTextField totalTextField  = new JTextField(20);
	private JTextField restTextField   = new JTextField(20);
    private JButton    additionBtn     = new JButton("Add");
    private JButton    subtractionBtn  = new JButton("Subtract");
    private JButton    multiplyBtn     = new JButton("Multiply");
    private JButton    divisionBtn     = new JButton("Divide");
    private JButton    derivationBtn   = new JButton("Derivate");
    private JButton    integrationBtn  = new JButton("Integrate");
    private JButton    clearBtnInput   = new JButton("Clear");
    private JButton    clearBtnTotal   = new JButton("Clear");
    
    private Model model;
    
    // Constructor
    public View(Model model){
    	resetTotal();
    	this.model = model;
    	
    	JPanel panel1 = new JPanel();
    	panel1.setLayout(new FlowLayout());
    	panel1.add(new JLabel("Input"));
    	panel1.add(inputTextField);
    	panel1.add(clearBtnInput);
    	
    	JPanel panel2 = new JPanel();
    	panel2.setLayout(new GridLayout(0,2));
    	panel2.add(additionBtn);
    	panel2.add(subtractionBtn);
    	panel2.add(multiplyBtn);
    	panel2.add(divisionBtn);
    	panel2.add(derivationBtn);
    	panel2.add(integrationBtn);
    	
    	JPanel panel3 = new JPanel();
    	panel3.setLayout(new FlowLayout());
    	panel3.add(new JLabel("Total"));
    	panel3.add(totalTextField);
    	panel3.add(clearBtnTotal);
    	
    	JPanel panel4 = new JPanel();
    	panel4.setLayout(new FlowLayout());
    	panel4.add(new JLabel("Rest (la impartire)"));
    	panel4.add(restTextField);
    	
    	JPanel panel = new JPanel();
    	panel.add(panel1);
    	panel.add(panel2);
    	panel.add(panel3);
    	panel.add(panel4);
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    	this.setContentPane(panel);
    	this.pack(); // the panel appears with the accurate dimension 
    	this.setTitle("Polynomial Calculator");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    
    public void resetTotal() {
        totalTextField.setText("0");
    }
    
    public void resetInput() {
        inputTextField.setText("");
    }
    
    public String getInput() {
        return inputTextField.getText();
    }
    
    public void setTotal(String newTotal) {
    	totalTextField.setText(newTotal);
    }
    
    public void setRest(String newRest) {
    	restTextField.setText(newRest);
    }
    
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    
   public void addAdditionListener(ActionListener al) {
        additionBtn.addActionListener(al);
    }
    
   public void addSubtractionListener(ActionListener al) {
        subtractionBtn.addActionListener(al);
    }
    
    public void addMultiplyListener(ActionListener al) {
        multiplyBtn.addActionListener(al);
    }
    
    public void addDivisionListener(ActionListener al) {
        divisionBtn.addActionListener(al);
    }
    
    public void addDerivationListener(ActionListener al) {
        derivationBtn.addActionListener(al);
    }
    
    public void addIntegrationListener(ActionListener al) {
        integrationBtn.addActionListener(al);
    }
    
    public void addInputClearListener(ActionListener al) {
        clearBtnInput.addActionListener(al);
    }
    
    public void addClearListener(ActionListener al) {
        clearBtnTotal.addActionListener(al);
    }
}
