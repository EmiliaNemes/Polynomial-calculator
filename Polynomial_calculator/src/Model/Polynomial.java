package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.math.RoundingMode;

public class Polynomial{
	private ArrayList<Monomial> monoms;
	private int maxGrade;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	// Constructors
	public Polynomial() {
		this.monoms = new ArrayList<Monomial>();
		this.maxGrade = 0;
	}
	
	public Polynomial(String input) throws Exception{
		this.monoms = formMonoms(input);
	}
    
	public int getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(int maxGrade) {
		this.maxGrade = maxGrade;
	}

	public ArrayList<Monomial> getMonoms() {
		return monoms;
	}

	public void setMonoms(String input) throws Exception {
		this.monoms = formMonoms(input);
	}

	public ArrayList<Monomial> orderMonoms(ArrayList<Monomial> monomsInput){
		int j = 0;
		ArrayList<Monomial> monomsInput2 = new ArrayList<Monomial>();
		
		for(Monomial m: monomsInput) {
			if(j != (m.getPower()).intValue()) {
				while(j != (m.getPower()).intValue()) {
					monomsInput2.add(j,new Monomial(new BigInteger(j+"")));
					j++;
				}
				monomsInput2.add(j,m);
				j++;
			} else {
				monomsInput2.add(j,m);
				j++;
			}
		}
		return monomsInput2;
	}
	
	public void verifyMatcher1() {
		
	}
	
	public ArrayList<Monomial> formMonoms(String input) throws Exception {
			Pattern pattern = Pattern.compile("(([+-]?)([\\d]+)?+\\*?[xX]?\\^?([\\d]+)?)+");
			Matcher matcher = pattern.matcher(input);

			if (input.matches("(([+|-]?)([\\d]+)?+\\*?[xX]?\\^?([\\d]+)?)+")) {
					pattern = Pattern.compile("([+|-])?([\\d]+)?\\*?(x|X)?(\\^)?([\\d]+)?");
					matcher = pattern.matcher(input);
					ArrayList<Monomial> monomsInput = new ArrayList<Monomial>();
					
					int i=0;
					int mGrade = 0;
					while (matcher.find()) {
						Monomial mon = new Monomial();
						
						if((matcher.group(2) == null) && (matcher.group(3) == null)) { // matcher.group(2))-coeficient, matcher.group(3))-x
							continue;
						}
						
						if(matcher.group(2) == null) {
							if((matcher.group(1) == null) || (matcher.group(1)).equals("+")){
								mon.setCoefficient(new BigDecimal("1"));
							} else {
								mon.setCoefficient(new BigDecimal("-1"));
							}
						} else {
							if((matcher.group(1) == null) || (matcher.group(1)).equals("+")){
								mon.setCoefficient(new BigDecimal(matcher.group(2)));
							} else {
								BigDecimal coef = new BigDecimal("-"+matcher.group(2));
								mon.setCoefficient(coef);
							}
						}
						
						if(matcher.group(4) == null) {  
							if(matcher.group(3) == null) {  
								mon.setPower(new BigInteger("0"));
							} else { // este x, dar nu este ^ => puterea este 1
								mon.setPower(new BigInteger("1"));
								if(1 > mGrade) {
									mGrade = 1;
								}
							}
						} else {
							mon.setPower(new BigInteger(matcher.group(5)));
							if(new BigInteger(matcher.group(5)).intValue() > mGrade) {
								mGrade = new BigInteger(matcher.group(5)).intValue();
							}
						}
						
						monomsInput.add(i,mon);
						i++;
					} 
					
					this.maxGrade = mGrade;
					Collections.sort(monomsInput); // in ordine crescatoare
					return orderMonoms(monomsInput);	
			} else {
				throw new Exception();
			}
	}
	
	@Override
	public String toString() {
		String result="";
		int ok = 0;
		
		for(Monomial m: monoms) {
			ok = 1;
			if(m.getCoefficient().doubleValue() != 0.00) {
				if(m.getCoefficient().doubleValue() > 0.00) {
					if(m.getPower().intValue() == 0) {
						result = "+" + df2.format(m.getCoefficient().doubleValue()) + result;
					} else {
						if(m.getPower().intValue() == 1) {
							result = "+" + df2.format(m.getCoefficient().doubleValue()) + "x" + result;	
						} else {
							result = "+" + df2.format(m.getCoefficient().doubleValue()) + "x^" + m.getPower().intValue() + result;
						}
					}	
		
				} else {
					if(m.getPower().intValue() == 0) {
						result = df2.format(m.getCoefficient().doubleValue()) + result;
					} else {
						if(m.getPower().intValue() == 1) {
							result = df2.format(m.getCoefficient().doubleValue()) + "x" + result;
						} else {
							result = df2.format(m.getCoefficient().doubleValue()) + "x^" + m.getPower().intValue() + result;
					    }
					}
				}
			}
		}
		if(ok == 0 || (df2.format(monoms.get(0).getCoefficient().doubleValue()) == "0.00" && maxGrade==0)) {
			result = "0";
		}
		return result;	
	}
}
