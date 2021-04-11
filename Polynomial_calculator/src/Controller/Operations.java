package Controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import Model.Monomial;
import Model.Polynomial;

public class Operations {
	private Polynomial total;
	private Polynomial rest;

	public Operations() {
		reset();
	}

	public void setTotal(Polynomial p) {
		total = p;
	}
	
	public void reset() {
		total = new Polynomial();
	}

	public void addTo(Polynomial operand) {
		if (total.toString().equals("0")) { // daca totalul este inca nul
			this.total = operand; // "0" + operand = operand;
		} else {
			if (total.getMaxGrade() < operand.getMaxGrade()) {
				Polynomial aux = total;
				total = operand;
				operand = aux;
			}

			for (Monomial m : operand.getMonoms()) {
				total.getMonoms().get(m.getPower().intValue()).setCoefficient(
						total.getMonoms().get(m.getPower().intValue()).getCoefficient().add(m.getCoefficient()));
			}
		}
	}

	public void subtractFrom(Polynomial operand) { // total = total - operand
		if (total.toString().equals("0")) { // daca totalul este inca nul => "0" - operand = - operand;
			for (Monomial m : operand.getMonoms()) {
				m.setCoefficient(m.getCoefficient().multiply(new BigDecimal("-1")));
			}
			this.total = operand;
		} else {
			for (Monomial m : operand.getMonoms()) {
				m.setCoefficient(m.getCoefficient().multiply(new BigDecimal("-1")));
			}

			if (total.getMaxGrade() < operand.getMaxGrade()) {
				Polynomial aux = total;
				total = operand;
				operand = aux;
			}

			for (Monomial m : operand.getMonoms()) {
				total.getMonoms().get(m.getPower().intValue()).setCoefficient(
						total.getMonoms().get(m.getPower().intValue()).getCoefficient().add(m.getCoefficient()));
			}

			total.setMaxGrade(0);
			for (Monomial m : total.getMonoms()) {
				if (m.getCoefficient().doubleValue() != 0.00) {
					total.setMaxGrade(m.getPower().intValue());
				}
			}
		}
	}

	public void multiplyBy(Polynomial operand) throws Exception {
		if (operand.toString().equals("0") || total.toString().equals("0")) {
			total = new Polynomial("0");
		} else {
			ArrayList<Monomial> list = new ArrayList<Monomial>();

			for (Monomial op : operand.getMonoms()) {
				for (Monomial tot : total.getMonoms()) {
					Monomial monom = new Monomial();
					monom.setCoefficient(op.getCoefficient().multiply(tot.getCoefficient()));
					monom.setPower(op.getPower().add(tot.getPower()));
					list.add(monom);
				}
			}

			int resultGrade = total.getMaxGrade() + operand.getMaxGrade();
			total = new Polynomial();
			int i = 0;
			while (i <= resultGrade) {
				total.getMonoms().add(new Monomial(new BigInteger(i + "")));
				i++;
			}

			for (Monomial m : list) {
				total.getMonoms().get(m.getPower().intValue()).setCoefficient(
						total.getMonoms().get(m.getPower().intValue()).getCoefficient().add(m.getCoefficient()));
			}
			total.setMaxGrade(resultGrade);
		}
	}

	public void divideBy(Polynomial operand) throws Exception {
		// if input=0, vagy opMXgRADE > totalMaxGrade => throw exception else...
		if((operand.getMaxGrade() == 0 && operand.getMonoms().get(0).getCoefficient().intValue() == 0) || operand.getMaxGrade() > total.getMaxGrade()) {
			throw new MyException();
		}
		
		Polynomial result = new Polynomial();
		Polynomial divizor = operand;
		int grade = 0;

		result.setMaxGrade(total.getMaxGrade() - operand.getMaxGrade());
		int i = 0;
		while (i <= result.getMaxGrade()) {
			result.getMonoms().add(new Monomial(new BigInteger(i + "")));
			i++;
		}

		while (total.getMaxGrade() >= operand.getMaxGrade()) {
			Polynomial aux = total;
			total = operand;
			operand = aux;
			grade = operand.getMaxGrade() - total.getMaxGrade();
			
			Polynomial division = new Polynomial();
			division.setMaxGrade(grade);
			int k = 0;
			while (k <= division.getMaxGrade()) {
				division.getMonoms().add(new Monomial(new BigInteger(k + "")));
				k++;
			}

			result.getMonoms().get(grade).setCoefficient(operand.getMonoms().get(operand.getMaxGrade()).getCoefficient()
					.divide(total.getMonoms().get(total.getMaxGrade()).getCoefficient(), 2, RoundingMode.HALF_EVEN));
			
			division.getMonoms().get(grade).setCoefficient(operand.getMonoms().get(operand.getMaxGrade()).getCoefficient()
					.divide(total.getMonoms().get(total.getMaxGrade()).getCoefficient(), 2, RoundingMode.HALF_EVEN));

			multiplyBy(division); // total = (m):(m) * total
			aux = total; // pt ca avem nevoie de (operand-total), si nu (total-operand)
			total = operand;
			operand = aux;

			subtractFrom(operand);
			operand = divizor;

			if (total.getMaxGrade() == 0 && total.getMonoms().get(0).getCoefficient().intValue() == 0) {
				break;
			}
		}
		rest = total;
		total = result;
	}

	public void derivate(Polynomial operand) {
		if (operand.toString().equals("0")) { // daca nu e input se deriveaza totalul
			operand = total;
		}
		int i = 0;
		while (i <= operand.getMaxGrade()) { //
			if (i > 0) {
				operand.getMonoms().get(i - 1).setCoefficient(operand.getMonoms().get(i).getCoefficient()
						.multiply(new BigDecimal(operand.getMonoms().get(i).getPower())));
				operand.getMonoms().get(i).setCoefficient(new BigDecimal("0"));
			}
			operand.getMonoms().get(i).setCoefficient(new BigDecimal("0"));
			i++;
		}
		this.total = operand;
	}

	public void integrate(Polynomial operand) {
		if (operand.toString().equals("0")) { // daca nu e input se integreaza totalul
			operand = total;
		}
		int i = operand.getMaxGrade();
		Monomial m1 = new Monomial();

		while (i >= 0) {
			if (i == operand.getMaxGrade()) {
				m1 = new Monomial(
						((operand.getMonoms().get(i).getCoefficient())
								.divide(new BigDecimal(new BigInteger(i + 1 + "")), 2, RoundingMode.HALF_EVEN)),
						new BigInteger(i + 1 + ""));
				operand.getMonoms().get(i).setCoefficient(new BigDecimal("0"));
			} else {
				operand.getMonoms().get(i + 1).setCoefficient(operand.getMonoms().get(i).getCoefficient()
						.divide(new BigDecimal(new BigInteger(i + 1 + "")), 2, RoundingMode.HALF_EVEN));
				operand.getMonoms().get(i).setCoefficient(new BigDecimal("0"));
			}
			i--;
		}
		operand.getMonoms().add(m1);
		operand.setMaxGrade(operand.getMaxGrade() + 1);
		this.total = operand;
	}

	public String getValue() {
		return total.toString();
	}
	
	public String getRest() {
		return rest.toString();
	}
}
