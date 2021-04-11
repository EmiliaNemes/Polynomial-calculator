package Model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Monomial implements Comparable<Monomial>{
	
	private BigDecimal coefficient;
	private BigInteger power;
	
	public Monomial(){
		this.coefficient = new BigDecimal("0");
		this.power = new BigInteger("0");
	}
	
	public Monomial(BigInteger power){
		this.coefficient = new BigDecimal("0");
		this.power = power;
	}
	
	public Monomial(BigDecimal coef, BigInteger power){
		this.coefficient = coef;
		this.power = power;
	}

	public BigDecimal getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}

	public BigInteger getPower() {
		return power;
	}

	public void setPower(BigInteger power) {
		this.power = power;
	}
	
	@Override
	public String toString() {
		return "Coef: " + this.coefficient + "  Power: " + this.power;
	}

	@Override
	public int compareTo(Monomial o) {
		return ((this.power).intValue() - (o.getPower()).intValue());
	}

}
